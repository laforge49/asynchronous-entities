(ns ae.kernel
  (:require [[clojure.core.async :as a]]))

(def operation-ports
  (atom {}))

(defn create-operation-port
  [operation-kw env]
  (let [port (chan)]
    ((swap! operation-ports assoc operation-kw port))
    port))
