(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.operations.contexts-operations :as cso]
            [ae.operations.context-operations :as co]
            ))

(defn create-operations
  [env]
  (cso/create-contexts-operations env)
  (co/create-context-operations env)
  )

(defn script1
  [return-port]
  [{:request         :REGISTER-CONTEXT-REQUEST
    :name            "CONTEXT/MAIN"
    :operation-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                      }
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name            "MAIN/SIMPLE_1"
    :operation-ports {}
    :return-port         return-port
    }
   ])

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
            (k/create-entity (assoc env :PARAMS {:name            "ROOT/CONTEXTS"
                                                 :operation-ports {:REGISTER-CONTEXT-REQUEST :REGISTER-CONTEXT-PORT
                                                                   :ROUTE-TO-CONTEXT-REQUEST :ROUTE-TO-CONTEXT-PORT}
                                                 }))
            env
            (assoc env :CONTEXTS-ENTITY contexts)
            return-port
            (a/chan)
            _ (a/>! (first contexts)
                    (assoc env :PARAMS {:request         :REGISTER-CONTEXT-REQUEST
                                        :name            "CONTEXT/MAIN"
                                        :operation-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                                                          }
                                        }))
            _ (a/>! (first contexts)
                    (assoc env :PARAMS {:request             :ROUTE-TO-CONTEXT-REQUEST
                                        :target-request      :REGISTER-ENTITY-REQUEST
                                        :target-context-name "CONTEXT/MAIN"
                                        :name            "MAIN/SIMPLE_1"
                                        :operation-ports {}
                                        }))
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
