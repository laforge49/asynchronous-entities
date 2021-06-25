(ns ae.operations.contexts-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-context-registration-operation
  [env]
  (let [context-registration-port
        (k/register-operation-port env {:operation-port-kw :REGISTER-CONTEXT-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! context-registration-port)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            new-context-name
            (:name params)
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
        (k/register-operation-port env {:operation-port-kw :ROUTE-TO-CONTEXT-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! route-to-context-port)
            operation-return-port
            (:operation-return-port params)
            - (a/>! operation-return-port :NO-RETURN)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            target-context-name
            (:target-context-name params)
            [target-contex-kw _ _]
            (kw/name-as-keyword target-context-name)
            context-entities
            (:CONTEXT-ENTITIES @this-volatile-map)
            context-entity
            (target-contex-kw context-entities)
            target-request
            (:target-request params)
            ]
        (a/>! (first context-entity) [env
                                      (assoc params :request target-request)])
        (recur)))))

(defn create-contexts-operations
  [env]
  (create-context-registration-operation env)
  (create-route-to-context-operation env)
  )
