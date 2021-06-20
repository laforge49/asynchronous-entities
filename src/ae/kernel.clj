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
        entity-port
        (first master-entity)
        ]
    (a/go
      (let [entity-map
            @(second master-entity)
            operations
            (:OPERATIONS entity-map)
            env
            (a/<! entity-port)
            params
            (:PARAMS env)
            request
            (:request params)
            operation-port
            (request operations)
            env
            (assoc env :master-entity master-entity)
            ]
        (a/>! operation-port env)
        )))
  )

(defn create-entity
  [env]
  (let [entity-port
        (a/chan)
        params
        (:PARAMS env)
        name
        (:name params)
        slashindex
        (s/index-of name "/")
        context-name
        (subs name 0 slashindex)
        context-kw
        (keyword context-name)
        base-name
        (subs name (inc slashindex))
        name-kw
        (keyword context-name base-name)
        entity-map
        (:entity-map params)
        entity-map
        (assoc entity-map :NAME name)
        entity-volatile
        (volatile! entity-map)
        new-entity
        [entity-port entity-volatile]
        _ (operation-dispatcher (assoc env :PARAMS {:master-entity new-entity}))
        contexts-atom
        (:CONTEXTS env)
        ]
    (if (= context-name "CONTEXT")
      (do
        (swap! contexts-atom assoc name-kw new-entity)
        new-entity)
      (let [return-port
            (a/chan)
            context-entity
            (context-kw @contexts-atom)
            ]
        (a/>! entity-port (assoc env :PARAMS {:master-entity context-entity
                                              :request       :REGISTER-ENTITY
                                              :new-entity    new-entity
                                              :name-kw       name-kw
                                              :return-port   return-port}))
        (a/<! return-port))
      )))
