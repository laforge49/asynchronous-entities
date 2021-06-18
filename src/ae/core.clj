(ns ae.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))

(defn -main
  [& args]
  (let [main-context
        [(chan) (volatile! {})]
        env
        (atom {})]
    (swap! env assoc :CONTEXTS {:CONTEXT_MAIN main-context})
    (vswap! (second main-context) (fn [old]
                                    (let [context-value
                                          (-> old
                                              (assoc :NAME :CONTEXT_MAIN)
                                              (assoc :ENTITIES {}))]
                                      context-value)))
    (println (pr-str @(second (:CONTEXT_MAIN (:CONTEXTS @env)))))
    ))
