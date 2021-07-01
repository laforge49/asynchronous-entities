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
            (k/volatile-map this-entity)
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
            (k/volatile-map this-entity)
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
            contexts-request-port
            (:CONTEXTS-REQUEST-PORT env)
            add-parent-return-port
            (a/chan)
            ]
        (vswap! this-volatile-map (fn [old]
                                    (let [relationship-children
                                          (conj (get-in this-entity [:CHILDVECTORS relationship] []) child-entity-name)]
                                      (assoc-in old [:CHILDVECTORS relationship] relationship-children))))
        (a/>! contexts-request-port [env
                                     {:request            :ROUTE-REQUEST
                                      :target-request     :ADD-PARENT-REQUEST
                                      :target-name        child-entity-name
                                      :relationship       :BASIC
                                      :parent-entity-name this-entity-name
                                      :return-port        add-parent-return-port
                                      }])
        (a/<! add-parent-return-port)
        (a/>! operation-return-port this-entity)
        (recur)))))

(defn create-clone-operation
  [env]
  (let [clone-port
        (k/register-operation-port env {:operation-port-kw :CLONE-PORT})]
    (a/go-loop []
      (let [[env params]
            (a/<! clone-port)
            operation-return-port
            (:operation-return-port params)
            - (a/>! operation-return-port :NO-RETURN)
            new-entity-name
            (:name params)
            [_ new-entity-context-base-name _]
            (kw/name-as-keyword new-entity-name)
            this-entity
            (:this-entity env)
            this-volatile-map
            (k/volatile-map this-entity)
            this-map
            @this-volatile-map
            this-descriptors
            (:DESCRIPTORS this-map)
            prototype-descriptors
            (:PROTOTYPE-DESCRIPTORS this-descriptors)
            prototype-classifiers
            (:PROTOTYPE-CLASSIFIERS this-descriptors)
            target-name
            (if (= new-entity-context-base-name "CONTENTS")
              (str "ROOT/CONTEXTS")
              (str "CONTEXTS/" new-entity-context-base-name))
            contexts-request-port
            (:CONTEXTS-REQUEST-PORT env)
            params
            (-> params
                (assoc :request :ROUTE-REQUEST)
                (assoc :target-request :REGISTER-ENTITY-REQUEST)
                (assoc :target-name target-name)
                (assoc :descriptors prototype-descriptors)
                (assoc :classifiers prototype-classifiers)
                )]
        (a/>! contexts-request-port [env params])
        (recur)))))

(defn create-entity-operations
  [env]
  (create-add-parent-operation env)
  (create-add-relationship-operation env)
  (create-clone-operation env)
  )
