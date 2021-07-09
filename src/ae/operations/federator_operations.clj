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
            root-contexts-request-port
            (:CONTEXT-REQUEST-PORT env)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)
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
            (a/<! subrequest-return-port)
            ]
        (println (prn-str :??? federation-context-request-port))
        (a/>! operation-return-port [this-map
                                     nil
                                     this-map]))
      (recur))))

(defn create-federator-operations
  [env]
  (create-run-federation-operation env))
