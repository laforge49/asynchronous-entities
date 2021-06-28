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
  [{:request     :REGISTER-CONTEXT-REQUEST
    :name        "CONTEXT/MAIN"
    :descriptors {:OPERATION-PORTS {:REGISTER-ENTITY-REQUEST       :REGISTER-ENTITY-PORT
                                    :ROUTE-TO-LOCAL-ENTITY-REQUEST :ROUTE-TO-LOCAL-ENTITY-PORT}}
    :classifiers {}
    :return-port return-port
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name                "MAIN/SIMPLE_1"
    :descriptors         {:OPERATION-PORTS {:ADD-PARENT-REQUEST       :ADD-PARENT-PORT
                                            :ADD-RELATIONSHIP-REQUEST :ADD-RELATIONSHIP-PORT}
                          }
    :classifiers         {}
    :return-port         return-port
    }
   {:request             :ROUTE-TO-CONTEXT-REQUEST
    :target-request      :REGISTER-ENTITY-REQUEST
    :target-context-name "CONTEXT/MAIN"
    :name                "MAIN/SIMPLE_2"
    :descriptors         {:OPERATION-PORTS {:ADD-PARENT-REQUEST       :ADD-PARENT-PORT
                                            :ADD-RELATIONSHIP-REQUEST :ADD-RELATIONSHIP-PORT
                                            }}
    :classifiers         {}
    :return-port         return-port
    }
   {:request            :ROUTE-TO-CONTEXT-ENTITY-REQUEST
    :target-request     :ADD-RELATIONSHIP-REQUEST
    :target-entity-name "MAIN/SIMPLE_1"
    :relationship       :BASIC
    :child-entity-name  "MAIN/SIMPLE_2"
    :return-port        return-port
    }
   {:request            :ROUTE-TO-CONTEXT-ENTITY-REQUEST
    :target-request     :ADD-PARENT-REQUEST
    :target-entity-name "MAIN/SIMPLE_2"
    :relationship       :BASIC
    :parent-entity-name "MAIN/SIMPLE_1"
    :return-port        return-port
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
            (k/create-entity env {:name        "ROOT/CONTEXTS"
                                  :descriptors {:OPERATION-PORTS {:REGISTER-ENTITY-REQUEST         :REGISTER-ENTITY-PORT
                                                                  :REGISTER-CONTEXT-REQUEST        :REGISTER-CONTEXT-PORT
                                                                  :ROUTE-TO-CONTEXT-REQUEST        :ROUTE-TO-CONTEXT-PORT
                                                                  :ROUTE-TO-CONTEXT-ENTITY-REQUEST :ROUTE-TO-CONTEXT-ENTITY-PORT}}
                                  :classifiers {}
                                  })
            env
            (assoc env :CONTEXTS-ENTITY contexts)
            return-port0
            (a/chan)
            _ (doseq [request-params (script1 return-port0)]
                (a/>! (first contexts) [env request-params])
                (a/<! return-port0)
                )
            return-port4
            (a/chan)
            _ (a/>! (first contexts) [env {:request            :ROUTE-TO-CONTEXT-ENTITY-REQUEST
                                           :target-request     :SNAPSHOT
                                           :target-entity-name "MAIN/SIMPLE_1"
                                           :return-port        return-port4}])
            simple1-snap
            (a/<! return-port4)
            _ (a/>! (first contexts) [env {:request            :ROUTE-TO-CONTEXT-ENTITY-REQUEST
                                           :target-request     :SNAPSHOT
                                           :target-entity-name "MAIN/SIMPLE_2"
                                           :return-port        return-port4}])
            simple2-snap
            (a/<! return-port4)
            ]
        (a/>! main-out [simple1-snap simple2-snap])
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
