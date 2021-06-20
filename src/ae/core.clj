(ns ae.core
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn a-main
  [main-in]
  (a/go
    (let [main-out
          (a/<! main-in)
          simple1
          [(a/chan) (volatile! {})]
          simple2
          [(a/chan) (volatile! {})]
          main-context
          [(a/chan) (volatile! {})]
          env
          {:CONTEXTS {:CONTEXT/MAIN main-context}
           :PARAMS   {}}
          ]
      (vswap! (second main-context) (fn [old]
                                      (let [context-value
                                            (-> old
                                                (assoc :NAME "CONTEXT/MAIN")
                                                (assoc :ENTITIES {:MAIN/SIMPLE_1 simple1
                                                                  :MAIN/SIMPLE_2 simple2})
                                                (assoc :OPERATIONS {})
                                                )]
                                        context-value)))
      (vswap! (second simple1) (fn [old]
                                 (let [context-value
                                       (-> old
                                           (assoc :NAME "MAIN/SIMPLE_1")
                                           (assoc :OPERATIONS {})
                                           (assoc :CHILDVECTORS {:PLAIN [:MAIN/SIMPLE_2]})
                                           )]
                                   context-value)))
      (vswap! (second simple2) (fn [old]
                                 (let [context-value
                                       (-> old
                                           (assoc :NAME "MAIN/SIMPLE_2")
                                           (assoc :OPERATIONS {})
                                           (assoc :PARENTVECTORS {:PLAIN [:MAIN/SIMPLE_1]})
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
    (println (a/<!! main-out))))
