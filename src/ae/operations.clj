(ns ae.operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn create-entity-registration-operation
  [env]
  (let [entity-registration-port
        (k/create-operation-port (assoc env :PARAMS {:operation-kw :REGISTER-ENTITY-PORT}))]
    (a/go-loop []
      (let [env
            (a/<! entity-registration-port)
            params
            (:PARAMS env)
            context-entity
            (:master-entity env)
            context-volatile
            (second context-entity)
            name
            (get-in env [:PARAMS :name])
            [name-kw context-name base-name]
            (k/name-as-keyword name)
            new-entity
            (k/create-entity (assoc env :PARAMS {:name name}))
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! context-volatile assoc-in [:ENTITIES name-kw] new-entity)
        (a/>! operation-return-port new-entity)
        (recur)
        ))))

(defn create-operations
  [env]
  (create-entity-registration-operation env))
