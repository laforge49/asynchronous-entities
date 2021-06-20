(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]))

(def operation-ports
  (atom {}))

(defn create-operation-port
  [env]
  (let [operation-kw
        (get-in env [:PARAMS :operation-kw])
        port
        (a/chan)]
    (swap! operation-ports assoc operation-kw port)
    port))

(defn operation-dispatcher
  [env]
  (let [params
        (:PARAMS env)
        master-entity
        (:master-entity params)
        request
        (:request params)
        entity-port
        (first master-entity)
        entity-map
        @(second master-entity)
        operations
        (:OPERATIONS entity-map)]
    (a/go
      (let [[request-out request env]
            (a/<! entity-port)]
        )))
  )

(defn create-entity
  [env]
  (let [entity-port
        (a/chan)
        params
        (:PARAMS env)
        name
        (:NAME params)
        slashindex
        (s/index-of name \")
        context-name
        (subs name 0 slashindex)
        context-kw
        (keyword context-name)
        entity-map
        (:entity-map params)
        entity-map
        (assoc entity-map :NAME name)
        entity-volatile
        (volatile! entity-map)
        new-entity
        [entity-port entity-volatile]
        contexts-atom
        (:CONTEXTS env)
        ]
    (if (= context-name "CONTEXT")
      (do
        (swap! contexts-atom assoc context-kw context-name)
        new-entity)
      (let [base-name
            (subs name (inc slashindex))
            name-kw
            (keyword context-name base-name)
            return-port
            (a/chan)
            ]
        (operation-dispatcher (assoc env :PARAMS {:master-entity (context-kw @contexts-atom)
                                                  :request :REGISTER-ENTITY
                                                  :new-entity    new-entity
                                                  :name-kw       name-kw
                                                  :return-port   return-port}))
        (a/<! return-port)))))
