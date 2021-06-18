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
    (println (pr-str @(second (:CONTEXT_MAIN (:CONTEXTS @env)))))
    ))
