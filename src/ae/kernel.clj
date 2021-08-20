(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [ae.keywords :as kw]))

(def classifier-values-map-atom
  (atom {}))

(defn get-classifier-values-map
  [context-name]
  (get @classifier-values-map-atom context-name))

(defn classifier-name?
  [name]
  (some? (get-classifier-values-map name)))

(defn add-classifier-value-
  [classifier-values-map context-name entity-name classifier-kw classifier-value-kw]
  (let [names
        (get-in classifier-values-map [context-name classifier-kw classifier-value-kw] [])
        i
        (.indexOf names entity-name)
        _ (if (> i -1)
            (throw (Exception. (str classifier-value-kw " for " classifier-kw " already registered for " entity-name))))
        names
        (conj names entity-name)]
    (assoc-in classifier-values-map [context-name classifier-kw classifier-value-kw] names)))

(defn add-classifier-value
  [context-kw entity-name classifier-name classifier-value-kw]
  (swap! classifier-values-map-atom add-classifier-value- context-kw entity-name classifier-name classifier-value-kw))

(def invariant-map-atom
  (atom {}))

(defn get-invariant-map
  [entity-id]
  (let [[_ context-base-name base-name]
        (if (keyword? entity-id)
          (kw/keyword-as-name entity-id)
          (kw/name-as-keyword entity-id))]
    (get-in @invariant-map-atom [context-base-name base-name])))

(defn add-invariant-map
  [entity-id this-map]
  (let [[_ context-base-name base-name]
        (if (keyword? entity-id)
          (kw/keyword-as-name entity-id)
          (kw/name-as-keyword entity-id))]
    (swap! invariant-map-atom assoc-in [context-base-name base-name] this-map)))

(defn get-invariant-descriptors
  [entity-kw]
  (let [this-map
        (get-invariant-map entity-kw)]
    (if (nil? this-map)
      nil
      (:DESCRIPTORS this-map))))

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
        (:REQUEST-PORT-STACK this-map)]
    (> (count this-request-port-stack) 1)))

(defn thisDescriptors
  [this-map params]
  (let [this-descriptors
        (:DESCRIPTORS this-map)]
    (if (nil? this-descriptors)
      (throw (Exception. (str "Descriptors is nil\n"
                              (prn-str params)
                              (prn-str this-map)))))
    this-descriptors))

(defn targetOperationid
  [env target-map params]
  (let [requestid
        (:requestid params)
        _ (if (nil? requestid)
            (throw (Exception. (str "Requestid is nil\n"
                                    (prn-str params)
                                    (prn-str target-map)))))
        this-descriptors
        (thisDescriptors target-map params)
        this-requestid-map
        (:SYSTEMcontext/REQUESTID_MAP this-descriptors)
        _ (if (nil? this-requestid-map)
            (throw (Exception. (str "Requestid map is nil\n"
                                    (prn-str params)
                                    (prn-str target-map)))))
        operationids
        (get this-requestid-map requestid)
        _ (if (not (vector? operationids))
            (throw (Exception. (str "Operationids for " requestid " is not a vector\n"
                                    (prn-str params)
                                    (prn-str target-map)))))
        operationid
        (first operationids)
        _ (if (nil? operationid)
            (throw (Exception. (str "Operationid is nil\n"
                                    (prn-str params)
                                    (prn-str target-map)))))]
    (if (= (get-in target-map [:DESCRIPTORS :SYSTEMcontext/INVARIANTdescriptor]) true)
      (let [request-descriptors
            (get-invariant-descriptors requestid)
            read-only
            (if (nil? request-descriptors)
              true
              (:SYSTEMcontext/READ_ONLYdescriptor request-descriptors))]
        (if (not read-only)
          (throw (Exception. (str "Can not apply " requestid " to invariant " (:NAME target-map)))))))
    operationid))

(defn federationRouteFunction
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        federation-map-volatile
        (:FEDERATION-MAP-VOLATILE env)
        _ (if (federated? this-map)
            (vreset! (first (get @federation-map-volatile this-name)) this-map))
        target-name
        (:target_name params)
        federation-entry
        (get @federation-map-volatile target-name)
        target-map
        (if (some? federation-entry)
          @(first federation-entry)
          (get-invariant-map target-name))
        _ (if (nil? target-map)
            (throw (Exception. (str "Unreachable: " target-name
                                    (prn-str params)
                                    (prn-str this-map)))))
        requestid
        (:target_requestid params)
        params
        (assoc params :requestid requestid)
        operationid
        (targetOperationid env target-map params)
        fun
        (:function (get @operationid-map-atom operationid))
        _ (if (nil? fun)
            (throw (Exception. (str "Operationid " operationid " has no function\n"
                                    (prn-str params)
                                    (prn-str this-map)))))
        [target-map rv]
        (fun env target-map params)
        _ (if (federated? target-map)
            (vreset! (first (get @federation-map-volatile target-name)) target-map))
        this-map
        (if (federated? this-map)
          @(first (get @federation-map-volatile this-name))
          this-map)]
    [this-map rv]))

(defn create-operation-dispatcher
  [this-map]
  (a/go-loop [this-map this-map]
    (recur (try
             (let [this-request-port-stack
                   (:REQUEST-PORT-STACK this-map)
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
                   (:return_port params)
                   _ (if (nil? return-port)
                       (throw (Exception. (str "Return port is nil\n"
                                               (prn-str params)
                                               (prn-str this-map)))))]
               (try
                 (let [this-name
                       (:NAME this-map)
                       federation-map
                       (:FEDERATION-MAP env)
                       target-map
                       (if (federated? this-map)
                         (first (get federation-map this-name))
                         this-map)
                       env
                       (assoc env :active-request-port this-request-port)
                       requestid
                       (:requestid params)
                       _ (if (nil? requestid)
                           (throw (Exception. (str "Requestid port is nil\n"
                                                   (prn-str params)
                                                   (prn-str target-map)))))
                       [this-map return-value]
                       (case requestid

                         "SNAPSHOT"
                         [target-map target-map]

                         "PUSH-REQUEST-PORT"
                         (let [this-descriptors
                               (thisDescriptors target-map params)]
                           (if (federated? target-map)
                             (throw (Exception. (str "Inappropriate async request on federated entity.\n"
                                                     (prn-str params)
                                                     (prn-str target-map)))))
                           (if (:SYSTEMcontext/INVARIANTdescriptor this-descriptors)
                             [target-map [target-map nil]]
                             (let [new-request-port
                                   (:new-request-port params)
                                   this-map
                                   (assoc target-map :REQUEST-PORT-STACK (conj this-request-port-stack new-request-port))]
                               [this-map [this-map new-request-port]])))

                         "RESET-REQUEST-PORT"
                         (let [this-map
                               (:this-map params)
                               this-map
                               (assoc this-map :REQUEST-PORT-STACK (pop this-request-port-stack))]
                           [this-map this-map])

                         ;;DEFAULT
                         (let [_ (if (federated? target-map)
                                   (throw (Exception. (str "Inappropriate async request on federated entity.\n"
                                                           (prn-str params)
                                                           (prn-str target-map)))))
                               operationid
                               (targetOperationid env target-map params)
                               operation-return-port
                               (a/chan)
                               params
                               (assoc params :operation-return-port operation-return-port)
                               operationid-submap
                               (get @operationid-map-atom operationid)
                               operation-port
                               (:port operationid-submap)
                               operation-goblock
                               (:goblock operationid-submap)
                               - (if (some? operation-port)
                                   (a/>! operation-port [env target-map params])
                                   (if (some? operation-goblock)
                                     (operation-goblock env target-map params)
                                     (throw (Exception. (str "Operationid-submap is empty\n"
                                                             (prn-str params)
                                                             (prn-str target-map))))))
                               operation-return-value
                               (a/<! operation-return-port)
                               _ (if (not (vector? operation-return-value))
                                   (throw (Exception. (str "Operation return value is not a vector\n"
                                                           (prn-str operation-return-value)
                                                           (prn-str params)
                                                           (prn-str target-map)))))
                               _ (if (not= (count operation-return-value) 3)
                                   (throw (Exception. (str "Operation return value is not a 3-tuple\n"
                                                           (prn-str operation-return-value)
                                                           (prn-str params)
                                                           (prn-str target-map)))))
                               [this-map e return-value]
                               operation-return-value]
                           (if (some? e)
                             (throw e))
                           [this-map return-value]
                           ))]
                   (if (not= return-value :NO-RETURN)
                     (a/>! return-port [nil return-value]))
                   this-map)
                 (catch Exception e
                   (a/>! return-port [e nil])
                   this-map)))
             (catch Exception e
               (stacktrace/print-stack-trace e)
               this-map)))))

(defn create-entity
  [env params]
  (let [new-public-request-port
        (a/chan)
        request-port-stack
        [new-public-request-port]
        descriptors
        (:descriptors params)
        descriptors
        (if (nil? descriptors)
          {}
          descriptors)
        classifiers
        (:classifiers params)
        classifiers
        (if (nil? classifiers)
          {}
          classifiers)
        invariant
        (:SYSTEMcontext/INVARIANTdescriptor descriptors)
        initialization-port
        (:initialization-port params)
        request-port-stack
        (if (or invariant (nil? initialization-port))
          request-port-stack
          (conj request-port-stack initialization-port))
        name
        (:name params)
        new-entity-map
        {:NAME               name
         :DESCRIPTORS        descriptors
         :CLASSIFIERS        classifiers
         :REQUEST-PORT-STACK request-port-stack}
        ]
    (if (= (:SYSTEMcontext/INVARIANTdescriptor descriptors) true)
      (add-invariant-map name new-entity-map))
    (create-operation-dispatcher new-entity-map)
    [new-public-request-port new-entity-map]))

(defn entityContextName
  [entity-name]
  (let [[_ entity-context-base-name _]
        (if (s/blank? entity-name)
          [nil "" nil]
          (kw/name-as-keyword entity-name))]
    (if (s/blank? entity-name)
      "ROOT+SYSTEMcontext"
      (if (= entity-context-base-name "SYSTEMcontext")
        (str "ROOT+SYSTEMcontext")
        (str "SYSTEMcontext+" entity-context-base-name)))))
