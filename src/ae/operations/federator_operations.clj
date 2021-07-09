(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-run-federation-operation
  [env]
  (let [new-run-federation-port
        (k/register-operation-port env {:operation-portid :RUN-FEDERATION-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! new-run-federation-port)
            this-map
            (:this-map env)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [root-contexts-request-port
                (:CONTEXT-REQUEST-PORT env)
                descriptors
                (:DESCRIPTORS this-map)
                federated-entity-names
                (:FEDERATED-ENTITY-NAMES descriptors)
                script
                (:SCRIPT descriptors)
                subrequest-return-port
                (a/chan)
                _ (a/>! root-contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                         :target-requestid :INSTANTIATE-REQUESTID
                                                         :target-name      "CONTEXTS/FEDERATION-CONTEXT-PROTOTYPE"
                                                         :return-port      subrequest-return-port
                                                         :name             "CONTEXTS/FEDERATION-CONTEXT_1"}])
                federation-context-request-port
                (k/request-exception-check (a/<! subrequest-return-port))
                _ (a/>! federation-context-request-port [env {:requestid   :SNAPSHOT
                                                              :return-port subrequest-return-port}])
                federation-context-snap
                (k/request-exception-check (a/<! subrequest-return-port))
                _ (println (prn-str :??? federation-context-snap))
                _ (a/>! operation-return-port [this-map
                                               nil
                                               this-map])])
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-federator-operations
  [env]
  (create-run-federation-operation env))
