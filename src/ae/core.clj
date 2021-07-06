(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [ae.kernel :as k]
            [ae.operations.context-operations :as co]
            [ae.operations.entity-operations :as eo]
            [ae.script1 :as s1]
            ))

(defn create-operations
  [env]
  (co/create-context-operations env)
  (eo/create-entity-operations env)
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
                contexts
                (k/create-entity env {:name        "ROOT/CONTEXTS"
                                      :descriptors {:OPERATION-PORTIDS {:REGISTER-ENTITY-REQUESTID :REGISTER-ENTITY-PORTID
                                                                        :ROUTE-REQUESTID           :ROUTE-PORTID}}
                                      :classifiers {}
                                      })
                contexts-request-port
                (k/request-port contexts)
                ;env
                ;(assoc env :CONTEXTS-ENTITY contexts)
                env
                (assoc env :CONTEXTS-REQUEST-PORT contexts-request-port)
                return-port0
                (a/chan)
                _ (doseq [request-params s1/script1]
                    (let [request-params
                          (assoc request-params :requestid :ROUTE-REQUESTID)
                          request-params
                          (assoc request-params :return-port return-port0)]
                      (a/>! contexts-request-port [env request-params])
                      (k/request-exception-check (a/<! return-port0))))
                return-port4
                (a/chan)
                #_ (a/>! contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                    :target-requestid :SNAPSHOT
                                                    :target-name      "ROOT/CONTEXTS"
                                                    :return-port      return-port4}])
                ;contexts-snap
                #_ (k/request-exception-check (a/<! return-port4))
                #_ (a/>! contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                    :target-requestid :SNAPSHOT
                                                    :target-name      "CONTEXTS/CONTEXT-PROTOTYPE"
                                                    :return-port      return-port4}])
                ;context-prototype-snap
                #_ (k/request-exception-check (a/<! return-port4))
                #_ (a/>! contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                    :target-requestid :SNAPSHOT
                                                    :target-name      "CONTEXTS/MAIN"
                                                    :return-port      return-port4}])
                ;context-snap
                #_ (k/request-exception-check (a/<! return-port4))
                #_ (a/>! contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                    :target-requestid :SNAPSHOT
                                                    :target-name      "MAIN/SIMPLE-PROTOTYPE"
                                                    :return-port      return-port4}])
                ;simple-prototype
                #_ (k/request-exception-check (a/<! return-port4))
                #_ (a/>! contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                    :target-requestid :SNAPSHOT
                                                    :target-name      "MAIN/SIMPLE_1"
                                                    :return-port      return-port4}])
                ;simple1-snap
                #_ (k/request-exception-check (a/<! return-port4))
                #_ (a/>! contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                    :target-requestid :SNAPSHOT
                                                    :target-name      "MAIN/SIMPLE_2"
                                                    :return-port      return-port4}])
                ;simple2-snap
                #_ (k/request-exception-check (a/<! return-port4))
                ]
            (a/>! main-out [nil [;contexts-snap
                                 ;context-prototype-snap
                                 ;context-snap
                                 ;simple-prototype
                                 ;simple1-snap
                                 ;simple2-snap
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
