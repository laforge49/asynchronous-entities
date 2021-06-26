(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.operations.contexts-operations :as cso]
            [ae.operations.context-operations :as co]
            [ae.operations.entity-operations :as eo]
            ))

(defn create-operations
  [env]
  (cso/create-contexts-operations env)
  (co/create-context-operations env)
  (eo/create-entity-operations env)
  )

(defn script1
  [return-port]
  [{:request         :REGISTER-CONTEXT-REQUEST
    :name            "CONTEXT/MAIN"
    :operation-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                      :ROUTE-TO-ENTITY-REQUEST :ROUTE-CONTEXT-TO-ENTITY-PORT}
    :return-port     return-port
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name                "MAIN/SIMPLE_1"
    :operation-ports     {}
    :return-port         return-port
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name                "MAIN/SIMPLE_2"
    :operation-ports     {:ADD-PARENT-REQUEST :ADD-PARENT-PORT}
    :return-port         return-port
    }
   {:request            :ROUTE-TO-ENTITY-REQUEST
    :target-request     :ADD-PARENT-REQUEST
    :target-entity-name "MAIN/SIMPLE_2"
    :relationship       :BASIC
    :parent-entity-name "MAIN/SIMPLE_1"
    :return-port        return-port
    }])

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
            (k/create-entity env {:name            "ROOT/CONTEXTS"
                                  :operation-ports {:REGISTER-CONTEXT-REQUEST :REGISTER-CONTEXT-PORT
                                                    :ROUTE-TO-CONTEXT-REQUEST :ROUTE-TO-CONTEXT-PORT
                                                    :ROUTE-TO-ENTITY-REQUEST  :ROUTE-CONTEXTS-TO-ENTITY-PORT}
                                  })
            env
            (assoc env :CONTEXTS-ENTITY contexts)
            return-port0
            (a/chan)
            _ (doseq [request-params (script1 return-port0)]
                (a/>! (first contexts) [env request-params])
                (a/<! return-port0))
            return-port4
            (a/chan)
            _ (a/>! (first contexts) [env {:request            :ROUTE-TO-ENTITY-REQUEST
                                           :target-request     :SNAPSHOT
                                           :target-entity-name "MAIN/SIMPLE_2"
                                           :return-port        return-port4}])
            simple2-snap
            (a/<! return-port4)
            ]
        (a/>! main-out simple2-snap)
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
