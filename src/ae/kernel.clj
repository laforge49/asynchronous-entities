(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [ae.keywords :as kw]))

(def operation-port-map-atom
  (atom {}))

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

(defn register-operation-port
  [env params]
  (let [operation-portid
        (:operation-portid params)
        port
        (a/chan)]
    (swap! operation-port-map-atom assoc operation-portid port)
    port))

(defn create-operation-dispatcher
  [this-map]
  (a/go-loop [this-map this-map]
    (recur (try
             (let [this-request-port-stack
                   (:REQUEST-PORT-STACK this-map)
                   _ (if (nil? this-request-port-stack)
                       (throw (Exception. "This request port stack is nil")))
                   this-request-port
                   (peek this-request-port-stack)
                   _ (if (nil? this-request-port)
                       (throw (Exception. "This request port is nil")))
                   request
                   (a/<! this-request-port)
                   _ (if (not (vector? request))
                       (throw (Exception. (prn-str "Request is not a vector: " request))))
                   [env params]
                   request
                   return-port
                   (:return-port params)
                   _ (if (nil? return-port)
                       (throw (Exception. (prn-str "Return port is nil for params: " params))))]
               (try
                 (let [env
                       (assoc env :this-map this-map)
                       env
                       (assoc env :active-request-port this-request-port)
                       requestid
                       (:requestid params)
                       _ (if (nil? requestid)
                           (throw (Exception. (prn-str "Requestid port is nil for params: " params))))
                       this-descriptors
                       (:DESCRIPTORS this-map)
                       _ (if (nil? this-descriptors)
                           (throw (Exception. (prn-str "Descriptors is nil"))))
                       this-operation-portids
                       (:OPERATION-PORTIDS this-descriptors)
                       _ (if (nil? this-operation-portids)
                           (throw (Exception. (prn-str "Operation portids is nil"))))
                       [this-map return-value]
                       (case requestid

                         :SNAPSHOT
                         [this-map this-map]

                         :PUSH-REQUEST-PORT
                         (let [new-request-port
                               (:new-request-port params)
                               saved-map
                               this-map
                               this-map
                               (assoc this-map :REQUEST-PORT-STACK (conj this-request-port-stack new-request-port))]
                           [this-map saved-map])

                         :POP-REQUEST-PORT
                         (let [this-map
                               (assoc this-map :REQUEST-PORT-STACK (pop this-request-port-stack))]
                           [this-map this-map])

                         :RESET
                         (let [this-map
                               (:saved-map params)]
                           [this-map this-map])

                         ;;DEFAULT
                         (let [operation-port-id
                               (requestid this-operation-portids)
                               _ (if (nil? operation-port-id)
                                   (throw (Exception. (prn-str "Operation portid is nil for this-map, params:" this-map params))))
                               operation-port
                               (operation-port-id @operation-port-map-atom)
                               operation-return-port
                               (a/chan)
                               _ (if (nil? operation-port-id)
                                   (throw (Exception. (str "Operation port id is nil for params " params))))
                               _ (if (nil? operation-port)
                                   (throw (Exception. (str "Operation port is nil for params " params))))
                               _ (a/>! operation-port [env (assoc params :operation-return-port operation-return-port)])
                               operation-return-value
                               (a/<! operation-return-port)
                               _ (if (not (vector? operation-return-value))
                                   (throw (Exception. (str "Operation return value is not a vector: " operation-return-value))))
                               _ (if (not= (count operation-return-value) 3)
                                   (throw (Exception. (str "Operation return value is not a 3-tuple: " operation-return-value))))]
                           operation-return-value)

                         )]
                   (a/>! return-port [nil return-value])
                   this-map)
                 (catch Exception e
                   (a/>! return-port [e nil])
                   this-map)))
             (catch Exception e
               (stacktrace/print-stack-trace e)
               this-map)))))

(defn create-operation-dispatcherX
  [this-map]
  (a/go-loop [this-map this-map]
    (let [this-request-port-stack
          (:REQUEST-PORT-STACK this-map)
          this-request-port
          (peek this-request-port-stack)]
      (if (nil? this-request-port-stack)
        (stacktrace/print-stack-trace (Exception. "This request port stack is nil"))
        (if (nil? this-request-port)
          (stacktrace/print-stack-trace (Exception. "This request port is nil"))
          (let [request
                (a/<! this-request-port)
                _ (if (not (vector? request))
                    (stacktrace/print-stack-trace (Exception. (prn-str "Request is not a vector: " request))))
                [env params]
                request
                return-port
                (:return-port params)
                _ (if (nil? return-port)
                    (stacktrace/print-stack-trace (Exception. (prn-str "Return port is nil for params: " params))))
                this-descriptors
                (:DESCRIPTORS this-map)
                this-operation-portids
                (:OPERATION-PORTIDS this-descriptors)
                env
                (assoc env :this-map this-map)
                env
                (assoc env :active-request-port this-request-port)
                requestid
                (:requestid params)
                [this-map e return-value]
                (case requestid
                  :SNAPSHOT
                  [this-map nil this-map]
                  :PUSH-REQUEST-PORT
                  (let [new-request-port
                        (:new-request-port params)
                        saved-map
                        this-map
                        this-map
                        (assoc this-map :REQUEST-PORT-STACK (conj this-request-port-stack new-request-port))]
                    [this-map nil saved-map])
                  :POP-REQUEST-PORT
                  (let [this-map
                        (assoc this-map :REQUEST-PORT-STACK (pop this-request-port-stack))]
                    [this-map nil this-map])
                  :RESET
                  (let [this-map
                        (:saved-map params)]
                    [this-map nil this-map])
                  ;;DEFAULT
                  (let [
                        operation-port-id
                        (requestid this-operation-portids)
                        _ (if (nil? operation-port-id)
                            (throw (Exception. (prn-str "Operation portid is nil for this-map, params:" this-map params))))
                        operation-port
                        (operation-port-id @operation-port-map-atom)
                        operation-return-port
                        (a/chan)

                        #_(println (prn-str :???? params))

                        [this-map e return-value]
                        (if (nil? operation-port-id)
                          [this-map
                           (Exception. (str "Operation port id is nil for params " params))
                           nil]
                          (if (nil? operation-port)
                            [this-map
                             (Exception. (str "Operation port is nil for params " params))
                             nil]
                            (let [- (a/>! operation-port [env (assoc params :operation-return-port operation-return-port)])
                                  operation-return-value
                                  (a/<! operation-return-port)]
                              (if (not (vector? operation-return-value))
                                [this-map
                                 (Exception. (str "Operation return value is not a vector: " operation-return-value))
                                 nil]
                                (if (not= (count operation-return-value) 3)
                                  [this-map
                                   (Exception. (str "Operation return value is not a 3-tuple: " operation-return-value))
                                   nil]
                                  operation-return-value)))))]
                    [this-map e return-value])

                  )]
            (if (some? e)
              (a/>! return-port [e nil])
              (if (not= return-value :NO-RETURN)
                (a/>! return-port [nil return-value])))
            (recur this-map)))))))

(defn create-entity
  [env params]
  (let [new-public-request-port
        (a/chan)
        request-port-stack
        [new-public-request-port]
        descriptors
        (:descriptors params)
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
