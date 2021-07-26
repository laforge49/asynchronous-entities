(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [ae.keywords :as kw]))

(def invariant-map-atom
  (atom {}))

(def operationid-map-atom
  (atom {}))

(defn register-function
  [env params]
  (let [operationid
        (:operationid params)
        function
        (:function params)]
    (swap! operationid-map-atom assoc operationid [nil function])))

(defn register-operation
  [env params]
  (let [operationid
        (:operationid params)
        function
        (:function params)
        port
        (a/chan)]
    (swap! operationid-map-atom assoc operationid [port function])
    port))

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

(defn thisOperationid
  [env this-map params]
  (let [requestid
        (:requestid params)
        _ (if (nil? requestid)
            (throw (Exception. (str "Requestid is nil\n"
                                    (prn-str params)
                                    (prn-str this-map)))))
        this-descriptors
        (thisDescriptors this-map params)
        this-requestid-map
        (:CONTEXTS/REQUESTID_MAP this-descriptors)
        _ (if (nil? this-requestid-map)
            (throw (Exception. (str "Requestid map is nil\n"
                                    (prn-str params)
                                    (prn-str this-map)))))
        operationids
        (requestid this-requestid-map)
        _ (if (not (vector? operationids))
            (throw (Exception. (str "Operationids for " requestid " is not a vector\n"
                                    (prn-str params)
                                    (prn-str this-map)))))
        operationid
        (first operationids)
        _ (if (nil? operationid)
            (throw (Exception. (str "Operationid is nil\n"
                                    (prn-str params)
                                    (prn-str this-map)))))]
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
          (if (contains? @invariant-map-atom target-name)
            (get @invariant-map-atom target-name)
            nil))
        _ (if (nil? target-map)
            (throw (Exception. (str "Unreachable: " target-name
                                    (prn-str params)
                                    (prn-str this-map)))))
        requestid
        (:target_requestid params)
        params
        (assoc params :requestid requestid)
        operationid
        (thisOperationid env target-map params)
        fun
        (second (operationid @operationid-map-atom))
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
                       this-map
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
                                                   (prn-str this-map)))))
                       [this-map return-value]
                       (case requestid

                         :SNAPSHOT
                         [this-map this-map]

                         :PUSH-REQUEST-PORT
                         (let [this-descriptors
                               (thisDescriptors this-map params)]
                           (if (:CONTEXTS/INVARIANT this-descriptors)
                             [this-map [this-map nil]]
                             (let [new-request-port
                                   (:new-request-port params)
                                   this-map
                                   (assoc this-map :REQUEST-PORT-STACK (conj this-request-port-stack new-request-port))]
                               [this-map [this-map new-request-port]])))

                         :RESET-REQUEST-PORT
                         (let [this-map
                               (:this-map params)
                               this-map
                               (assoc this-map :REQUEST-PORT-STACK (pop this-request-port-stack))]
                           [this-map this-map])

                         ;;DEFAULT
                         (let [operationid
                               (thisOperationid env this-map params)
                               operation-port
                               (first (operationid @operationid-map-atom))
                               _ (if (nil? operation-port)
                                   (throw (Exception. (str "Operation port is nil\n"
                                                           (prn-str params)
                                                           (prn-str this-map)))))
                               operation-return-port
                               (a/chan)
                               _ (a/>! operation-port [env this-map (assoc params :operation-return-port operation-return-port)])
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
        (:CONTEXTS/INVARIANT descriptors)
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
    (if (= (:CONTEXTS/INVARIANT descriptors) true)
      (swap! invariant-map-atom assoc name new-entity-map))
    (create-operation-dispatcher new-entity-map)
    [new-public-request-port new-entity-map]))

(defn entityContextName
  [entity-name]
  (let [[_ entity-context-base-name _]
        (if (s/blank? entity-name)
          [nil "" nil]
          (kw/name-as-keyword entity-name))]
    (if (s/blank? entity-name)
      "ROOT/CONTEXTS"
      (if (= entity-context-base-name "CONTEXTS")
        (str "ROOT/CONTEXTS")
        (str "CONTEXTS/" entity-context-base-name)))))
