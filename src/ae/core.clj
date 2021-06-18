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
        (atom {})]
    (swap! env assoc :CONTEXTS {:CONTEXT_MAIN main-context})
    (vswap! (second main-context) (fn [old]
                                    (let [context-value
                                          (-> old
                                              (assoc :NAME "CONTEXT_MAIN")
                                              (assoc :ENTITIES {:SIMPLE_1 simple1
                                                                :SIMPLE_2 simple2})
                                              )]
                                      context-value)))
    (vswap! (second simple1) (fn [old]
                               (let [context-value
                                     (-> old
                                         (assoc :NAME "SIMPLE_1")
                                         (assoc :CONTEXTS [:CONTEXT_MAIN])
                                         (assoc :CHILDVECTORS {:PLAIN [:SIMPLE_2]})
                                         )]
                                 context-value)))
    (vswap! (second simple2) (fn [old]
                               (let [context-value
                                     (-> old
                                         (assoc :NAME "SIMPLE_2")
                                         (assoc :CONTEXTS [:CONTEXT_MAIN])
                                         (assoc :PARENTVECTORS {:PLAIN [:SIMPLE_1]})
                                         )]
                                 context-value)))
    (println (pr-str @(second (:SIMPLE_1 (get-in @(second (get-in @env [:CONTEXTS :CONTEXT_MAIN])) [:ENTITIES])))))
    ))
