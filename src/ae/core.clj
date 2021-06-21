(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn entity-registration-operation
  [env]
  (let [entity-registration-port
        (k/create-operation-port (assoc env :PARAMS {:operation-kw :REGISTER-ENTITY-PORT}))]
    (a/go
      (let [env
            (a/<! entity-registration-port)
            params
            (:PARAMS env)
            context-entity
            (:master-entity env)
            context-volatile
            @(second context-entity)
            new-entity
            (:new-entity params)
            name-kw
            (:name-kw params)
            operation-return-port
            (:operation-return-port params)
            ]
        (swap! context-volatile assoc-in [:ENTITIES name-kw] new-entity)
        (a/>! operation-return-port new-entity)
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
            {:CONTEXTS-ATOM
             contexts-atom
             }
            ;simple2
            ;(k/create-entity (assoc-in env [:PARAMS :name] "MAIN/SIMPLE_2"))
            ;main-context
            #_(k/create-entity (-> env
                                   (assoc-in [:PARAMS :name] "MAIN")
                                   ;                     (assoc-in [:PARAMS :entity-map :ENTITIES :MAIN/SIMPLE_1] simple1)
                                   ;                     (assoc-in [:PARAMS :entity-map :ENTITIES :MAIN/SIMPLE_2] simple2)
                                   ))
            ;request-out
            ;(a/chan)
            _ (create-operations env)
            main-context
            (k/register-context (assoc-in env [:PARAMS :name] "CONTEXT/MAIN"))
            ]
        ;(k/register-entity (assoc-in env [:PARAMS :name] "MAIN/SIMPLE_1"))
        (a/>! main-out :ribit)
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
