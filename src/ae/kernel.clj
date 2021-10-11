(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [tupelo.parse.yaml :as yaml]
            [ae.keywords :as kw]))

(def entity-map-atom
  (atom {}))

(defn entity-keys
  []
  (keys @entity-map-atom))

(defn get-entity-map
  [name]
  (get @entity-map-atom name))

(defn refresh-entity-map
  [entity-map]
  (get-entity-map (get entity-map "NAME")))

(defn assoc-entity-map
  [name entity-map]
  (swap! entity-map-atom assoc name entity-map))

(defn save-entity-map
  [entity-map]
  (assoc-entity-map (get entity-map "NAME") entity-map))

(def classifier-values-map-atom
  (atom {}))

(defn get-classifier-values-map
  [context-name]
  (get @classifier-values-map-atom context-name))

(defn add-classifier-value-
  [classifier-values-map context-name entity-name classifier-kw classifier-value]
  (let [names
        (get-in classifier-values-map [context-name classifier-kw classifier-value] [])
        i
        (.indexOf names entity-name)
        _ (if (> i -1)
            (throw (Exception. (str classifier-value " for " classifier-kw " already registered for " entity-name))))
        names
        (conj names entity-name)]
    (assoc-in classifier-values-map [context-name classifier-kw classifier-value] names)
    ))

(defn add-classifier-values-
  [classifier-values-map context-name entity-name classifier-kw classifier-values]
  (reduce
    (fn [classifier-values-map classifier-value]
      (add-classifier-value- classifier-values-map context-name entity-name classifier-kw classifier-value))
    classifier-values-map
    classifier-values))

(defn add-classifier-value
  [context-kw entity-name classifier-name classifier-value]
  (if (vector? classifier-value)
    (swap! classifier-values-map-atom add-classifier-values- context-kw entity-name classifier-name classifier-value)
    (swap! classifier-values-map-atom add-classifier-value- context-kw entity-name classifier-name classifier-value)))

(defn get-invariant-map
  [entity-id]
  (let [entity-map
        (get-entity-map entity-id)]
    (if (get-in entity-map ["DESCRIPTORS" "SYS+descriptor-INVARIANT$bool"])
      entity-map
      nil)))

(defn get-classifiers-map
  [entity-name]
  (let [entity-map
        (get-entity-map entity-name)]
    (get entity-map "CLASSIFIERS")))

(defn get-classifier
  [entity-name classifier-name]
  (get (get-classifiers-map entity-name) classifier-name))

(defn get-resources-set
  [entity-name]
  (let [resources-vec
        (get-classifier entity-name "RESOURCES")
        [_ entity-context-base-name _]
        (kw/name-as-keyword entity-name)]
    (reduce
      (fn [contexts-set context-name]
        (let [[_ context-base-name _]
              (kw/name-as-keyword context-name)]
          (conj contexts-set context-base-name)))
      #{entity-context-base-name}
      resources-vec)))

(defn get-child-map
  [entity-name env]
  (let [entity-map
        (get-entity-map entity-name)
        new-children-map
        @(get env "NEW-CHILDREN-VOLATILE")]
    (if (nil? (get new-children-map entity-name))
      nil
      entity-map)))

(defn get-federated-map
  [entity-name env]
  (let [env-federator-name
        (get env "FEDERATOR-NAME")
        entity-map
        (get-entity-map entity-name)
        this-federator-name
        (get entity-map "FEDERATOR-NAME")]
    (if (= this-federator-name env-federator-name)
      entity-map
      nil)))

(defn get-invariant-descriptors
  [entity-kw]
  (let [this-map
        (get-invariant-map entity-kw)]
    (if (nil? this-map)
      nil
      (get this-map "DESCRIPTORS"))))

(def operationid-map-atom
  (atom {}))

(defn register-function
  [env params]
  (let [operationid
        (:operationid params)
        function
        (:function params)
        goblock
        (:goblock params)]
    (swap! operationid-map-atom assoc operationid {:function function
                                                   :goblock  goblock})))

(defn request-exception-check
  [request-return-value]
  (if (not (vector? request-return-value))
    (throw (Exception. (prn-str "Request return value is not a vector " request-return-value)))
    (if (not= (count request-return-value) 2)
      (throw (Exception. (prn-str "Request return value is not a 2-tuple " request-return-value)))
      (let [[ex val]
            request-return-value]
        (if (some? ex)
          (throw ex)
          val)))))

(defn federated?
  [this-map]
  (let [this-request-port-stack
        (get this-map "REQUEST-PORT-STACK")]
    (> (count this-request-port-stack) 1)))

(defn thisDescriptors
  [this-map params]
  (let [this-descriptors
        (get this-map "DESCRIPTORS")]
    (if (nil? this-descriptors)
      (throw (Exception. (str "Descriptors is nil\n"
                              (prn-str params)
                              (prn-str this-map)))))
    this-descriptors))

(defn targetOperationids
  [env target-map params]
  (let [requestid
        (get params "requestid")
        _ (if (nil? requestid)
            (throw (Exception. (str "Requestid is nil\n"
                                    (prn-str params)
                                    (prn-str target-map)))))
        this-descriptors
        (thisDescriptors target-map params)
        this-requestid-map
        (get this-descriptors "SYS+descriptor_map-REQUESTID$operationid")
        _ (if (nil? this-requestid-map)
            (throw (Exception. (str "Requestid map is nil\n"
                                    (prn-str params)
                                    (prn-str target-map)))))
        operationids
        (get this-requestid-map requestid)]
    (if (not (vector? operationids))
      (throw (Exception. (str "Operationids for " requestid " is not a vector\n"
                              (prn-str params)
                              (prn-str target-map)))))
    (if (empty? operationids)
      (throw (Exception. (str "Operationids for " requestid " is empty\n"
                              (prn-str params)
                              (prn-str target-map)))))
    (doseq [operationid operationids]
      (if (nil? operationid)
        (throw (Exception. (str "Operationid is nil\n"
                                (prn-str params)
                                (prn-str target-map)))))
      (if (= (get-in target-map ["DESCRIPTORS" "SYS+descriptor-INVARIANT$bool"]) true)
        (let [request-descriptors
              (get-invariant-descriptors requestid)
              read-only
              (if (nil? request-descriptors)
                true
                (get request-descriptors "SYS+descriptor-READ_ONLY$bool"))]
          (if (not read-only)
            (throw (Exception. (str "Can not apply " requestid " to invariant " (get target-map "NAME")
                                    (prn-str params)
                                    (prn-str target-map))))))))
    operationids))

(defn routeFunction
  [env this-map params]
  (save-entity-map this-map)
  (let [target-name
        (get params "target_name")
        target-map
        (get-federated-map target-name env)
        target-map
        (if (some? target-map)
          target-map
          (get-child-map target-name env))
        target-map
        (if (some? target-map)
          target-map
          (get-invariant-map target-name))
        _ (if (nil? target-map)
            (throw (Exception. (str "Unreachable: " target-name "\n"
                                    (prn-str params)
                                    (prn-str this-map)))))
        requestid
        (get params "target_requestid")
        params
        (assoc params "requestid" requestid)
        operationids
        (targetOperationids env target-map params)
        [target-map rv]
        (reduce
          (fn [[target-map rv] operationid]
            (if (some? rv)
              [target-map rv]
              (let [fun
                    (:function (get @operationid-map-atom operationid))]
                (if (nil? fun)
                  (throw (Exception. (str "Operationid " operationid " has no function\n"
                                          (prn-str params)
                                          (prn-str this-map)))))
                (fun env target-map params))))
          [target-map nil]
          operationids)]
    (assoc-entity-map target-name target-map)
    [(refresh-entity-map this-map) rv]))

(defn targetOperationid
  [env target-map params]
  (let [operationids (targetOperationids env target-map params)]
    (if (> (count operationids) 1)
      (throw (Exception. (str "Multiple operationids not supported for async invocation\n"
                              (prn-str params)
                              (prn-str target-map)))))
    (first operationids)))

(defn create-operation-dispatcher
  [this-name]
  (a/go-loop [this-name this-name]
    (recur
      (try
        (let [this-map
              (get-entity-map this-name)
              this-request-port-stack
              (get this-map "REQUEST-PORT-STACK")
              _ (if (nil? this-request-port-stack)
                  (throw (Exception. (str "This request port stack is nil\n"
                                          (prn-str this-map)))))
              this-request-port
              (peek this-request-port-stack)
              _ (if (nil? this-request-port)
                  (throw (Exception. (str "This request port is nil\n"
                                          (prn-str this-map)))))
              request
              (a/<! this-request-port)
              _ (if (not (vector? request))
                  (throw (Exception. (str "Request is not a vector\n"
                                          (prn-str request)
                                          (prn-str this-map)))))
              _ (if (not= (count request) 2)
                  (throw (Exception. (str "Request is not a 2-tuple\n"
                                          (prn-str request)
                                          (prn-str this-map)))))
              [env params]
              request
              return-port
              (get params "return_port")
              _ (if (nil? return-port)
                  (throw (Exception. (str "Return port is nil\n"
                                          (prn-str params)
                                          (prn-str this-map)))))]
          (try
            (let [env
                  (assoc env "active-request-port" this-request-port)
                  requestid
                  (get params "requestid")
                  _ (if (nil? requestid)
                      (throw (Exception. (str "Requestid port is nil\n"
                                              (prn-str params)
                                              (prn-str this-map)))))
                  this-map
                  (get-entity-map this-name)
                  [this-map return-value]
                  (case requestid

                    "SNAPSHOT"
                    [this-map this-map]

                    "PUSH-REQUEST-PORT"
                    (let [this-descriptors
                          (thisDescriptors this-map params)
                          this-federator-name
                          (get this-map "FEDERATOR-NAME")]
                      (if (or (some? this-federator-name)
                              (federated? this-map))
                        (throw (Exception. (str "Inappropriate async request on federated entity.\n"
                                                (prn-str params)
                                                (prn-str this-map)))))
                      (if (get this-descriptors "SYS+descriptor-INVARIANT$bool")
                        [this-map [this-map nil]]
                        (let [new-request-port
                              (get params "new-request-port")
                              this-map
                              (assoc this-map "REQUEST-PORT-STACK" (conj this-request-port-stack new-request-port))
                              federator-name
                              (get env "FEDERATOR-NAME")
                              this-map
                              (assoc this-map "FEDERATOR-NAME" federator-name)]
                          [this-map [this-map new-request-port]])))

                    "RESET-REQUEST-PORT"
                    (let [this-map
                          (assoc this-map "REQUEST-PORT-STACK" (pop this-request-port-stack))
                          this-map
                          (assoc this-map "FEDERATOR-NAME" nil)]
                      [this-map this-map])

                    ;;DEFAULT
                    (let [
                          _ (if (federated? this-map)
                              (throw (Exception. (str "Inappropriate async request on federated entity.\n"
                                                      (prn-str params)
                                                      (prn-str this-map)))))

                          operationid
                          (targetOperationid env this-map params)
                          operation-return-port
                          (a/chan)
                          params
                          (assoc params "operation-return-port" operation-return-port)
                          operationid-submap
                          (get @operationid-map-atom operationid)
                          operation-goblock
                          (:goblock operationid-submap)
                          - (if (some? operation-goblock)
                              (operation-goblock env this-map params)
                              (throw (Exception. (str "Operationid-submap is empty\n"
                                                      (prn-str params)
                                                      (prn-str this-map)))))
                          operation-return-value
                          (a/<! operation-return-port)
                          _ (if (not (vector? operation-return-value))
                              (throw (Exception. (str "Operation return value is not a vector\n"
                                                      (prn-str operation-return-value)
                                                      (prn-str params)
                                                      (prn-str this-map)))))
                          _ (if (not= (count operation-return-value) 3)
                              (throw (Exception. (str "Operation return value is not a 3-tuple\n"
                                                      (prn-str operation-return-value)
                                                      (prn-str params)
                                                      (prn-str this-map)))))
                          [this-map e return-value]
                          operation-return-value]
                      (if (some? e)
                        (throw e))
                      [this-map return-value]
                      ))]
              (assoc-entity-map this-name this-map)
              (if (not= return-value :NO-RETURN)
                (a/>! return-port [nil return-value]))
              this-name)
            (catch Exception e
              (a/>! return-port [e nil])
              this-name)))
        (catch Exception e
          (stacktrace/print-stack-trace e)
          this-name)))))

(defn create-entity
  [env params]
  (let [new-public-request-port
        (a/chan)
        request-port-stack
        [new-public-request-port]
        descriptors
        (get params "descriptors" {})
        classifiers
        (get params "classifiers" {})
        content
        (get params "content$ml" "")
        invariant
        (get descriptors "SYS+descriptor-INVARIANT$bool")
        initialization-port
        (get params "initialization-port")
        request-port-stack
        (if (or invariant (nil? initialization-port))
          request-port-stack
          (conj request-port-stack initialization-port))
        name
        (get params "name")
        new-entity-map
        {"NAME"               name
         "DESCRIPTORS"        descriptors
         "CLASSIFIERS"        classifiers
         "RELATIONS"          {}
         "INVERSE-RELATIONS"  {}
         "CONTENT$ml"         content
         "REQUEST-PORT-STACK" request-port-stack}
        ]
    (assoc-entity-map name new-entity-map)
    (create-operation-dispatcher name)
    [new-public-request-port new-entity-map]))

(defn entityContextName
  [entity-name]
  (let [[_ entity-context-base-name _]
        (if (s/blank? entity-name)
          [nil nil nil]
          (kw/name-as-keyword entity-name))]
    (if (s/blank? entity-name)
      "ROOT+context-SYS"
      (if (= entity-context-base-name "SYS")
        "ROOT+context-SYS"
        (str "SYS+context-" entity-context-base-name)))))

#_(defn validate-edn
    [path context-map type-entity edn env]
    (println :path path)
    (let [[_ [data-type key-entity value-entity]]
          (if (nil? type-entity)
            [nil [nil nil nil]]
            (let [params
                  {"requestid"        "SYS+requestid-ROUTE"
                   "target_requestid" "SYS+requestidTYPE_OF"
                   "target_name"      type-entity}]
              (routeFunction env context-map params)))]
      (cond
        (string? edn)
        (if (not= data-type "string")
          (throw (Exception. (str "At " path ", expected a string, not\n" data-type "\n"
                                  (prn-str edn)
                                  (prn-str context-map)))))

        (vector? edn)
        (if (not= data-type "vector")
          (throw (Exception. (str "At " path ", expected a vector, not\n" data-type "\n"
                                  (prn-str edn)
                                  (prn-str context-map))))
          (let [c
                (count edn)]
            (doseq [i (range c)]
              (let [v
                    (nth edn i)]
                (println "vector" i (prn-str v))
                (validate-edn (str path " " i) context-map value-entity v env)))))

        (map? edn)
        (if (not= data-type "map")
          (throw (Exception. (str "At " path ", expected a map, not\n" data-type "\n"
                                  (prn-str edn)
                                  (prn-str context-map))))
          (doseq [[k v] edn]
            (validate-edn (str path " key") context-map key-entity k env)
            (validate-edn (str path " " (prn-str k)) context-map value-entity v env)))

        true
        (if (not= data-type "undefined")
          (throw (Exception. (str "At " path ", expected an undefined, not\n" data-type "\n"
                                  (prn-str edn)
                                  (prn-str context-map))))))))

(defn validate-entity-name
  [s]
  (let [dot-ndx
        (s/index-of s ".")
        h-ndx
        (s/index-of s "-")
        d-ndx
        (s/index-of s "$")]
    (cond
      (some? dot-ndx)
      (throw (Exception. (str "Name " s " should not contain a .")))

      (nil? h-ndx)
      (throw (Exception. (str "Name " s " is missing a -")))

      (and (some? d-ndx)
           (> h-ndx d-ndx))
      (throw (Exception. (str "Name " s " has a $ before the -")))

      (= h-ndx 0)
      (throw (Exception. (str "Name " s " begins with a -")))

      (= h-ndx (dec (count s)))
      (throw (Exception. (str "Name " s " ends with a -")))

      (and (some? d-ndx)
           (= h-ndx (dec d-ndx)))
      (throw (Exception. (str "Name " s " has a $ immediately following the -"))))))

(defn unbind-context
  [local-context-+ edn values-as-data env]
  (cond
    (string? edn)
    (if values-as-data
      edn
      (if (s/starts-with? edn local-context-+)
        (subs edn (count local-context-+))
        edn))

    (vector? edn)
    (reduce
      (fn [v item]
        (conj v (if values-as-data
                  item
                  (unbind-context local-context-+ item values-as-data env))))
      []
      edn)

    (map? edn)
    (reduce
      (fn [m [k v]]
        (assoc m (unbind-context local-context-+ k false env)
                 (unbind-context local-context-+ v
                                 (or values-as-data (some? (s/index-of k "$")))
                                 env)))
      (sorted-map)
      edn)

    true
    edn))

(defn bind-context-
  [local-context resources-set edn values-as-data env]
  (cond
    (string? edn)
    (if values-as-data
      edn
      (do
        (validate-entity-name edn)
        (if (some? (s/index-of edn "+"))
          edn
          (str local-context "+" edn))))

    (vector? edn)
    (reduce
      (fn [v item]
        (conj v (if values-as-data
                  item
                  (bind-context- local-context resources-set item values-as-data env))))
      []
      edn)

    (map? edn)
    (reduce
      (fn [m [k v]]
        (assoc m (bind-context- local-context resources-set k false env)
                 (bind-context- local-context resources-set v
                               (or values-as-data (some? (s/index-of k "$")))
                               env)))
      (sorted-map)
      edn)

    true
    edn))

(defn bind-context
  [full-context-name edn values-as-data env]
  (let [resources-set
        (get-resources-set full-context-name)
        [_ _ base-name]
        (kw/name-as-keyword full-context-name)
        base-name
        (if (s/starts-with? base-name "context-")
          (subs base-name 8)
          base-name)]
    (bind-context- base-name resources-set edn values-as-data env)))

(defn async-script
  [script-path yaml-script context-map env]
  (let [full-local-context
        (get context-map "NAME")
        out
        (a/chan)]
    (a/go
      (try
        (let [edn-script
              (yaml/parse-raw yaml-script)
              edn-script
              (reduce
                (fn [edn-script request]
                  (conj edn-script
                        (reduce
                          (fn [request [k v]]
                            (into request {k
                                           (bind-context full-local-context v (some? (s/index-of k "$")) env)}))
                          {}
                          request)))
                []
                edn-script)
              return-port0
              (a/chan)
              context-request-port
              (get env "CONTEXT-REQUEST-PORT")]
          (doseq [request-params edn-script]
            (let [request-params
                  (assoc request-params "requestid" "SYS+requestid-ROUTE")
                  request-params
                  (assoc request-params "return_port" return-port0)]
              (a/>! context-request-port [env request-params])
              (request-exception-check (a/<! return-port0))))
          ;(validate-edn script-path context-map "SYS+list-SCRIPT" edn-script env)
          )
        (a/>! out [nil])
        (catch Exception e
          (a/>! out [e]))))
    out))
