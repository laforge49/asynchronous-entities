(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port env {:operation-port-kw :ADD-PARENT-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! add-parent-port)
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

(defn create-add-relationship-operation
  [env]
  (let [add-relationship-port
        (k/register-operation-port env {:operation-port-kw :ADD-RELATIONSHIP-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! add-relationship-port)
            this-entity
            (:this-entity env)
            this-volatile-map
            (second this-entity)
            this-map
            @this-volatile-map
            this-entity-name
            (:NAME this-map)
            child-entity-name
            (:child-entity-name params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)
            contexts
            (:CONTEXTS-ENTITY env)
            add-parent-return-port
            (a/chan)
            ]
        (vswap! this-volatile-map (fn [old]
                                    (let [relationship-children
                                          (conj (get-in this-entity [:CHILDVECTORS relationship] []) child-entity-name)]
                                      (assoc-in old [:CHILDVECTORS relationship] relationship-children))))
        (a/>! (first contexts) [env
                                {:request            :ROUTE-TO-CONTEXT-ENTITY-REQUEST
                                 :target-request     :ADD-PARENT-REQUEST
                                 :target-entity-name child-entity-name
                                 :relationship       :BASIC
                                 :parent-entity-name this-entity-name
                                 :return-port        add-parent-return-port
                                 }])
        (a/<! add-parent-return-port)
        (a/>! operation-return-port this-entity)
        (recur)))))

(defn create-entity-operations
  [env]
  (create-add-parent-operation env)
  (create-add-relationship-operation env)
  )
