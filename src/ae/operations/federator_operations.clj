(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
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
                      _ (a/>! initialization-port [env {"requestid"   "RESET-REQUEST-PORT"
                                                        "this-map"    snap
                                                        "return_port" subrequest-return-port}])
                      _ (k/request-exception-check (a/<! subrequest-return-port))
                      context-request-port
                      (get env "CONTEXT-REQUEST-PORT")
                      _ (a/>! context-request-port [env {"requestid"                  "SYS+requestid-ROUTE"
                                                         "target_requestid"           "SYS+requestid-REGISTER_ENTITY"
                                                         "entity-public-request-port" entity-public-request-port
                                                         "target_name"                context-name
                                                         "name"                       (get snap "NAME")
                                                         "classifiers"                (get snap "CLASSIFIERS")
                                                         "return_port"                subrequest-return-port}])
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

(defn run-federation-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "operation-return-port")]
      (try
        (let [root-contexts-request-port
              (get env "CONTEXT-REQUEST-PORT")
              this-name
              (get this-map "NAME")
              descriptors
              (get this-map "DESCRIPTORS")
              federation-names
              (get descriptors "SYS+descriptor-FEDERATION_NAMES")
              env
              (assoc env "FEDERATION-NAMES" federation-names)
              subrequest-return-port
              (a/chan)
              _ (a/>! root-contexts-request-port [env {"requestid"        "SYS+requestid-ROUTE"
                                                       "target_requestid" "SYS+requestid-INSTANTIATE"
                                                       "target_name"      "SYS+instantiator-FEDERATION_CONTEXT"
                                                       "return_port"      subrequest-return-port
                                                       "name"             nil}])
              federation-context-request-port
              (k/request-exception-check (a/<! subrequest-return-port))
              env
              (assoc env "FEDERATOR-NAME" this-name)
              _ (a/>! federation-context-request-port [env {"requestid"        "SYS+requestid-ACQUIRE"
                                                            "return_port"      subrequest-return-port}])
              _ (k/request-exception-check (a/<! subrequest-return-port))
              env
              (assoc env "NEW-CHILDREN-VOLATILE" (volatile! {}))
              script
              (get descriptors "SYS+descriptor-SCRIPT$yaml")
              this-name
              (get this-map "NAME")
              [_ local-context _]
              (kw/name-as-keyword this-name)
              _ (doseq [request script]
                  (let [
                        request
                        (reduce
                          (fn [request [k v]]
                            (into request {k
                                           (k/bind-context local-context v (some? (s/index-of k "$")) env)}))
                          {}
                          request)]
                    (k/routeFunction env this-map request)))
#_              [e federation-map]
#_              (a/<! (registerChildren env
                                      federation-map
                                      @(get env "NEW-CHILDREN-VOLATILE")))
;              _ (if (some? e)
;                  (throw e))
              env
              (assoc env "FEDERATOR-NAME" nil)
              env
              (assoc env "NEW-CHILDREN-VOLATILE" nil)
              _ (a/>! federation-context-request-port [env {"requestid"   "SYS+requestid-RELEASE"
                                                            "return_port" subrequest-return-port}])
              _ (k/request-exception-check (a/<! subrequest-return-port))
              _ (a/>! operation-return-port [this-map
                                             nil
                                             this-map])
              ])
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn create-federator-operations
  [env]
  (k/register-function env {:operationid "RUN_FEDERATIONoperationid"
                            :goblock     run-federation-goblock})
  )
