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
        (atom {})])
  (println "I'm a little teapot!"))
