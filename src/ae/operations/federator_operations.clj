(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn registerChildren
  [federation-map new-children]
  (let [return-port
        (a/chan)]
    (a/go-loop [lvec [federation-map new-children]]
      (if (some? lvec)
        (let [[federation-map new-children]
              lvec]
          (if (empty? new-children)
            (a/>! return-port [nil federation-map])
            (recur
              (try
                (let [child-names
                      (keys new-children)
                      child-name
                      (first child-names)
                      [vsnap initialization-port]
                      (get federation-map child-name)
                      new-entity-public-request-port
                      (get new-children child-name)

                      federation-map
                      (dissoc federation-map child-name)
                      new-children
                      (dissoc new-children child-name)]
                  [federation-map new-children])
                (catch Exception e
                  (a/>! return-port [e nil])
                  nil)))))))
    return-port))

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
                federation-vmap
                (reduce
                  (fn [federation-vmap [k [snap active-port]]]
                       (assoc federation-vmap k [(volatile! snap) active-port]))
                  {}
                  federation-map)
                env
                (assoc env :FEDERATION-MAP-VOLATILE (volatile! federation-vmap))
                env
                (assoc env :NEW-CHILDREN-VOLATILE (volatile! {}))
                script
                (:SCRIPT descriptors)
                _ (doseq [script-item script]
                    (k/federationRouteFunction env this-map script-item))
                [e federation-vmap]
                (a/<! (registerChildren @(:FEDERATION-MAP-VOLATILE env)
                                        @(:NEW-CHILDREN-VOLATILE env)))
                _ (if (some? e)
                    (throw e))
                env
                (assoc env :FEDERATION-MAP federation-vmap)
                env
                (assoc env :FEDERATION-MAP-VOLATILE nil)
                env
                (assoc env :NEW-CHILDREN-VOLATILE nil)
                _ (a/>! federation-context-request-port [env {:requestid   :RELEASE-REQUESTID
                                                              :return-port subrequest-return-port}])
                _ (k/request-exception-check (a/<! subrequest-return-port))
                _ (a/>! operation-return-port [this-map
                                               nil
                                               this-map])
                ])
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-federator-operations
  [env]
  (create-run-federation-operation env))
