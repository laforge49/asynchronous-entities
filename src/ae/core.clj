(ns ae.core
  (:gen-class))

(defn -main
  [& args]
  (let [env
        (atom {})])
  (println "I'm a little teapot!"))
