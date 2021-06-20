(ns ae.kernel
  (:require [clojure.core.async :as a]))

(def operation-ports
  (atom {}))

(defn create-operation-port
  [env]
  (let [operation-kw
        (get-in env [:PARAMS :operation-kw])
        port
        (a/chan)]
    ((swap! operation-ports assoc operation-kw port))
    port))

(defn create-entity
  [env]
  (let [entity-map (get-in env [:PARAMS :entity-map])]
    [(a/chan)
     (volatile! entity-map)]))
