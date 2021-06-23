(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-context-registration-operation
  [env]
  (let [context-registration-port
        (k/register-operation-port (assoc env :PARAMS {:operation-port-kw :REGISTER-CONTEXT-PORT}))]
    (a/go-loop []
      (let [env
            (a/<! context-registration-port)
            params
            (:PARAMS env)
            contexts-entity
            (:master-entity env)
            contexts-volatile
            (second contexts-entity)
            context-name
            (get-in env [:PARAMS :name])
            [context-name-kw _ _]
            (kw/name-as-keyword context-name)
            new-context
            (k/create-entity env)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! contexts-volatile assoc-in [:CONTEXTS context-name-kw] new-context)
        (a/>! operation-return-port new-context)
        (recur)))))

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
            (:master-entity env)
            context-volatile
            (second context-entity)
            entity-name
            (get-in env [:PARAMS :name])
            [entity-name-kw _ _]
            (kw/name-as-keyword entity-name)
            new-entity
            (k/create-entity env)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! context-volatile assoc-in [:ENTITIES entity-name-kw] new-entity)
        (a/>! operation-return-port new-entity)
        (recur)))))

(defn create-operations
  [env]
  (create-context-registration-operation env)
  (create-entity-registration-operation env)
  )
