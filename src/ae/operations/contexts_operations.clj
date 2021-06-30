(ns ae.operations.contexts-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

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
            (:ENTITIES @this-volatile-map)
            target-context-entity
            (target-contex-kw context-entities)
            target-request
            (:target-request params)
            ]
        (a/>! (first target-context-entity) [env
                                             (assoc params :request target-request)])
        (recur)))))

(defn create-contexts-operations
  [env]
  (create-route-to-context-operation env)
  )
