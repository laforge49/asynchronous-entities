(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.transform :as t]))

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
                    context-request-port
                    (k/get-public-request-port context-name)
                    _ (if (nil? context-request-port)
                        (throw (Exception. (str "Missing request port on " context-name))))
                    initialization-port
                    (peek (k/get-request-port-stack entity-name))
                    subrequest-return-port
                    (a/chan)
                    _ (a/>! initialization-port [env {"SYS+param-REQUESTID&requestid" "RESET-REQUEST-PORT"
                                                      "SYS+param-RETURN$chan"         subrequest-return-port}])
                    _ (k/request-exception-check (a/<! subrequest-return-port))
                    _ (a/>! context-request-port [env {"SYS+param-REQUESTID&requestid"        "SYS+requestid-REGISTERgem"
                                                       "SYS+param-NAME&%"                     entity-name
                                                       "SYS+param-CLASSIFIERS_map^classifier" (k/get-classifiers-map entity-name)
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
          (a/>! return-port [nil])
          (let [federation-name
                (peek federation-names-vec)
                federation-request-port
                (k/get-public-request-port federation-name)
                _ (if (nil? federation-request-port)
                    (throw (Exception. (str "Missing request port while federating on gem " federation-name))))
                federation-names-vec
                (pop federation-names-vec)
                federation-names-vec
                (try
                  (let [new-request-port
                        (a/chan)
                        subrequest-return-port
                        (a/chan)
                        _ (a/>! federation-request-port [env {"SYS+param-REQUESTID&requestid" "PUSH-REQUEST-PORT"
                                                              "SYS+param-NEWrequestport"      new-request-port
                                                              "SYS+param-RETURN$chan"         subrequest-return-port}])
                        _ (k/request-exception-check (a/<! subrequest-return-port))]
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
          (let [request-port-stack
                (k/get-request-port-stack entity-name)
                request-port
                (peek request-port-stack)
                sub-return-port
                (a/chan)]
            (a/>! request-port [env {"SYS+param-REQUESTID&requestid" "RESET-REQUEST-PORT"
                                     "SYS+param-RETURN$chan"         sub-return-port}])
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
        (let [this-name
              (get this-map "SYS+facet-NAME&%")
              descriptors
              (get this-map "SYS+facet_map-DESCRIPTORS^descriptor")
              federation-names
              (get descriptors "FED+descriptor-FEDERATIONnames_vec&%")
              env
              (assoc env "SYS+env-FEDERATORname&federator" this-name)
              acquire-port
              (federation-acquire federation-names env)
              _ (k/request-exception-check (a/<! acquire-port))
              env
              (assoc env "SYS+env_volmap-CHILDREN&%" (volatile! {}))
              script
              (get descriptors "SYS+descriptor_map-SCRIPT^request")
              _ (doseq [request script]
                  (let [request-params
                        (val request)]
                    (k/routeFunction env this-map request-params)))
              [e]
              (a/<! (registerChildren env
                                      @(get env "SYS+env_volmap-CHILDREN&%")))
              _ (if (some? e)
                  (throw e))
              env
              (assoc env "SYS+env-FEDERATORname&federator" nil)
              env
              (assoc env "SYS+env_volmap-CHILDREN&%" nil)
              sub-return-port
              (federation-release env federation-names)
              [e]
              (a/<! sub-return-port)]
          (if (some? e)
            (throw e))
          (t/validate-names script "map" "request" nil nil nil env)
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn create-federator-operations
  [env]
  (k/register-function env {:operationid "RUN_FEDERATIONoperationid"
                            :goblock     run-federation-goblock})
  )
