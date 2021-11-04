(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn registerChildren
  [env new-children]
  (let [return-port
        (a/chan)]
    (a/go-loop [new-children new-children]
      (if (some? new-children)
        (if (empty? new-children)
          (a/>! return-port [nil])
          (recur
            (try
              (let [child-names
                    (keys new-children)
                    entity-name
                    (first child-names)
                    context-name
                    (k/entityContextName entity-name)
                    snap
                    (k/get-entity-map entity-name)
                    request-port-stack
                    (get snap "REQUEST-PORT-STACK")
                    initialization-port
                    (peek request-port-stack)
                    entity-public-request-port
                    (get new-children entity-name)
                    subrequest-return-port
                    (a/chan)
                    _ (a/>! initialization-port [env {"requestid"   "RESET-REQUEST-PORT"
                                                      "return_port" subrequest-return-port}])
                    _ (k/request-exception-check (a/<! subrequest-return-port))
                    context-request-port
                    (get env "CONTEXT-REQUEST-PORT")
                    _ (a/>! context-request-port [env {"requestid"                  "SYS+requestid-ROUTE"
                                                       "param-TARGETrequestid"           "SYS+requestid-REGISTERentity"
                                                       "entity-public-request-port" entity-public-request-port
                                                       "param-TARGETname"                context-name
                                                       "name"                       (get snap "NAME")
                                                       "classifiers"                (get snap "CLASSIFIERS")
                                                       "return_port"                subrequest-return-port}])
                    _ (k/request-exception-check (a/<! subrequest-return-port))
                    new-children
                    (dissoc new-children entity-name)]
                new-children)
              (catch Exception e
                (a/>! return-port [e])
                nil))))))
    return-port))

(defn federation-acquire
  [root-contexts-request-port federation-names env]
  (let [return-port
        (a/chan)]
    (a/go-loop [federation-names-vec (reverse (sort federation-names))]
      (if (some? federation-names-vec)
        (if (empty? federation-names-vec)
          (a/>! return-port [nil nil])
          (let [federation-name
                (peek federation-names-vec)
                federation-names-vec
                (pop federation-names-vec)
                federation-names-vec
                (try
                  (let [new-request-port
                        (a/chan)
                        subrequest-return-port
                        (a/chan)
                        _ (a/>! root-contexts-request-port [env {"requestid"        "SYS+requestid-ROUTE"
                                                                 "param-TARGETrequestid" "PUSH-REQUEST-PORT"
                                                                 "param-TARGETname"      federation-name
                                                                 "new-request-port" new-request-port
                                                                 "return_port"      subrequest-return-port}])
                        [snap new-request-port]
                        (k/request-exception-check (a/<! subrequest-return-port))]
                    federation-names-vec)
                  (catch Exception e
                    (a/>! return-port [e nil])
                    nil))]
            (recur federation-names-vec)))))
    return-port))

(defn federation-release
  [env federation-names]
  (let [return-port
        (a/chan)]
    (a/go
      (try
        (doseq [entity-name federation-names]
          (let [entity-map
                (k/get-entity-map entity-name)
                request-port-stack
                (get entity-map "REQUEST-PORT-STACK")
                request-port
                (peek request-port-stack)
                sub-return-port
                (a/chan)]
            (a/>! request-port [env {"requestid" "RESET-REQUEST-PORT"
                                     "return_port" sub-return-port}])
            (k/request-exception-check (a/<! sub-return-port))))
        (a/>! return-port [nil])
        (catch Exception e
          (a/>! return-port [e]))))
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
              (get descriptors "SYS+descriptor_vec-FEDERATIONnames")
              env
              (assoc env "FEDERATOR-NAME" this-name)
              root-contexts-request-port
              (get env "CONTEXT-REQUEST-PORT")
              acquire-port
              (federation-acquire root-contexts-request-port federation-names env)
              _ (k/request-exception-check (a/<! acquire-port))
              env
              (assoc env "NEW-CHILDREN-VOLATILE" (volatile! {}))
              script
              (get descriptors "SYS+descriptor-SCRIPT$yaml")
              this-name
              (get this-map "NAME")
              local-context
              (k/entityContextName this-name)
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
              [e]
              (a/<! (registerChildren env
                                      @(get env "NEW-CHILDREN-VOLATILE")))
              _ (if (some? e)
                  (throw e))
              env
              (assoc env "FEDERATOR-NAME" nil)
              env
              (assoc env "NEW-CHILDREN-VOLATILE" nil)
              sub-return-port
              (federation-release env federation-names)
              [e]
              (a/<! sub-return-port)
              _ (if (some? e)
                  (throw e))
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
