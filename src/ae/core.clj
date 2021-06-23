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
            #_(
                contexts-atom
                (atom {})
                env
                {:CONTEXTS-ATOM
                 contexts-atom
                 }
                _ (o/create-operations env)
                main-context
                (k/register-context (assoc-in env [:PARAMS :name] "CONTEXT/MAIN"))
                main-context-port
                (first main-context)
                return-port
                (a/chan)
                _ (a/>! main-context-port (assoc env :PARAMS {:name        "MAIN/SIMPLE_1"
                                                              :request     :REGISTER-ENTITY-REQUEST
                                                              :return-port return-port}))
                simple1-entity
                (a/<! return-port)
                _ (a/>! main-context-port (assoc env :PARAMS {:name        "MAIN/SIMPLE_2"
                                                              :request     :REGISTER-ENTITY-REQUEST
                                                              :return-port return-port}))
                simple2-entity
                (a/<! return-port)
                )
            ]
        (a/>! main-out :ribit)
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
    (println (a/<!! main-out))
    ))
