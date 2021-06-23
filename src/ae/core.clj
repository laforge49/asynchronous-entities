(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.operations :as o]))

(defn a-main
  []
  (let [main-in
        (a/chan)]
    (a/go
      (let [main-out
            (a/<! main-in)
            env
            {}
            _ (o/create-operations env)
            contexts
            (k/create-entity (assoc env :PARAMS {:name            "ROOT/CONTEXTS"
                                                 :operation-ports {:REGISTER-CONTEXT-REQUEST :REGISTER-CONTEXT-PORT}
                                                 }))
            env
            (assoc env :CONTEXTS-ENTITY contexts)
            return-port
            (a/chan)
            _ (a/>! (first contexts)
                    (assoc env :PARAMS {:request         :REGISTER-CONTEXT-REQUEST
                                        :name            "CONTEXT/MAIN"
                                        :operation-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT}
                                        :return-port     return-port}))
            main-context
            (a/<! return-port)
            _ (a/>! (first main-context)
                    (assoc env :PARAMS {:request         :REGISTER-ENTITY-REQUEST
                                        :name            "MAIN/SIMPLE_1"
                                        :operation-ports {}
                                        :return-port     return-port}))
            simple1-entity
            (a/<! return-port)
            _ (a/>! (first main-context)
                    (assoc env :PARAMS {:request         :REGISTER-ENTITY-REQUEST
                                        :name            "MAIN/SIMPLE_2"
                                        :operation-ports {}
                                        :return-port     return-port}))
            simple2-entity
            (a/<! return-port)
            _ (println (pr-str @(second simple2-entity)))
            #_(
                _ (a/>! main-context-port (assoc env :PARAMS {:name        "MAIN/SIMPLE_2"
                                                              :request     :REGISTER-ENTITY-REQUEST
                                                              :return-port return-port}))
                  simple2-entity
                  (a/<! return-port)
                  )
            ]
        (a/>! main-out :Ribit!)
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
    (println (pr-str (a/<!! main-out)))
    ))
