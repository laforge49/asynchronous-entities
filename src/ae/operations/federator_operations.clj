(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn registerChildren
  [env federation-map new-children]
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
                      entity-name
                      (first child-names)
                      context-name
                      (k/entityContextName entity-name)
                      [snap initialization-port]
                      (get federation-map entity-name)
                      entity-public-request-port
                      (get new-children entity-name)
                      subrequest-return-port
                      (a/chan)
                      _ (a/>! initialization-port [env {:requestid   :RESET-REQUEST-PORT
                                                        :this-map    snap
                                                        :return_port subrequest-return-port}])
                      _ (k/request-exception-check (a/<! subrequest-return-port))
                      context-request-port
                      (:CONTEXT-REQUEST-PORT env)
                      _ (a/>! context-request-port [env {:requestid                  :SYSTEMcontext/ROUTE_REQUESTID
                                                         :target_requestid           :SYSTEMcontext/REGISTER_ENTITY_REQUESTID
                                                         :entity-public-request-port entity-public-request-port
                                                         :target_name                context-name
                                                         :name                       (:NAME snap)
                                                         :classifiers                (:CLASSIFIERS snap)
                                                         :return_port                subrequest-return-port}])
                      _ (k/request-exception-check (a/<! subrequest-return-port))
                      federation-map
                      (dissoc federation-map entity-name)
                      new-children
                      (dissoc new-children entity-name)]
                  [federation-map new-children])
                (catch Exception e
                  (a/>! return-port [e nil])
                  nil)))))))
    return-port))

(defn registerClassifiers
  [env new-classifiers new-children]
  (let [return-port
        (a/chan)]
    (a/go-loop [new-classifiers new-classifiers]
      (if (empty? new-classifiers)
        (a/>! return-port [nil])
        (recur
          (try
            (let [[entity-name classifier classifier-value]
                  (peek new-classifiers)
                  context-name
                  (k/entityContextName entity-name)
                  context-request-port
                  (:CONTEXT-REQUEST-PORT env)
                  subrequest-return-port
                  (a/chan)
                  new-classifiers
                  (pop new-classifiers)]
              (when (not (contains? new-children entity-name))
                (a/>! context-request-port [env {:requestid        :SYSTEMcontext/ROUTE_REQUESTID
                                                 :target_requestid :SYSTEMcontext/REGISTER_CLASSIFIER_REQUESTID
                                                 :target_name      context-name
                                                 :name             entity-name
                                                 :classifier       classifier
                                                 :classifier-value classifier-value
                                                 :return_port      subrequest-return-port}])
                (k/request-exception-check (a/<! subrequest-return-port)))
              new-classifiers)
            (catch Exception e
              (a/>! return-port [e])
              nil)))))
    return-port))

(defn create-run-federation-operation
  [env]
  (let [new-run-federation-port
        (k/register-operation env {:operationid :RUN_FEDERATION_OPERATIONID})]
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
                (:SYSTEMcontext/FEDERATION_NAMES descriptors)
                subrequest-return-port
                (a/chan)
                _ (a/>! root-contexts-request-port [env {:requestid        :SYSTEMcontext/ROUTE_REQUESTID
                                                         :target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
                                                         :target_name      "SYSTEMcontext+FEDERATION_CONTEXT_INSTANTIATOR"
                                                         :return_port      subrequest-return-port
                                                         :name             nil}])
                federation-context-request-port
                (k/request-exception-check (a/<! subrequest-return-port))
                _ (a/>! federation-context-request-port [env {:requestid        :SYSTEMcontext/ACQUIRE_REQUESTID
                                                              :federation-names federation-names
                                                              :return_port      subrequest-return-port}])
                federation-map
                (k/request-exception-check (a/<! subrequest-return-port))
                federation-vmap
                (volatile! (reduce
                             (fn [federation-vmap [k [snap active-port]]]
                               (assoc federation-vmap k [(volatile! snap) active-port]))
                             {}
                             federation-map))
                env
                (assoc env :FEDERATION-MAP-VOLATILE federation-vmap)
                env
                (assoc env :NEW-CHILDREN-VOLATILE (volatile! {}))
                env
                (assoc env :NEW-CLASSIFIERS-VOLATILE (volatile! []))
                script
                (:SYSTEMcontext/SCRIPT descriptors)
                _ (doseq [script-item script]
                    (k/federationRouteFunction env this-map script-item))
                federation-vmap
                (:FEDERATION-MAP-VOLATILE env)
                federation-map
                (reduce
                  (fn [federation-map [k [vsnap active-port]]]
                    (assoc federation-map k [@vsnap active-port]))
                  {}
                  @federation-vmap)
                [e federation-map]
                (a/<! (registerChildren env
                                        federation-map
                                        @(:NEW-CHILDREN-VOLATILE env)))
                _ (if (some? e)
                    (throw e))
                [e]
                (a/<! (registerClassifiers env
                                           @(:NEW-CLASSIFIERS-VOLATILE env)
                                           @(:NEW-CHILDREN-VOLATILE env)
                                           ))
                _ (if (some? e)
                    (throw e))
                env
                (assoc env :FEDERATION-MAP federation-map)
                env
                (assoc env :FEDERATION-MAP-VOLATILE nil)
                env
                (assoc env :NEW-CHILDREN-VOLATILE nil)
                env
                (assoc env :NEW-CLASSIFIERS-VOLATILE nil)
                _ (a/>! federation-context-request-port [env {:requestid   :SYSTEMcontext/RELEASE_REQUESTID
                                                              :return_port subrequest-return-port}])
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
