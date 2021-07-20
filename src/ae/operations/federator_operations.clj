(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-run-federation-operation
  [env]
  (let [new-run-federation-port
        (k/register-operation env {:operationid :RUN-FEDERATION-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! new-run-federation-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [root-contexts-request-port
                (:CONTEXT-REQUEST-PORT env)
                descriptors
                (:DESCRIPTORS this-map)
                federation-names
                (:FEDERATION-NAMES descriptors)
                subrequest-return-port
                (a/chan)
                _ (a/>! root-contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                         :target-requestid :INSTANTIATE-REQUESTID
                                                         :target-name      "CONTEXTS/FEDERATION-CONTEXT-PROTOTYPE"
                                                         :return-port      subrequest-return-port
                                                         :name             nil}])
                federation-context-request-port
                (k/request-exception-check (a/<! subrequest-return-port))
                _ (a/>! federation-context-request-port [env {:requestid        :ACQUIRE-REQUESTID
                                                              :federation-names federation-names
                                                              :return-port      subrequest-return-port}])
                federation-map
                (k/request-exception-check (a/<! subrequest-return-port))
                env
                (assoc env :FEDERATION-MAP-VOLATILE (volatile! federation-map))
                env
                (assoc env :NEW-CHILDREN-VOLATILE (volatile! {}))
                env
                (assoc env :CONTEXT-REQUEST-PORT federation-context-request-port)
                script
                (:SCRIPT descriptors)
                _ (doseq [script-item script]
                    (k/federationRouteFunction env this-map script-item))
                env
                (assoc env :CONTEXT-REQUEST-PORT root-contexts-request-port)
                _ (a/>! federation-context-request-port [env {:requestid   :RELEASE-REQUESTID
                                                              :return-port subrequest-return-port}])
                _ (k/request-exception-check (a/<! subrequest-return-port))
                _ (a/>! federation-context-request-port [env {:requestid   :SNAPSHOT
                                                              :return-port subrequest-return-port}])
                federation-context-snap
                (k/request-exception-check (a/<! subrequest-return-port))
                _ (a/>! operation-return-port [this-map
                                               nil
                                               this-map])])
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-federator-operations
  [env]
  (create-run-federation-operation env))
