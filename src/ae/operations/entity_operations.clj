(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn instantiateFunction
  [env this-map params]
  (let [name
        (:name params)
        this-name
        (:NAME this-map)
        this-descriptors
        (:DESCRIPTORS this-map)
        instantiation-descriptors
        (:INSTANTIATION-DESCRIPTORS this-descriptors)
        instantiation-descriptors
        (assoc instantiation-descriptors :PROTOTYPE this-name)
        instantiation-descriptors
        (into instantiation-descriptors (:descriptors params))
        initialization-port
        (a/chan)
        params
        (into params {:target-name         name
                      :initialization-port initialization-port
                      :descriptors         instantiation-descriptors})
        [new-entity-public-request-port snap]
        (k/create-entity env params)
        federation-map-volatile
        (:FEDERATION-MAP-VOLATILE env)
        new-children-volatile
        (:NEW-CHILDREN-VOLATILE env)
        ]
    (vswap! federation-map-volatile assoc name [(volatile! snap) initialization-port])
    (vswap! new-children-volatile assoc name new-entity-public-request-port)
    [this-map this-map]))

(defn instantiateOperation
  [env this-map params]
  (let [name
        (:name params)
        [_ entity-context-base-name _]
        (if (s/blank? name)
          [nil "" nil]
          (kw/name-as-keyword name))
        target-name
        (if (s/blank? name)
          "ROOT/CONTEXTS"
          (if (= entity-context-base-name "CONTEXTS")
            (str "ROOT/CONTEXTS")
            (str "CONTEXTS/" entity-context-base-name)))
        this-name
        (:NAME this-map)
        this-descriptors
        (:DESCRIPTORS this-map)
        instantiation-descriptors
        (:INSTANTIATION-DESCRIPTORS this-descriptors)
        instantiation-descriptors
        (assoc instantiation-descriptors :PROTOTYPE this-name)
        instantiation-descriptors
        (into instantiation-descriptors (:descriptors params))]
    (into params {:target-requestid :REGISTER-ENTITY-REQUESTID
                  :target-name      target-name
                  :descriptors      instantiation-descriptors})))

(defn create-instantiate-operation
  [env]
  (let [instantiate-port
        (k/register-operation env {:operationid :INSTANTIATE-OPERATIONID
                                   :function    instantiateFunction})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! instantiate-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [context-request-port
                (:CONTEXT-REQUEST-PORT env)
                route-params
                (instantiateOperation env this-map params)
                route-params
                (assoc route-params :requestid :ROUTE-REQUESTID)]
            (a/>! operation-return-port [this-map nil :NO-RETURN])
            (a/>! context-request-port [env route-params]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

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
  (let [child-name
        (:child-name params)
        _ (if (s/blank? child-name)
            (throw (Exception. "ADD RELATIONSHIP requires child-name")))
        relationship
        (:relationship params)
        _ (if (not (keyword? relationship))
            (throw (Exception. "ADD RELATIONSHIP requires keyword: " relationship)))
        child-instantiator
        (:child-instantiator params)
        _ (if (not (s/blank? child-instantiator))
            (k/federationRouteFunction env
                                       this-map
                                       {:target-requestid :INSTANTIATE-REQUESTID
                                        :target-name      child-instantiator
                                        :name             child-name}))
        this-map
        (addChildOperation env this-map params)
        [this-map rv]
        (k/federationRouteFunction env
                                   this-map
                                   {:target-requestid :ADD-PARENT-REQUESTID
                                    :target-name      child-name
                                    :parent-name      (:NAME this-map)
                                    :relationship     (:relationship params)})]
    [this-map this-map]))

(defn create-entity-operations
  [env]
  (create-instantiate-operation env)
  (k/register-function env {:operationid :ADD-PARENT-OPERATIONID
                            :function    addParentFunction})
  (k/register-operation env {:operationid :ADD-RELATIONSHIP-OPERATIONID
                             :function    addRelationshipFunction})
  )
