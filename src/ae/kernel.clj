(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [tupelo.parse.yaml :as yaml]
            [ae.keywords :as kw]))

(def entities-map-atom
  (atom {}))

(defn get-entity-map
  [name]
  (get @entities-map-atom name))

(defn refresh-entity-map
  [entity-map]
  (get-entity-map (get entity-map "SYS+facet-NAME&%")))

(defn assoc-entity-map
  [name entity-map]
  (swap! entities-map-atom assoc name entity-map))

(defn save-entity-map
  [entity-map]
  (assoc-entity-map (get entity-map "SYS+facet-NAME&%") entity-map))

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
    (if (get-in entity-map ["SYS+facet_map-DESCRIPTORS^descriptor" "SYS+descriptor-INVARIANT$bool"])
      entity-map
      nil)))

(defn get-classifiers-map
  [entity-name]
  (let [entity-map
        (get-entity-map entity-name)]
    (get entity-map "SYS+facet_map-CLASSIFIERS^classifier")))

(defn get-classifier
  [entity-name classifier-name]
  (get (get-classifiers-map entity-name) classifier-name))

(defn get-resources-set
  [local-context-name]
  (let [resources-vec
        (get-classifier local-context-name "SYS+classifier_vec-RESOURCES&context")
        [_ _ entity-base-name]
        (kw/name-as-keyword local-context-name)
        short-context-name
        (if (s/starts-with? entity-base-name "context-")
          (subs entity-base-name 8)
          entity-base-name)
        resources-set
        (reduce
          (fn [contexts-set context-name]
            (let [[_ _ context-base-name]
                  (kw/name-as-keyword context-name)
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
  (let [entity-map
        (get-entity-map entity-name)
        new-children-map
        @(get env "SYS+env_volmap-CHILDREN&%")]
    (if (nil? (get new-children-map entity-name))
      nil
      entity-map)))

(defn get-federated-map
  [entity-name env]
  (let [env-federator-name
        (get env "SYS+env-FEDERATORname&federator")
        entity-map
        (get-entity-map entity-name)
        this-federator-name
        (get entity-map "SYS+facet-FEDERATORname&federator")]
    (if (= this-federator-name env-federator-name)
      entity-map
      nil)))

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
        (get this-descriptors "SYS+descriptor_mapvec-REQUESTS^requestid$str")
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
            (throw (Exception. (str "Can not apply " requestid " to invariant " (get target-map "SYS+facet-NAME&%")
                                    (prn-str params)
                                    (prn-str target-map))))))))
    operationids))

(defn routeFunction
  [env this-map params]
  (save-entity-map this-map)
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
                                    (prn-str params)
                                    (prn-str this-map)))))
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

(defn get-public-request-port
  [name]
  (let [this-map
        (get-entity-map name)
        request-port-stack
        (get this-map "SYS+facet_vec-REQUESTportSTACK$chan")
        ]
    (first request-port-stack)))

(defn create-operation-dispatcher
  [this-name]
  (a/go-loop [this-name this-name]
    (recur
      (try
        (let [this-map
              (get-entity-map this-name)
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
                  (get-entity-map this-name)
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
              (assoc-entity-map this-name this-map)
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
  (let [new-public-request-port
        (a/chan)
        request-port-stack
        [new-public-request-port]
        descriptors
        (get params "SYS+param_map-DESCRIPTORS^descriptor" (sorted-map))
        classifiers
        (get params "SYS+param_map-CLASSIFIERS^classifier" (sorted-map))
        content
        (get params "SYS+param-CONTENT$str" "")
        invariant
        (get descriptors "SYS+descriptor-INVARIANT$bool")
        initialization-port
        (get params "SYS+param-INITIALIZATIONport")
        request-port-stack
        (if (or invariant (nil? initialization-port))
          request-port-stack
          (conj request-port-stack initialization-port))
        name
        (get params "SYS+param-NAME&%")
        new-entity-map
        {"SYS+facet-NAME&%"                          name
         "SYS+facet_map-DESCRIPTORS^descriptor"      descriptors
         "SYS+facet_map-CLASSIFIERS^classifier"      classifiers
         "SYS+facet_map-RELATIONS^relation&%"        (sorted-map)
         "SYS+facet_map-INVERSErelations^relation&%" (sorted-map)
         "SYS+facet-CONTENT$str"                     content
         "SYS+facet_vec-REQUESTportSTACK$chan"       request-port-stack}
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

(def styp-set
  #{"map" "vec" "mapmap" "mapvec" "vecmap"})

(def dtyp-set
  #{"bool" "str" "chan"})

(defn parse-entity-name
  [s]
  (let [
        _ (if (some? (s/index-of s "."))
            (throw (Exception. (str "Name " s " should not contain a ."))))
        _ (if (some? (s/index-of s "/"))
            (throw (Exception. (str "Name " s " should not contain a /"))))
        p-ndx
        (s/index-of s "+")
        _ (if (not= p-ndx (s/last-index-of s "+"))
            (throw (Exception. (str "Name " s " should not contain two +'s"))))
        u-ndx
        (s/index-of s "_")
        _ (if (not= u-ndx (s/last-index-of s "_"))
            (throw (Exception. (str "Name " s " should not contain two _'s"))))
        h-ndx
        (s/index-of s "-")
        _ (if (not= h-ndx (s/last-index-of s "-"))
            (throw (Exception. (str "Name " s " should not contain two -'s"))))
        c-ndx
        (s/index-of s "^")
        _ (if (not= c-ndx (s/last-index-of s "^"))
            (throw (Exception. (str "Name " s " should not contain two ^'s"))))
        a-ndx
        (s/index-of s "&")
        _ (if (not= a-ndx (s/last-index-of s "&"))
            (throw (Exception. (str "Name " s " should not contain two &'s"))))
        d-ndx
        (s/index-of s "$")
        _ (if (not= d-ndx (s/last-index-of s "$"))
            (throw (Exception. (str "Name " s " should not contain two $'s"))))
        _ (if (and (some? a-ndx) (some? d-ndx))
            (throw (Exception. (str "Name " s " can not have both & and $"))))
        _ (if (nil? h-ndx)
            (throw (Exception. (str "Name " s " is missing a -"))))
        typ-start
        (if (some? p-ndx)
          (inc p-ndx)
          0)
        typ-end
        (if (some? u-ndx)
          u-ndx
          h-ndx)
        _ (if (> typ-start typ-end)
            (throw (Exception. (str "Name " s " lacks a properly delineated type"))))
        typ
        (subs s typ-start typ-end)
        _ (if (empty? typ)
            (throw (Exception. (str "Name " s " has an empty type"))))
        styp
        (if (nil? u-ndx)
          nil
          (if (> u-ndx h-ndx)
            (throw (Exception. (str "Name " s " has an improperly delineated structure type")))
            (subs s (inc u-ndx) h-ndx)))
        _ (if (and (some? u-ndx) (empty? styp))
            (throw (Exception. (str "Name " s " has a _ but the structure type is empty"))))
        _ (if (not= (and (some? styp)
                         (or (s/starts-with? styp "map") (s/ends-with? styp "map")))
                    (some? c-ndx))
            (throw (Exception. (str "Name " s " Can include a ^ if and only if the structure type starts with map"))))
        root-end
        (if (some? c-ndx)
          c-ndx
          (if (some? a-ndx)
            a-ndx
            (if (some? d-ndx)
              d-ndx
              (count s))))
        _ (if (> h-ndx root-end)
            (throw (Exception. (str "Name " s " lacks a properly delineated root"))))
        root
        (subs s (inc h-ndx) root-end)
        _ (if (empty? root)
            (throw (Exception. (str "Name " s " has an empty root"))))
        ktyp
        (if (nil? c-ndx)
          nil
          (if (some? a-ndx)
            (if (> a-ndx c-ndx)
              (subs s (inc c-ndx) a-ndx)
              (throw (Exception. (str "Name " s " has an improperly delineated key type"))))
            (if (some? d-ndx)
              (if (> d-ndx c-ndx)
                (subs s (inc c-ndx) d-ndx)
                (throw (Exception. (str "Name " s " has an improperly delineated key type"))))
              (subs s (inc c-ndx)))))
        _ (if (and (some? c-ndx) (empty? ktyp))
            (throw (Exception. (str "Name " s " has a ^ but the key type is empty"))))
        ntyp
        (if (nil? a-ndx)
          nil
          (subs s (inc a-ndx)))
        _ (if (and (some? a-ndx) (empty? ntyp))
            (throw (Exception. (str "Name " s " has a & but the named type is empty"))))
        dtyp
        (if (nil? d-ndx)
          nil
          (subs s (inc d-ndx)))
        _ (if (and (some? d-ndx) (empty? dtyp))
            (throw (Exception. (str "Name " s " has a $ but the data type is empty"))))]
    (if (and (some? styp) (not (contains? styp-set styp)))
      (throw (Exception. (str "Name " s " has an unknown structure type: " styp))))
    (if (and (some? dtyp) (not (contains? dtyp-set dtyp)))
      (throw (Exception. (str "Name " s " has an unknown data type: " dtyp))))
    [typ styp root ktyp ntyp dtyp]))

(defn validate-names
  [edn parent-styp parent-ktyp parent-ntyp parent-dtyp env]
  (cond
    (string? edn)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a scalar, not structure type " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str (pr-str edn) " is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (some? parent-dtyp)
          (if (not= parent-dtyp "str")
            (throw (Exception. (str (pr-str edn) " is not of data type " (pr-str parent-dtyp)))))
          (let [[typ styp root ktyp ntyp dtyp]
                (parse-entity-name edn)]
            (if (nil? parent-ntyp)
              (throw (Exception. (str "There is no name type for " (pr-str edn)))))
            (if (and (not= parent-ntyp "%") (not= typ parent-ntyp))
              (throw (Exception. (str (pr-str edn) " is not of name type " parent-ntyp))))
            (if (nil? (get-entity-map edn))
              (throw (Exception. (str "No such entity: " (pr-str edn)))))))))

    (vector? edn)
    (if (and (not (s/starts-with? parent-styp "vec")) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a vector, not structure typ " (pr-str parent-styp))))
      (let [styp
            (if (= parent-styp "vecmap")
              "map"
              nil)]
        (doseq [item edn]
          (validate-names item styp parent-ktyp parent-ntyp parent-dtyp env))))

    (map? edn)
    (doseq [[k v] edn]
      (if (some? v)
        (let [[typ styp root ktyp ntyp dtyp]
              (parse-entity-name k)
              styp
              (if (= parent-styp "mapmap")
                "map"
                (if (= parent-styp "mapvec")
                  "vec"
                  (if (= parent-styp "map")
                    styp
                    (throw (Exception. (str (pr-str edn) " is a map, not structure typ " (pr-str parent-styp)))))))
              _ (if (and (not= parent-ktyp "%") (not= typ parent-ktyp))
                  (throw (Exception. (str (pr-str k) " is not the expected key type: " (pr-str parent-ktyp)))))
              ktyp
              (if (= parent-ktyp "%")
                "%"
                ktyp)
              [ntyp dtyp]
              (if (or (some? parent-ntyp) (some? parent-dtyp))
                [parent-ntyp parent-dtyp]
                [ntyp dtyp])]
          (validate-names k nil nil "%" nil env)
          (validate-names v styp ktyp ntyp dtyp env))))

    (boolean? edn)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a scalar, not structure typ " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str (pr-str edn) " is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (not= parent-dtyp "bool")
          (throw (Exception. (str (pr-str edn) "is boolean, not "
                                  (if (nil? parent-dtyp)
                                    "a name"
                                    parent-dtyp)))))))

    (= (class edn) clojure.core.async.impl.channels.ManyToManyChannel)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str "clojure.core.async.chan is a scalar, not structure typ " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str "clojure.core.async.chan is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (not= parent-dtyp "chan")
          (throw (Exception. (str "clojure.core.async.chan is not "
                                  (if (nil? parent-dtyp)
                                    "a name"
                                    parent-dtyp)))))))

    true
    (throw (Exception. (str "Data type " (pr-str parent-dtyp) " is not known, value: " (pr-str edn))))))

(def testChanClass (class (a/chan)))

(defn unbind-context
  [edn parent-dtyp env]
  (cond
    (string? edn)
    (if (some? parent-dtyp)
      edn
      (let [ndx
            (s/index-of edn "+")]
        (if (nil? ndx)
          (throw (Exception. (str "Expecting a + in name " edn)))
          (subs edn (inc ndx)))))

    (vector? edn)
    (reduce
      (fn [v item]
        (conj v (unbind-context item parent-dtyp env)))
      []
      edn)

    (map? edn)
    (reduce
      (fn [m [k v]]
        (if (or (nil? v)
                (and (map? v) (empty? v))
                (and (vector? v) (empty? v)))
          m
          (let [[typ styp root ktyp ntyp dtyp]
                (parse-entity-name k)
                dtyp
                (if (some? parent-dtyp)
                  parent-dtyp
                  dtyp)]
            (assoc m (unbind-context k nil env)
                     (unbind-context v dtyp env)))))
      (sorted-map)
      edn)

    (boolean? edn)
    edn

    (= (class edn) testChanClass)
    "clojure.core.async.chan"

    true
    (pr-str (class edn))))

(defn bind-context-
  [local-context resources-set edn parent-styp parent-ktyp parent-ntyp parent-dtyp env]
  (cond
    (string? edn)
    (if (some? parent-dtyp)
      edn
      (let [ndx
            (s/index-of edn "+")
            un-edn
            (if (some? ndx)
              (subs edn (inc ndx))
              edn)
            full-edn
            (reduce
              (fn [full-edn resource]
                (if (some? full-edn)
                  full-edn
                  (let [full-edn
                        (str resource "+" un-edn)]
                    (if (some? (get-entity-map full-edn))
                      full-edn
                      nil))))
              nil
              resources-set)
            full-edn
            (if (some? full-edn)
              full-edn
              (if (= un-edn "context-SYS")
                "ROOT+context-SYS"
                (str local-context "+" un-edn)))]
        (if (some? ndx)
          (if (not= edn full-edn)
            (throw (Exception. (str edn " should have been " full-edn)))))
        full-edn))

    (vector? edn)
    (let [styp
          (if (= parent-styp "vecmap")
            "map"
            nil)]
      (reduce
        (fn [v item]
          (conj v (bind-context- local-context resources-set item styp parent-ktyp parent-ntyp parent-dtyp env)))
        []
        edn))

    (map? edn)
    (reduce
      (fn [m [k v]]
        (let [[typ styp root ktyp ntyp dtyp]
              (parse-entity-name k)
              styp
              (if (= parent-styp "mapmap")
                "map"
                (if (= parent-styp "mapvec")
                  "vec"
                  styp))
              [ntyp dtyp]
              (if (or (some? parent-ntyp) (some? parent-dtyp))
                [parent-ntyp parent-dtyp]
                [ntyp dtyp])]
          (assoc m (bind-context- local-context resources-set k nil nil "%" nil env)
                   (bind-context- local-context resources-set v styp ktyp ntyp dtyp env))))
      (sorted-map)
      edn)

    true
    edn))

(defn bind-context
  [full-context-name edn styp ktyp ntyp dtyp env]
  (let [[_ _ context-base-name]
        (kw/name-as-keyword full-context-name)
        short-context-name
        (if (s/starts-with? context-base-name "context-")
          (subs context-base-name 8)
          context-base-name)
        resources-set
        (get-resources-set full-context-name)]
    (bind-context- short-context-name resources-set edn styp ktyp ntyp dtyp env)))

(defn parse-bind-script
  [yaml-script this-map env]
  (let [full-local-context
        (get this-map "SYS+facet-NAME&%")
        edn-script
        (yaml/parse-raw yaml-script)]
    (reduce
      (fn [edn-script request-map]
        (conj edn-script
              (bind-context full-local-context request-map "map" "request" nil nil env)))
      []
      edn-script)))

(defn merge-maps
  [base-map update-map]
  (reduce
    (fn [base-map [uk uv]]
      (let [bv
            (get base-map uk)]
        (if (nil? bv)
          (assoc base-map uk uv)
          (if (not= (map? bv) (map? uv)
                    (throw (Exception. (str "change of value type for " uk))))
            (if (not (map? uv))
              (assoc base-map uk uv)
              (assoc base-map uk (merge-maps bv uv)))))))
    base-map
    update-map))