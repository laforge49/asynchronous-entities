(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port (assoc env :PARAMS {:operation-port-kw :ADD-PARENT-PORT}))]
    (a/go-loop []
      (let [env
            (a/<! add-parent-port)
            params
            (:PARAMS env)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            parent-entity
            (:parent-entity params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)
            ]

        (a/>! operation-return-port true)
        (recur)))))

(defn create-entity-operations
  [env]
  (create-add-parent-operation env)
  )
