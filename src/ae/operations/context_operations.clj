(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-entity-registration-operation
  [env]
  (let [entity-registration-port
        (k/register-operation-port env {:operation-port-kw :REGISTER-ENTITY-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! entity-registration-port)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            new-entity-name
            (:name params)
            [new-entity-kw _ _]
            (kw/name-as-keyword new-entity-name)
            new-entity
            (k/create-entity env params)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! this-volatile-map assoc-in [:ENTITIES new-entity-kw] new-entity)
        (a/>! operation-return-port new-entity)
        (recur)))))

(defn create-route-to-entity-operation
  [env]
  (let [route-to-context-port
        (k/register-operation-port env {:operation-port-kw :ROUTE-TO-ENTITY-PORT})]
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
            target-entity-name
            (:target-entity-name params)
            [target-entity-kw _ _]
            (kw/name-as-keyword target-entity-name)
            entities
            (:ENTITIES @this-volatile-map)
            target-entity
            (target-entity-kw entities)
            target-request
            (:target-request params)
            ]
        (a/>! (first target-entity) [env
                                      (assoc params :request target-request)])
        (recur)))))

(defn create-context-operations
  [env]
  (create-entity-registration-operation env)
  (create-route-to-entity-operation env)
  )
