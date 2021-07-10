(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [ae.kernel :as k]
            [ae.operations.context-operations :as co]
            [ae.operations.entity-operations :as eo]
            [ae.operations.federator-operations :as fo]
            [ae.script1 :as s1]
            ))

(defn create-operations
  [env]
  (co/create-context-operations env)
  (eo/create-entity-operations env)
  (fo/create-federator-operations env)
  )

(defn a-main
  []
  (let [main-in
        (a/chan)]
    (a/go
      (let [main-out
            (a/<! main-in)]
        (try
          (let [env
                {}
                _ (create-operations env)
                context-request-port
                (k/create-entity env {:name        "ROOT/CONTEXTS"
                                      :descriptors {:OPERATION-PORTIDS {:REGISTER-NEW-ENTITY-REQUESTID :REGISTER-NEW-ENTITY-PORTID
                                                                        :ROUTE-REQUESTID               :ROUTE-PORTID}}
                                      :classifiers {}
                                      })
                env
                (assoc env :CONTEXT-REQUEST-PORT context-request-port)
                return-port0
                (a/chan)
                _ (doseq [request-params s1/script1]
                    (let [request-params
                          (assoc request-params :requestid :ROUTE-REQUESTID)
                          request-params
                          (assoc request-params :return-port return-port0)]
                      (a/>! context-request-port [env request-params])
                      (k/request-exception-check (a/<! return-port0))))
                return-port4
                (a/chan)
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "ROOT/CONTEXTS"
                                                   :return-port      return-port4}])
                contexts-snap
                (k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
                                                   :return-port      return-port4}])
                prototype-prototype-snap
                (k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "CONTEXTS/CONTEXT-PROTOTYPE"
                                                   :return-port      return-port4}])
                context-prototype-snap
                (k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "CONTEXTS/FEDERATOR-PROTOTYPE"
                                                   :return-port      return-port4}])
                federator-prototype-snap
                (k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "CONTEXTS/FEDERATION-CONTEXT-PROTOTYPE"
                                                   :return-port      return-port4}])
                federation-context-prototype-snap
                (k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "CONTEXTS/MAIN"
                                                   :return-port      return-port4}])
                context-snap
                (k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "MAIN/SIMPLE-PROTOTYPE"
                                                   :return-port      return-port4}])
                simple-prototype-snap
                (k/request-exception-check (a/<! return-port4))
                #_ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "MAIN/SIMPLE_1"
                                                   :return-port      return-port4}])
                ;simple1-snap
                ;(k/request-exception-check (a/<! return-port4))
                #_ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "MAIN/SIMPLE_2"
                                                   :return-port      return-port4}])
                ;simple2-snap
                ;(k/request-exception-check (a/<! return-port4))
                _ (a/>! context-request-port [env {:requestid        :ROUTE-REQUESTID
                                                   :target-requestid :SNAPSHOT
                                                   :target-name      "MAIN/FEDERATOR_1"
                                                   :return-port      return-port4}])
                federator1-snap
                (k/request-exception-check (a/<! return-port4))
                ]
            (a/>! main-out [nil [contexts-snap
                                 prototype-prototype-snap
                                 context-prototype-snap
                                 federator-prototype-snap
                                 federation-context-prototype-snap
                                 context-snap
                                 simple-prototype-snap
                                 ;simple1-snap
                                 ;simple2-snap
                                 federator1-snap
                                 ]]))
          (catch Exception e
            (a/>! main-out [e nil])))))
    main-in))

(defn -main
  [& args]
  (println "go!")
  (try
    (let [main-in
          (a-main)
          main-out
          (a/chan)]
      (a/>!! main-in main-out)
      (doseq [s (k/request-exception-check (a/<!! main-out))]
        (println "\n" (pr-str s))))
    (catch Exception e
      (stacktrace/print-stack-trace e))))
