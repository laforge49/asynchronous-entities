(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn entity-registration-operation
  [env]
  (let [entity-registration-port
        (k/create-operation-port (assoc env :PARAMS {:operation-kw :REGISTER-ENTITY-PORT}))]
    (a/go
      (let [[context-entity env]
            (a/<! entity-registration-port)
            params
            (:PARAMS env)
            context-volatile
            @(second context-entity)
            new-entity
            (:new-entity params)
            name-kw
            (:name-kw params)
            request-out
            (:request-out params)
            ]
        (swap! context-volatile assoc-in [:ENTITIES name-kw] new-entity)
        (a/>! request-out new-entity)
        ))))

(defn create-operations
  [env]
  (entity-registration-operation env))

(defn a-main
  []
  (let [main-in
        (a/chan)]
    (a/go
      (let [main-out
            (a/<! main-in)
            contexts-atom
            (atom {})
            env
            {:CONTEXTS
             contexts-atom
             }
            _ (create-operations env)
            _ (k/create-entity (assoc env :PARAMS {:name             "CONTEXT/MAIN"
                                                   :operations-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT}
                                                   :childvectors     {}
                                                   :parentvectors    {}
                                                   }))
            ;simple1
            ;(k/create-entity (assoc-in env [:PARAMS :NAME] "MAIN/SIMPLE_1"))
            ;simple2
            ;(k/create-entity (assoc-in env [:PARAMS :NAME] "MAIN/SIMPLE_2"))
            ;main-context
            #_(k/create-entity (-> env
                                   (assoc-in [:PARAMS :NAME] "MAIN")
                                   ;                     (assoc-in [:PARAMS :entity-map :ENTITIES :MAIN/SIMPLE_1] simple1)
                                   ;                     (assoc-in [:PARAMS :entity-map :ENTITIES :MAIN/SIMPLE_2] simple2)
                                   ))
            ;request-out
            ;(a/chan)
            ]
        #_(a/>! (first main-context) [request-out
                                      :REGISTER-ENTITY
                                      (assoc env :PARAMS {:new-entity simple1
                                                          :name-kw    :MAIN/SIMPLE_1})])
        #_(println :request-got (a/<! request-out))
        #_(vswap! (second simple1) (fn [old]
                                     (let [context-value
                                           (-> old
                                               (assoc-in [:CHILDVECTORS :PLAIN] [:MAIN/SIMPLE_2])
                                               )]
                                       context-value)))
        #_(vswap! (second simple2) (fn [old]
                                     (let [context-value
                                           (-> old
                                               (assoc-in [:PARENTVECTORS :PLAIN] [:MAIN/SIMPLE_1])
                                               )]
                                       context-value)))
        #_(a/>! main-out (pr-str @(second (:MAIN/SIMPLE_1 (get-in @(second (get-in env [:CONTEXTS :CONTEXT/MAIN])) [:ENTITIES])))))
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
