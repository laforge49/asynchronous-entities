(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [tupelo.parse.yaml :as yaml]
            [ae.names :as n]
            [ae.internals :as i]))

(defn get-entity-names
  []
  (keys @i/gem-maps-atom))

(defn entity-exists?
  [name]
  (some? (i/get-gem-map name)))

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

(defn get-request-port-stack
  [name]
  (get (i/get-gem-map name) "SYS+facet_vec-REQUESTportSTACK$chan"))

(defn invariant?
  [gem-map]
  (get-in gem-map ["SYS+facet_map-DESCRIPTORS^descriptor" "SYS+descriptor-INVARIANT$bool"]))

(defn get-invariant-map
  [name]
  (let [entity-map
        (i/get-gem-map name)]
    (if (invariant? entity-map)
      entity-map
      nil)))

(defn refresh-entity-map
  [entity-map]
  (i/get-gem-map (get entity-map "SYS+facet-NAME&%")))

(defn save-entity-map
  [gem-map]
  (if (invariant? gem-map)
    (i/assoc-gem-map (get gem-map "SYS+facet-NAME&%") gem-map)))

(defn get-classifiers-map
  [entity-name]
  (let [entity-map
        (i/get-gem-map entity-name)]
    (get entity-map "SYS+facet_map-CLASSIFIERS^classifier")))

(defn get-classifier
  [entity-name classifier-name]
  (get (get-classifiers-map entity-name) classifier-name))

(defn get-resources-set
  [local-context-name]
  (let [resources-vec
        (get-classifier local-context-name "SYS+classifier-RESOURCES_vec&context")
        [_ entity-base-name]
        (n/parse-into-2 local-context-name)
        short-context-name
        (if (s/starts-with? entity-base-name "context-")
          (subs entity-base-name 8)
          entity-base-name)
        resources-set
        (reduce
          (fn [contexts-set context-name]
            (let [[_ context-base-name]
                  (n/parse-into-2 context-name)
                  context-base-name
                  (if (s/starts-with? context-base-name "context-")
                    (subs context-base-name 8)
                    context-base-name)]
              (conj contexts-set context-base-name)))
          #{short-context-name}
          resources-vec)]
    resources-set))

(defn get-child-map
  [entity-name env]
  #_ (println :get-child-map :env-keys (prn-str (keys env)))
  (let [entity-map
        (i/get-gem-map entity-name)
        new-children-volmap
        (get env "SYS+env_volmap-CHILDREN&%")
        new-children-map
        (if (nil? new-children-volmap)
          nil
          @new-children-volmap)]
    (if (nil? (get new-children-map entity-name))
      nil
      entity-map)))

(defn get-federated-map
  [entity-name env]
  (let [env-federator-name
        (get env "SYS+env-FEDERATORname&federator")
        entity-map
        (i/get-gem-map entity-name)
        #_ (println "\n" :target-map entity-name " - " (prn-str entity-map) "\n")
        this-federator-name
        (get entity-map "SYS+facet-FEDERATORname&federator")]
    (if (= this-federator-name env-federator-name)
      entity-map
      nil)))

(defn assoc-federated-entity-map
  [name entity-map env]
  (let [federated-map
        (get-federated-map name env)])
  (if (some? get-federated-map)
    (i/assoc-gem-map name entity-map)
    (throw (Exception. (str "Not federated: " name)))))

(defn get-invariant-descriptors
  [entity-kw]
  (let [this-map
        (get-invariant-map entity-kw)]
    (if (nil? this-map)
      nil
      (get this-map "SYS+facet_map-DESCRIPTORS^descriptor"))))

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
    (if (not= (count request-return-value) 1)
      (throw (Exception. (prn-str "Request return value is not a 1-tuple " request-return-value)))
      (let [[ex]
            request-return-value]
        (if (some? ex)
          (throw ex))))))

(defn federated?
  [this-map]
  (let [this-request-port-stack
        (get this-map "SYS+facet_vec-REQUESTportSTACK$chan")]
    (> (count this-request-port-stack) 1)))

(defn thisDescriptors
  [this-map params]
  (let [this-descriptors
        (get this-map "SYS+facet_map-DESCRIPTORS^descriptor")]
    (if (nil? this-descriptors)
      (throw (Exception. (str "Descriptors is nil\n"
                              (prn-str params)
                              (prn-str this-map)))))
    this-descriptors))

(defn targetOperationids
  [env target-map params]
  (let [requestid
        (get params "SYS+param-REQUESTID&requestid")
        _ (if (nil? requestid)
            (throw (Exception. (str "Requestid is nil\n"
                                    (prn-str params)
                                    (prn-str target-map)))))
        this-descriptors
        (thisDescriptors target-map params)
        this-requestid-map
        (get this-descriptors "SYS+descriptor-REQUESTS_mapvec^requestid$str")
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
      (if (= (get-in target-map ["SYS+facet_map-DESCRIPTORS^descriptor" "SYS+descriptor-INVARIANT$bool"]) true)
        (let [request-descriptors
              (get-invariant-descriptors requestid)
              read-only
              (if (nil? request-descriptors)
                true
                (get request-descriptors "SYS+descriptor-READonly$bool"))]
          (if (not read-only)
            (throw (Exception. (str "Can not apply " requestid " to invariant " (get target-map "SYS+facet-NAME&%") "\n"
                                    :params " " (prn-str params)
                                    :this-map " " (prn-str target-map))))))))
    operationids))

(defn routeFunction
  [env this-map params]
  #_ (println :routeFunction :params (prn-str params))
  (if (some? this-map)
    (save-entity-map this-map))
  (let [target-name
        (get params "SYS+param-TARGETname&%")
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
            (throw (Exception. (str "Unreachable: " (prn-str target-name) "\n"
                                    :params " - " (prn-str params) "\n"
                                    :this-map " - " (prn-str this-map) "\n"
                                    :env " - " env "\n"))))
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
    (if (nil? (get-invariant-map target-name))
      (i/assoc-gem-map target-name target-map))
    (let [this-map
          (if (some? this-map)
            (refresh-entity-map this-map)
            nil)]
      [this-map rv])))

(defn targetOperationid
  [env target-map params]
  (let [operationids (targetOperationids env target-map params)]
    (if (> (count operationids) 1)
      (throw (Exception. (str "Multiple operationids not supported for async invocation\n"
                              (prn-str params)
                              (prn-str target-map)))))
    (first operationids)))

(defn get-public-request-port
  [name]
  (let [request-port-stack
        (get-request-port-stack name)]
    (if (nil? request-port-stack)
      nil
      (first request-port-stack))))

(defn create-operation-dispatcher
  [this-name]
  (a/go-loop [this-name this-name]
    (recur
      (try
        (let [this-map
              (i/get-gem-map this-name)
              this-request-port-stack
              (get this-map "SYS+facet_vec-REQUESTportSTACK$chan")
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
              (get params "SYS+param-RETURN$chan")
              _ (if (nil? return-port)
                  (throw (Exception. (str "Return port is nil\n"
                                          (prn-str params)
                                          (prn-str this-map)))))]
          (try
            (let [requestid
                  (get params "SYS+param-REQUESTID&requestid")
                  _ (if (nil? requestid)
                      (throw (Exception. (str "Requestid port is nil\n"
                                              (prn-str params)
                                              (prn-str this-map)))))
                  this-map
                  (i/get-gem-map this-name)
                  [this-map]
                  (case requestid

                    "SNAPSHOT"
                    [this-map]

                    "PUSH-REQUEST-PORT"
                    (let [this-descriptors
                          (thisDescriptors this-map params)
                          this-federator-name
                          (get this-map "SYS+facet-FEDERATORname&federator")]
                      (if (or (some? this-federator-name)
                              (federated? this-map))
                        (throw (Exception. (str "Inappropriate async request on federated entity.\n"
                                                (prn-str params)
                                                (prn-str this-map)))))
                      (if (get this-descriptors "SYS+descriptor-INVARIANT$bool")
                        [this-map]
                        (let [new-request-port
                              (get params "SYS+param-NEWrequestport")
                              this-map
                              (assoc this-map "SYS+facet_vec-REQUESTportSTACK$chan" (conj this-request-port-stack new-request-port))
                              federator-name
                              (get env "SYS+env-FEDERATORname&federator")
                              this-map
                              (assoc this-map "SYS+facet-FEDERATORname&federator" federator-name)]
                          [this-map])))

                    "RESET-REQUEST-PORT"
                    (let [this-map
                          (assoc this-map "SYS+facet_vec-REQUESTportSTACK$chan" (pop this-request-port-stack))
                          this-map
                          (assoc this-map "SYS+facet-FEDERATORname&federator" nil)]
                      [this-map])

                    ;;DEFAULT
                    (let [_ (if (federated? this-map)
                              (throw (Exception. (str "Inappropriate async request on federated entity.\n"
                                                      (prn-str params)
                                                      (prn-str this-map)))))

                          operationid
                          (targetOperationid env this-map params)
                          operation-return-port
                          (a/chan)
                          params
                          (assoc params "SYS+param-OPERATIONreturnport" operation-return-port)
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
                                                      (prn-str :operation-return-value operation-return-value)
                                                      (prn-str :params params)
                                                      (prn-str :this-map this-map)))))
                          _ (if (not= (count operation-return-value) 2)
                              (throw (Exception. (str "Operation return value is not a 2-tuple\n"
                                                      (prn-str :operation-return-value operation-return-value)
                                                      (prn-str :params params)
                                                      (prn-str :this-map this-map)))))
                          [this-map e]
                          operation-return-value]
                      (if (some? e)
                        (throw e))
                      [this-map]
                      ))]
              (i/assoc-gem-map this-name this-map)
              (a/>! return-port [nil])
              this-name)
            (catch Exception e
              (a/>! return-port [e])
              this-name)))
        (catch Exception e
          (stacktrace/print-stack-trace e)
          this-name)))))

(defn create-entity
  [env params]
  (let [federator-name
        (get env "SYS+env-FEDERATORname&federator")
        name
        (get params "SYS+param-NAME&%")
        descriptors
        (get params "SYS+param-DESCRIPTORS_map^descriptor" (sorted-map))
        classifiers
        (get params "SYS+param-CLASSIFIERS_map^classifier" (sorted-map))
        content
        (get params "SYS+param-CONTENT$str" "")
        invariant
        (get descriptors "SYS+descriptor-INVARIANT$bool")
        new-public-request-port
        (if (not invariant)
          (a/chan)
          nil)
        request-port-stack
        (if (not invariant)
          [new-public-request-port]
          nil)
        initialization-port
        (get params "SYS+param-INITIALIZATIONport")
        request-port-stack
        (if (not invariant)
          (if (nil? initialization-port)
            request-port-stack
            (conj request-port-stack initialization-port))
          nil)
        new-entity-map
        {"SYS+facet-NAME&%"                     name
         "SYS+facet-FEDERATORname&federator"    federator-name
         "SYS+facet_map-DESCRIPTORS^descriptor" descriptors
         "SYS+facet_map-CLASSIFIERS^classifier" classifiers
         ;"FED+facet-RELATIONS_map^relation&%"        (sorted-map)
         ;"FED+facet-INVERSErelations_map^relation&%" (sorted-map)
         "SYS+facet-CONTENT$str"                content
         "SYS+facet_vec-REQUESTportSTACK$chan"  request-port-stack}
        ]
    (i/assoc-gem-map name new-entity-map)
    (if (not invariant)
      (create-operation-dispatcher name))))

(defn entityContextName
  [entity-name]
  (let [[entity-context-base-name _]
        (if (s/blank? entity-name)
          [nil nil]
          (n/parse-into-2 entity-name))]
    #_ (println :entityContextName entity-name entity-context-base-name)
    (if (s/blank? entity-name)
      "ROOT+context-SYS"
      (if (= entity-context-base-name "SYS")
        "ROOT+context-SYS"
        (str "SYS+context-" entity-context-base-name)))))

(defn merge-maps
  [base-map update-map]
  (reduce
    (fn [base-map [uk uv]]
      (let [bv
            (get base-map uk)]
        (if (nil? bv)
          (assoc base-map uk uv)
          (if (not= (map? bv) (map? uv))
            (throw (Exception. (str "change of value type for " uk
                                    "\n" :bv " " (map? bv) " " (prn-str bv)
                                    :uv " " (map? uv) " " (prn-str uv))))
            (if (not (map? uv))
              (assoc base-map uk uv)
              (assoc base-map uk (merge-maps bv uv)))))))
    base-map
    update-map))
