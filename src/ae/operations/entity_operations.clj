(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port env {:operationid :ADD-PARENT-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! add-parent-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [this-name
                (:NAME this-map)
                parent-entity-name
                (:parent-entity-name params)
                _ (if (not (k/federated? this-map))
                    (throw (Exception. (str "Entity " this-name
                                            " is not federated and so can not add a relationship to "
                                            parent-entity-name))))
                relationship
                (:relationship params)
                relationship-parents
                (get-in this-map [:PARENTVECTORS relationship] [])
                _ (if (> (.indexOf relationship-parents parent-entity-name) -1)
                    (throw (Exception. (str "Entity " parent-entity-name " is already a " relationship
                                            " parent of " this-name))))
                relationship-parents
                (conj relationship-parents parent-entity-name)
                this-map
                (assoc-in this-map [:PARENTVECTORS relationship] relationship-parents)]
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-add-relationship-operation
  [env]
  (let [add-relationship-port
        (k/register-operation-port env {:operationid :ADD-RELATIONSHIP-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! add-relationship-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [this-name
                (:NAME this-map)
                child-entity-name
                (:child-entity-name params)
                _ (if (not (k/federated? this-map))
                    (throw (Exception. (str "Entity " this-name
                                            " is not federated and so can not add a relationship to "
                                            child-entity-name))))
                relationship
                (:relationship params)
                relationship-children
                (get-in this-map [:CHILDVECTORS relationship] [])
                _ (if (> (.indexOf relationship-children child-entity-name) -1)
                    (throw (Exception. (str "Entity " child-entity-name " is already a " relationship
                                            " child of " this-name))))
                relationship-children
                (conj relationship-children child-entity-name)
                this-map
                (assoc-in this-map [:CHILDVECTORS relationship] relationship-children)
                context-request-port
                (:CONTEXT-REQUEST-PORT env)
                add-parent-return-port
                (a/chan)]
            (a/>! context-request-port [env
                                        {:requestid          :ROUTE-REQUESTID
                                         :target-requestid   :ADD-PARENT-REQUESTID
                                         :target-name        child-entity-name
                                         :relationship       :BASIC
                                         :parent-entity-name this-name
                                         :return-port        add-parent-return-port
                                         }])
            (k/request-exception-check (a/<! add-parent-return-port))
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn add-new-child-function
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        child-entity-name
        (:child-entity-name params)
        relationship
        (:relationship params)
        relationship-children
        (get-in this-map [:CHILDVECTORS relationship] [])
        _ (if (> (.indexOf relationship-children child-entity-name) -1)
            (throw (Exception. (str "Entity " child-entity-name " is already a " relationship
                                    " child of " this-name))))
        prototype
        (:prototype params)]
    [{:target-requestid :INSTANTIATE-REQUESTID
      :target-name      prototype
      :name             child-entity-name}
     {:target-requestid   :ADD-PARENT-REQUESTID
      :target-name        child-entity-name
      :relationship       relationship
      :parent-entity-name this-name}]))

(defn create-add-new-child-operation
  [env]
  (let [add-new-child-port
        (k/register-operation-port env {:operationid :ADD-NEW-CHILD-OPERATIONID
                                        :function    add-new-child-function})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! add-new-child-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [context-request-port
                (:CONTEXT-REQUEST-PORT env)
                [instantiate-params add-parent-params]
                (add-new-child-function env this-map params)
                instantiate-return-port
                (a/chan)
                instantiate-params
                (into instantiate-params {:return-port instantiate-return-port
                                          :requestid :ROUTE-REQUESTID})
                add-parent-return-port
                (a/chan)
                add-parent-params
                (into add-parent-params {:return-port add-parent-return-port
                                         :requestid :ROUTE-REQUESTID})]
            (a/>! context-request-port [env instantiate-params])
            (k/request-exception-check (a/<! instantiate-return-port))
            (a/>! context-request-port [env add-parent-params])
            (k/request-exception-check (a/<! add-parent-return-port))
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn instantiate-function
  [env this-map params]
  (let [new-entity-name
        (:name params)
        [_ new-entity-context-base-name _]
        (if (s/blank? new-entity-name)
          [nil "" nil]
          (kw/name-as-keyword new-entity-name))
        target-name
        (if (s/blank? new-entity-name)
          "ROOT/CONTEXTS"
          (if (= new-entity-context-base-name "CONTEXTS")
            (str "ROOT/CONTEXTS")
            (str "CONTEXTS/" new-entity-context-base-name)))
        this-name
        (:NAME this-map)
        this-descriptors
        (:DESCRIPTORS this-map)
        prototype-descriptors
        (:PROTOTYPE-DESCRIPTORS this-descriptors)
        prototype-descriptors
        (assoc prototype-descriptors :PROTOTYPE this-name)
        prototype-descriptors
        (into prototype-descriptors (:descriptors params))]
    (into params {:target-requestid :REGISTER-NEW-ENTITY-REQUESTID
                  :target-name      target-name
                  :descriptors      prototype-descriptors})))

(defn create-instantiate-operation
  [env]
  (let [instantiate-port
        (k/register-operation-port env {:operationid :INSTANTIATE-OPERATIONID
                                        :function    instantiate-function})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! instantiate-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [context-request-port
                (:CONTEXT-REQUEST-PORT env)
                route-params
                (instantiate-function env this-map params)
                route-params
                (assoc route-params :requestid :ROUTE-REQUESTID)]
            (a/>! operation-return-port [this-map nil :NO-RETURN])
            (a/>! context-request-port [env route-params]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-entity-operations
  [env]
  (create-add-parent-operation env)
  (create-add-relationship-operation env)
  (create-add-new-child-operation env)
  (create-instantiate-operation env)
  )
