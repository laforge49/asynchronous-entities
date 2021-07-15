(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [ae.keywords :as kw]))

(def operationid-map-atom
  (atom {}))

(defn register-operation-port
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
    (> (count this-request-port-stack) 0)))

(defn thisDescriptors
  [this-map params]
  (let [this-descriptors
        (:DESCRIPTORS this-map)]
    (if (nil? this-descriptors)
      (throw (Exception. (str "Descriptors is nil\n"
                              (prn-str params)
                              (prn-str this-map)))))
    this-descriptors))

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
                   [env params]
                   request
                   return-port
                   (:return-port params)
                   _ (if (nil? return-port)
                       (throw (Exception. (str "Return port is nil\n"
                                               (prn-str params)
                                               (prn-str this-map)))))]
               (try
                 (let [env
                       (assoc env :active-request-port this-request-port)
                       requestid
                       (:requestid params)
                       _ (if (nil? requestid)
                           (throw (Exception. (str "Requestid port is nil\n"
                                                   (prn-str params)
                                                   (prn-str this-map)))))
                       this-descriptors
                       (thisDescriptors this-map params)
                       this-requestid-map
                       (:REQUESTID-MAP this-descriptors)
                       _ (if (nil? this-requestid-map)
                           (throw (Exception. (str "requestid map is nil\n"
                                                   (prn-str params)
                                                   (prn-str this-map)))))
                       [this-map return-value]
                       (case requestid

                         :SNAPSHOT
                         [this-map this-map]

                         :PUSH-REQUEST-PORT
                         (if (:INVARIANT this-descriptors)
                           [this-map this-map]
                           (let [new-request-port
                                 (:new-request-port params)
                                 saved-map
                                 this-map
                                 this-map
                                 (assoc this-map :REQUEST-PORT-STACK (conj this-request-port-stack new-request-port))]
                             [this-map saved-map]))

                         :POP-REQUEST-PORT
                         (if (:INVARIANT this-descriptors)
                           [this-map this-map]
                           (let [this-map
                                 (assoc this-map :REQUEST-PORT-STACK (pop this-request-port-stack))]
                             [this-map this-map]))

                         ;;DEFAULT
                         (let [operationid
                               (requestid this-requestid-map)
                               _ (if (nil? operationid)
                                   (throw (Exception. (str "Operationid is nil\n"
                                                           (prn-str params)
                                                           (prn-str this-map)))))
                               operation-port
                               (first (operationid @operationid-map-atom))
                               operation-return-port
                               (a/chan)
                               _ (if (nil? operationid)
                                   (throw (Exception. (str "Operationid is nil\n"
                                                           (prn-str params)
                                                           (prn-str this-map)))))
                               _ (if (nil? operation-port)
                                   (throw (Exception. (str "Operation port is nil\n"
                                                           (prn-str params)
                                                           (prn-str this-map)))))
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
        invariant
        (:INVARIANT descriptors)
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
         :CHILDVECTORS       {}
         :PARENTVECTORS      {}
         :REQUEST-PORT-STACK request-port-stack}
        ]
    (create-operation-dispatcher new-entity-map)
    new-public-request-port))
