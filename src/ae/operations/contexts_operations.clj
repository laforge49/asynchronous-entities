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
            (:target-name params)
            [target-contex-kw _ _]
            (kw/name-as-keyword target-context-name)
            context-entities
            (:CONTEXT-ENTITIES @this-volatile-map)
            target-context-entity
            (target-contex-kw context-entities)
            target-request
            (:target-request params)
            ]
        (a/>! (first target-context-entity) [env
                                             (assoc params :request target-request)])
        (recur)))))

(defn create-route-to-context-entity-operation
  [env]
  (let [route-to-context-entity-port
        (k/register-operation-port env {:operation-port-kw :ROUTE-TO-CONTEXT-ENTITY-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! route-to-context-entity-port)
            operation-return-port
            (:operation-return-port params)
            - (a/>! operation-return-port :NO-RETURN)
            this-contexts-entity
            (:this-entity env)
            this-volatile-map
            (second this-contexts-entity)
            target-entity-name
            (:target-name params)
            [_ target-context-base-name _]
            (kw/name-as-keyword target-entity-name)
            target-context-entity-kw
            (keyword "CONTEXT" target-context-base-name)
            context-entities
            (:CONTEXT-ENTITIES @this-volatile-map)
            target-context-entity
            (target-context-entity-kw context-entities)
            target-request
            (:target-request params)
            ]
        (a/>! (first target-context-entity) [env
                                             (assoc params :request :ROUTE-TO-LOCAL-ENTITY-REQUEST)])
        (recur)))))

(defn create-contexts-operations
  [env]
  (create-context-registration-operation env)
  (create-route-to-context-operation env)
  (create-route-to-context-entity-operation env)
  )
