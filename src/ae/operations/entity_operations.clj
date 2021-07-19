(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn addParentFunction
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        parent-name
        (:parent-name params)
        relationship
        (:relationship params) relationship-parents
        (get-in this-map [:PARENTVECTORS relationship] [])
        _ (if (> (.indexOf relationship-parents parent-name) -1)
            (throw (Exception. (str "Entity " parent-name " is already a " relationship
                                    " parent of " this-name))))
        relationship-parents
        (conj relationship-parents parent-name)
        this-map
    (assoc-in this-map [:PARENTVECTORS relationship] relationship-parents)]
    [this-map this-map]))

(defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port env {:operationid :ADD-PARENT-OPERATIONID
                                        :function    addParentFunction})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! add-parent-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [this-name
                (:NAME this-map)
                parent-name
                (:parent-name params)
                _ (if (not (k/federated? this-map))
                    (throw (Exception. (str "Entity " this-name
                                            " is not federated and so can not add a relationship to "
                                            parent-name))))
                [this-map rv]
                (addParentFunction env this-map params)]
            (a/>! operation-return-port [this-map nil rv]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn addParentParams
  [env this-map params]
  {:target-requestid :ADD-PARENT-REQUESTID
   :target-name      (:child-name params)
   :parent-name      (:NAME this-map)
   :relationship     (:relationship params)})

(defn addChildOperation
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        child-name
        (:child-name params)
        _ (if (not (k/federated? this-map))
            (throw (Exception. (str "Entity " this-name
                                    " is not federated and so can not add a relationship to "
                                    child-name))))
        relationship
        (:relationship params)
        relationship-children
        (get-in this-map [:CHILDVECTORS relationship] [])
        _ (if (> (.indexOf relationship-children child-name) -1)
            (throw (Exception. (str "Entity " child-name " is already a " relationship
                                    " child of " this-name))))
        relationship-children
        (conj relationship-children child-name)]
    (assoc-in this-map [:CHILDVECTORS relationship] relationship-children)))

(defn addRelationshipFunction
  [env this-map params]
  (let [this-map
        (addChildOperation env this-map params)
        [this-map rv]
        (k/federationRouteFunction env this-map (addParentParams env this-map params))]
    [this-map this-map]))

(defn create-add-relationship-operation
  [env]
  (let [add-relationship-port
        (k/register-operation-port env {:operationid :ADD-RELATIONSHIP-OPERATIONID
                                        :function    addRelationshipFunction})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! add-relationship-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (if (not (k/federated? this-map))
            (throw (Exception. (str "Entity " (:NAME this-map)
                                    " is not federated and so can not add a relationship to "
                                    (:child-name params)))))
          (let [add-parent-return-port
                (a/chan)
                _ (a/>! (:CONTEXT-REQUEST-PORT env) [env
                                                     (into (addParentParams env this-map params)
                                                           {:requestid   :ROUTE-REQUESTID
                                                            :return-port add-parent-return-port})])
                _ (k/request-exception-check (a/<! add-parent-return-port))
                this-map
                (addChildOperation env this-map params)]
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn addRelationshipParams
  [env this-map params]
  {:target-requestid :ADD-RELATIONSHIP-REQUESTID
   :target-name      (:NAME this-map)
   :relationship     (:relationship params)
   :child-name       (:child-name params)})

(defn instantiateParams
  [env this-map params]
  {:target-requestid :INSTANTIATE-REQUESTID
   :target-name      (:prototype params)
   :name             (:child-name params)})

(defn create-add-new-child-operation
  [env]
  (let [add-new-child-port
        (k/register-operation-port env {:operationid :ADD-NEW-CHILD-OPERATIONID
                                        :function    nil})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! add-new-child-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [context-request-port
                (:CONTEXT-REQUEST-PORT env)
                instantiate-return-port
                (a/chan)
                add-relationship-return-port
                (a/chan)
                _ (a/>! context-request-port [env (into (instantiateParams env this-map params)
                                                        {:return-port instantiate-return-port
                                                         :requestid   :ROUTE-REQUESTID})])
                _ (k/request-exception-check (a/<! instantiate-return-port))
                _ (a/>! context-request-port [env (into (addRelationshipParams env this-map params)
                                                        {:return-port add-relationship-return-port
                                                         :requestid   :ROUTE-REQUESTID})])
                this-map
                (k/request-exception-check (a/<! add-relationship-return-port))]
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
