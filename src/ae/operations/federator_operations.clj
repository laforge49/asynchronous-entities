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
                    (get snap "SYS+facet_vec-REQUESTportSTACK$chan")
                    initialization-port
                    (peek request-port-stack)
                    entity-public-request-port
                    (get new-children entity-name)
                    subrequest-return-port
                    (a/chan)
                    _ (a/>! initialization-port [env {"SYS+param-REQUESTID"   "RESET-REQUEST-PORT"
                                                      "SYS+param-RETURN$chan" subrequest-return-port}])
                    _ (k/request-exception-check (a/<! subrequest-return-port))
                    context-request-port
                    (k/get-sys-request-port)
                    _ (a/>! context-request-port [env {"SYS+param-REQUESTID"                  "SYS+requestid-ROUTE"
                                                       "SYS+param-TARGET&requestid"           "SYS+requestid-REGISTERentity"
                                                       "SYS+param-ENTITYpublicREQUESTPORT"    entity-public-request-port
                                                       "SYS+param-TARGETname&?"               context-name
                                                       "SYS+param-NAME&?"                     (get snap "SYS+facet-NAME&?")
                                                       "SYS+param_map-CLASSIFIERS^classifier" (get snap "SYS+facet_map-CLASSIFIERS^classifier")
                                                       "SYS+param-RETURN$chan"                subrequest-return-port}])
                    _ (k/request-exception-check (a/<! subrequest-return-port))
                    new-children
                    (dissoc new-children entity-name)]
                new-children)
              (catch Exception e
                (a/>! return-port [e])
                nil))))))
    return-port))

(defn federation-acquire
  [federation-names env]
  (let [return-port
        (a/chan)]
    (a/go-loop [federation-names-vec (reverse (sort federation-names))]
      (if (some? federation-names-vec)
        (if (empty? federation-names-vec)
          (a/>! return-port [nil nil])
          (let [federation-name
                (peek federation-names-vec)
                federation-request-port
                (k/get-public-request-port federation-name)
                federation-names-vec
                (pop federation-names-vec)
                federation-names-vec
                (try
                  (let [new-request-port
                        (a/chan)
                        subrequest-return-port
                        (a/chan)
                        _ (a/>! federation-request-port [env {"SYS+param-REQUESTID"      "PUSH-REQUEST-PORT"
                                                              "SYS+param-NEWrequestport" new-request-port
                                                              "SYS+param-RETURN$chan"    subrequest-return-port}])
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
                (get entity-map "SYS+facet_vec-REQUESTportSTACK$chan")
                request-port
                (peek request-port-stack)
                sub-return-port
                (a/chan)]
            (a/>! request-port [env {"SYS+param-REQUESTID"   "RESET-REQUEST-PORT"
                                     "SYS+param-RETURN$chan" sub-return-port}])
            (k/request-exception-check (a/<! sub-return-port))))
        (a/>! return-port [nil])
        (catch Exception e
          (a/>! return-port [e]))))
    return-port))

(defn run-federation-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [root-contexts-request-port
              (k/get-sys-request-port)
              this-name
              (get this-map "SYS+facet-NAME&?")
              descriptors
              (get this-map "SYS+facet_map-DESCRIPTORS^descriptor")
              federation-names
              (get descriptors "SYS+descriptor_vec-FEDERATIONnames&?")
              env
              (assoc env "SYS+facet-FEDERATORname&federator" this-name)
              acquire-port
              (federation-acquire federation-names env)
              _ (k/request-exception-check (a/<! acquire-port))
              env
              (assoc env "NEW-CHILDREN-VOLATILE" (volatile! {}))
              script
              (get descriptors "SYS+descriptor_vecmap-SCRIPT^request")
              this-name
              (get this-map "SYS+facet-NAME&?")
              local-context
              (k/entityContextName this-name)
              _ (doseq [request script]
                  (let [request-params
                        (val (first request))
                        request-params
                        (k/bind-context local-context request-params "map" "param" nil nil env)]
                    (k/routeFunction env this-map request-params)))
              [e]
              (a/<! (registerChildren env
                                      @(get env "NEW-CHILDREN-VOLATILE")))
              _ (if (some? e)
                  (throw e))
              env
              (assoc env "SYS+facet-FEDERATORname&federator" nil)
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
