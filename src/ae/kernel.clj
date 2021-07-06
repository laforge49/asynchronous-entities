(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.stacktrace :as stacktrace]
            [ae.keywords :as kw]))

(defn request-exception-check
  [return-value]
  (if (not (vector? return-value))
    (throw (Exception. (prn-str "Return value is not a vector " return-value)))
    (let [[ex val]
          return-value]
      (if (some? ex)
        (throw ex)
        val))))

(defn operation-exception-check
  [return-value]
  (if (not (vector? return-value))
    (throw (Exception. (prn-str "Return value is not a vector " return-value)))
    (let [[ex val]
          return-value]
      (if (some? ex)
        (throw ex)
        val))))

(defn volatile-map
  [entity]
  (second entity))

(def operation-port-map-atom
  (atom {}))

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
    (let [this-request-port-stack
          (:REQUEST-PORT-STACK this-map)
          this-request-port
          (peek this-request-port-stack)
          env-params
          (a/<! this-request-port)
          _ (if (not (vector? env-params))
              (stacktrace/print-stack-trace (Exception. (prn-str "Request is not a vector: " env-params))))
          [env params]
          env-params
          return-port
          (:return-port params)
          descriptors
          (:DESCRIPTORS this-map)
          this-operation-portid-map
          (:OPERATION-PORTIDS descriptors)
          env
          (assoc env :this-map this-map)
          requestid
          (:requestid params)
          operation-port-id
          (requestid this-operation-portid-map)
          [this-map return-value]
          (case requestid
            :SNAPSHOT
            [this-map this-map]
            :PUSH-REQUEST-PORT
            (let [new-request-port
                  (:new-request-port params)
                  federated-entity-request-ports
                  (:federated-entity-request-ports params)
                  saved-map
                  this-map
                  this-map
                  (assoc this-map :REQUEST-PORT-STACK (conj this-request-port-stack new-request-port))
                  this-map
                  (assoc this-map :FEDERATED-ENTITY-REQUEST-PORTS federated-entity-request-ports)]
              [this-map saved-map])
            :POP-REQUEST-PORT
            (let [this-map
                  (assoc this-map :REQUEST-PORT-STACK (pop this-request-port-stack))]
              [this-map this-map])
            :RESET
            (let [this-map
                  (:saved-map params)]
              [this-map this-map])
            (let [operation-port
                  (operation-port-id @operation-port-map-atom)
                  operation-return-port
                  (a/chan)]
              (a/>! operation-port [env (assoc params :operation-return-port operation-return-port)])
              (try
                (operation-exception-check (a/<! operation-return-port))
                (catch Exception e
                  (a/>! return-port [e nil])))))]
      (if (not= return-value :NO-RETURN)
        (a/>! return-port [nil return-value]))
      (recur this-map))))

(defn create-entity
  [env params]
  (let [new-request-port
        (a/chan)
        request-port-stack
        [new-request-port]
        initialization-port
        (:initialization-port params)
        request-port-stack
        (if (nil? initialization-port)
          request-port-stack
          (conj request-port-stack initialization-port))
        new-entity-map
        {:NAME               (:name params)
         :DESCRIPTORS        (:descriptors params)
         :CLASSIFIERS        (:classifiers params)
         :CHILDVECTORS       {}
         :PARENTVECTORS      {}
         :REQUEST-PORT-STACK request-port-stack}
        ]
    (create-operation-dispatcher new-entity-map)
    new-request-port))
