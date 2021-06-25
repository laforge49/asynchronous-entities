(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port env {:operation-port-kw :ADD-PARENT-PORT})]
    (a/go-loop []
      (let [env
            (a/<! add-parent-port)
            params
            (:PARAMS env)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            parent-entity-name
            (:parent-entity-name params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! this-volatile-map (fn [old]
                                    (let [relationship-parents
                                          (conj (get-in this-entity [:PARENTVECTORS relationship] []) parent-entity-name)]
                                      (assoc-in old [:PARENTVECTORS relationship] relationship-parents))))
        (a/>! operation-return-port this-entity)
        (recur)))))

(defn create-entity-operations
  [env]
  (create-add-parent-operation env)
  )
