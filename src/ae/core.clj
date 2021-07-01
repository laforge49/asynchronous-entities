(ns ae.core
  (:require [clojure.core.async :as a]
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
            (a/<! main-in)
            env
            {}
            _ (create-operations env)
            contexts
            (k/create-entity env {:name        "ROOT/CONTEXTS"
                                  :descriptors {:OPERATION-PORTS {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                                                                  :ROUTE-REQUEST           :ROUTE-PORT}}
                                  :classifiers {}
                                  })
            contexts-request-port
            (k/request-port contexts)
            env
            (assoc env :CONTEXTS-ENTITY contexts)
            env
            (assoc env :CONTEXTS-REQUEST-PORT contexts-request-port)
            return-port0
            (a/chan)
            _ (doseq [request-params s1/script1]
                (let [request-params
                      (assoc request-params :request :ROUTE-REQUEST)
                      request-params
                      (assoc request-params :return-port return-port0)]
                  (a/>! contexts-request-port [env request-params])
                  (a/<! return-port0)))
            return-port4
            (a/chan)
            _ (a/>! contexts-request-port [env {:request        :ROUTE-REQUEST
                                                :target-request :SNAPSHOT
                                                :target-name    "CONTEXTS/CONTEXT-PROTOTYPE"
                                                :return-port    return-port4}])
            context-prototype-snap
            (a/<! return-port4)
            _ (a/>! contexts-request-port [env {:request        :ROUTE-REQUEST
                                                :target-request :SNAPSHOT
                                                :target-name    "CONTEXTS/MAIN"
                                                :return-port    return-port4}])
            context-snap
            (a/<! return-port4)
            _ (a/>! contexts-request-port [env {:request        :ROUTE-REQUEST
                                                :target-request :SNAPSHOT
                                                :target-name    "MAIN/SIMPLE_1"
                                                :return-port    return-port4}])
            simple1-snap
            (a/<! return-port4)
            _ (a/>! contexts-request-port [env {:request        :ROUTE-REQUEST
                                                :target-request :SNAPSHOT
                                                :target-name    "MAIN/SIMPLE_2"
                                                :return-port    return-port4}])
            simple2-snap
            (a/<! return-port4)
            ]
        (a/>! main-out [context-prototype-snap context-snap simple1-snap simple2-snap])
        )
      )
    main-in))

(defn -main
  [& args]
  (println "go!")
  (let [main-in
        (a-main)
        main-out
        (a/chan)]
    (a/>!! main-in main-out)
    (doseq [s (a/<!! main-out)]
      (println "\n" (pr-str s)))))
