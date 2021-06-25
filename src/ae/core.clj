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
  [env]
  [{:request         :REGISTER-CONTEXT-REQUEST
    :name            "CONTEXT/MAIN"
    :operation-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                      }
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name                "MAIN/SIMPLE_1"
    :operation-ports     {}
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name                "MAIN/SIMPLE_2"
    :operation-ports     {}
    :return-port         (get-in env [:PARAMS :return-port])
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
            ]
        (doseq [request-params (script1 (assoc env :PARAMS {:return-port return-port}))]
          (a/>! (first contexts)
                (assoc env :PARAMS request-params)))
        (a/>! main-out @(second (a/<! return-port)))
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
