(ns ae.operations.contexts-operations
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
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            new-context-name
            (get-in env [:PARAMS :name])
            [new-context-kw _ _]
            (kw/name-as-keyword new-context-name)
            new-context
            (k/create-entity env params)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! this-volatile-map assoc-in [:CONTEXT-ENTITIES new-context-kw] new-context)
        (a/>! operation-return-port new-context)
        (recur)))))

(defn create-route-to-context-operation
  [env]
  (let [route-to-context-port
        (k/register-operation-port (assoc env :PARAMS {:operation-port-kw :ROUTE-TO-CONTEXT-PORT}))]
    (a/go-loop []
      (let [env
            (a/<! route-to-context-port)
            params
            (:PARAMS env)
            operation-return-port
            (:operation-return-port params)
            - (a/>! operation-return-port :NO-RETURN)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            target-context-name
            (get-in env [:PARAMS :target-context-name])
            [target-contex-kw _ _]
            (kw/name-as-keyword target-context-name)
            context-entities
            (:CONTEXT-ENTITIES @this-volatile-map)
            context-entity
            (target-contex-kw context-entities)
            target-request
            (:target-request params)
            ]
        (a/>! (first context-entity) (assoc-in env [:PARAMS :request] target-request))
        (recur)))))

(defn create-contexts-operations
  [env]
  (create-context-registration-operation env)
  (create-route-to-context-operation env)
  )
