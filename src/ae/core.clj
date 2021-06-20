(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn a-main
  [main-in]
  (a/go
    (let [main-out
          (a/<! main-in)
          env
          {:CONTEXTS {}
           :PARAMS   {:entity-map {:OPERATIONS    {}
                                   :CHILDVECTORS  {}
                                   :PARENTVECTORS {}}}}
          simple1
          (k/create-entity (assoc-in env [:PARAMS :entity-map :NAME] "MAIN/SIMPLE_1"))
          simple2
          (k/create-entity (assoc-in env [:PARAMS :entity-map :NAME] "MAIN/SIMPLE_2"))
          main-context
          (k/create-entity (-> env
                               (assoc-in [:PARAMS :entity-map :NAME] "MAIN")
                               (assoc-in [:PARAMS :entity-map :ENTITIES :MAIN/SIMPLE_1] simple1)
                               (assoc-in [:PARAMS :entity-map :ENTITIES :MAIN/SIMPLE_2] simple2)
                               ))
          env
          (assoc-in env [:CONTEXTS :CONTEXT/MAIN] main-context)
          request-out
          (a/chan)
          ]
      (a/>! (first main-context) [request-out
                                  (assoc env :PARAMS {:request :REGISTER-ENTITY
                                                      :entity  simple1})])
      (println :request-got (a/<! request-out))
      (vswap! (second simple1) (fn [old]
                                 (let [context-value
                                       (-> old
                                           (assoc-in [:CHILDVECTORS :PLAIN] [:MAIN/SIMPLE_2])
                                           )]
                                   context-value)))
      (vswap! (second simple2) (fn [old]
                                 (let [context-value
                                       (-> old
                                           (assoc-in [:PARENTVECTORS :PLAIN] [:MAIN/SIMPLE_1])
                                           )]
                                   context-value)))
      (a/>! main-out (pr-str @(second (:MAIN/SIMPLE_1 (get-in @(second (get-in env [:CONTEXTS :CONTEXT/MAIN])) [:ENTITIES])))))
      )))

(defn -main
  [& args]
  (println "go!")
  (let [main-in
        (a/chan)
        main-out
        (a/chan)]
    (a-main main-in)
    (a/>!! main-in main-out)
    (println (a/<!! main-out))
    ))
