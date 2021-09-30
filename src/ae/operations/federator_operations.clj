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
                  (get env "CONTEXT-REQUEST-PORT")
                  subrequest-return-port
                  (a/chan)
                  new-classifiers
                  (pop new-classifiers)]
              (when (not (contains? new-children entity-name))
                (a/>! context-request-port [env {"requestid"        "SYS+requestid-ROUTE"
                                                 "target_requestid" "SYS+requestid-REGISTER_CLASSIFIER"
                                                 "target_name"      context-name
                                                 "name"             entity-name
                                                 "classifier"       classifier
                                                 "classifier-value" classifier-value
                                                 "return_port"      subrequest-return-port}])
                (k/request-exception-check (a/<! subrequest-return-port)))
              new-classifiers)
            (catch Exception e
              (a/>! return-port [e])
              nil)))))
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
              subrequest-return-port
              (a/chan)
              _ (a/>! root-contexts-request-port [env {"requestid"        "SYS+requestid-ROUTE"
                                                       "target_requestid" "SYS+requestid-INSTANTIATE"
                                                       "target_name"      "SYS+instantiator-FEDERATION_CONTEXT"
                                                       "return_port"      subrequest-return-port
                                                       "name"             nil}])
              federation-context-request-port
              (k/request-exception-check (a/<! subrequest-return-port))
              _ (a/>! federation-context-request-port [env {"requestid"       "SYS+requestid-ACQUIRE"
                                                            :federation-names federation-names
                                                            "return_port"     subrequest-return-port}])
              federation-map
              (k/request-exception-check (a/<! subrequest-return-port))
              federation-vmap
              (volatile! (reduce
                           (fn [federation-vmap [k [snap active-port]]]
                             (assoc federation-vmap k [(volatile! snap) active-port]))
                           {}
                           federation-map))
              env
              (assoc env "FEDERATION-MAP-VOLATILE" federation-vmap)
              env
              (assoc env "FEDERATOR-NAME" this-name)
              env
              (assoc env "NEW-CHILDREN-VOLATILE" (volatile! {}))
              env
              (assoc env :NEW-CLASSIFIERS-VOLATILE (volatile! []))
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
              federation-vmap
              (get env "FEDERATION-MAP-VOLATILE")
              federation-map
              (reduce
                (fn [federation-map [k [vsnap active-port]]]
                  (assoc federation-map k [@vsnap active-port]))
                {}
                @federation-vmap)
              [e federation-map]
              (a/<! (registerChildren env
                                      federation-map
                                      @(get env "NEW-CHILDREN-VOLATILE")))
              _ (if (some? e)
                  (throw e))
              [e]
              (a/<! (registerClassifiers env
                                         @(:NEW-CLASSIFIERS-VOLATILE env)
                                         @(get env "NEW-CHILDREN-VOLATILE")
                                         ))
              _ (if (some? e)
                  (throw e))
              env
              (assoc env "FEDERATION-MAP" federation-map)
              env
              (assoc env "FEDERATION-MAP-VOLATILE" nil)
              env
              (assoc env "FEDERATOR-NAME" nil)
              env
              (assoc env "NEW-CHILDREN-VOLATILE" nil)
              env
              (assoc env :NEW-CLASSIFIERS-VOLATILE nil)
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
