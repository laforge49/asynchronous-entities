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

(defn operation-dispatcher
  [entity]
  (let [entity-port
        (first entity)
        entity-map
        (@(second entity))
        operations
        (:OPERATIONS entity-map)]
    (a/go
      (let [env
            (a/<! entity-port)]
        (println :got (pr-str env)))))
  )

(defn create-entity
  [env]
  (let [entity-port
        (a/chan)
        entity-volatile
        (volatile! (get-in env [:PARAMS :entity-map]))
        new-entity
        [entity-port entity-volatile]
        ]
    (operation-dispatcher new-entity)
    new-entity
    ))
