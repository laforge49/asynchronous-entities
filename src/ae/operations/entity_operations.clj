(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port env {:operation-portid :ADD-PARENT-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! add-parent-port)
            this-map
            (:this-map env)
            parent-entity-name
            (:parent-entity-name params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)
            relationship-parents
            (get-in this-map [:PARENTVECTORS relationship] [])
            relationship-parents
            (conj relationship-parents parent-entity-name)
            this-map
            (assoc-in this-map [:PARENTVECTORS relationship] relationship-parents)]
        (a/>! operation-return-port [this-map nil this-map])
        (recur)))))

(defn create-add-relationship-operation
  [env]
  (let [add-relationship-port
        (k/register-operation-port env {:operation-portid :ADD-RELATIONSHIP-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! add-relationship-port)
            this-map
            (:this-map env)
            this-name
            (:NAME this-map)
            child-entity-name
            (:child-entity-name params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)
            context-request-port
            (:CONTEXT-REQUEST-PORT env)
            add-parent-return-port
            (a/chan)
            relationship-children
            (get-in this-map [:CHILDVECTORS relationship] [])]
        (if (> (.indexOf relationship-children child-entity-name) -1)
          (a/>! operation-return-port [this-map
                                       (Exception. (str "Entity " child-entity-name " is already a " relationship " child of " this-name))
                                       nil])
          (let [relationship-children
                (conj relationship-children child-entity-name)
                this-map
                (assoc-in this-map [:CHILDVECTORS relationship] relationship-children)
                _ (a/>! context-request-port [env
                                                      {:requestid          :ROUTE-REQUESTID
                                                       :target-requestid   :ADD-PARENT-REQUESTID
                                                       :target-name        child-entity-name
                                                       :relationship       :BASIC
                                                       :parent-entity-name this-name
                                                       :return-port        add-parent-return-port
                                                       }])
                [e _]
                (a/<! add-parent-return-port)]
            (a/>! operation-return-port [this-map e this-map])))
        (recur)))))

(defn create-instantiate-operation
  [env]
  (let [instantiate-port
        (k/register-operation-port env {:operation-portid :INSTANTIATE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! instantiate-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)
            this-name
            (:NAME this-map)
            - (a/>! operation-return-port [this-map nil :NO-RETURN])
            new-entity-name
            (:name params)
            [_ new-entity-context-base-name _]
            (kw/name-as-keyword new-entity-name)
            this-descriptors
            (:DESCRIPTORS this-map)
            prototype-descriptors
            (:PROTOTYPE-DESCRIPTORS this-descriptors)
            prototype-descriptors
            (assoc prototype-descriptors :PROTOTYPE this-name)
            prototype-descriptors
            (into prototype-descriptors (:descriptors params))
            target-name
            (if (= new-entity-context-base-name "CONTEXTS")
              (str "ROOT/CONTEXTS")
              (str "CONTEXTS/" new-entity-context-base-name))
            context-request-port
            (:CONTEXT-REQUEST-PORT env)
            params
            (-> params
                (assoc :requestid :ROUTE-REQUESTID)
                (assoc :target-requestid :REGISTER-NEW-ENTITY-REQUESTID)
                (assoc :target-name target-name)
                (assoc :descriptors prototype-descriptors)
                )]
        (a/>! context-request-port [env params])
        (recur)))))

(defn create-entity-operations
  [env]
  (create-add-parent-operation env)
  (create-add-relationship-operation env)
  (create-instantiate-operation env)
  )
