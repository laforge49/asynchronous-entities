(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-entity-registration-operation
  [env]
  (let [entity-registration-port
        (k/register-operation-port (assoc env :PARAMS {:operation-port-kw :REGISTER-ENTITY-PORT}))]
    (a/go-loop []
      (let [env
            (a/<! entity-registration-port)
            params
            (:PARAMS env)
            context-entity
            (:this-entity env)
            context-volatile
            (second context-entity)
            entity-name
            (get-in env [:PARAMS :name])
            [entity-kw _ _]
            (kw/name-as-keyword entity-name)
            new-entity
            (k/create-entity env)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! context-volatile assoc-in [:ENTITIES entity-kw] new-entity)
        (a/>! operation-return-port new-entity)
        (recur)))))

(defn create-context-operations
  [env]
  (create-entity-registration-operation env)
  )