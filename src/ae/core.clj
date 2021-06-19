(ns ae.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))

(defn -main
  [& args]
  (let [simple1
        [(chan) (volatile! {})]
        simple2
        [(chan) (volatile! {})]
        main-context
        [(chan) (volatile! {})]
        env
        {:CONTEXTS {:CONTEXT/MAIN main-context}
         :PARAMS {}}
        ]
    (vswap! (second main-context) (fn [old]
                                    (let [context-value
                                          (-> old
                                              (assoc :NAME "CONTEXT/MAIN")
                                              (assoc :ENTITIES {:MAIN/SIMPLE_1 simple1
                                                                :MAIN/SIMPLE_2 simple2})
                                              )]
                                      context-value)))
    (vswap! (second simple1) (fn [old]
                               (let [context-value
                                     (-> old
                                         (assoc :NAME "MAIN/SIMPLE_1")
                                         (assoc :CHILDVECTORS {:PLAIN [:MAIN/SIMPLE_2]})
                                         )]
                                 context-value)))
    (vswap! (second simple2) (fn [old]
                               (let [context-value
                                     (-> old
                                         (assoc :NAME "MAIN/SIMPLE_2")
                                         (assoc :PARENTVECTORS {:PLAIN [:MAIN/SIMPLE_1]})
                                         )]
                                 context-value)))
    (println (pr-str @(second (:MAIN/SIMPLE_1 (get-in @(second (get-in env [:CONTEXTS :CONTEXT/MAIN])) [:ENTITIES])))))
    ))
