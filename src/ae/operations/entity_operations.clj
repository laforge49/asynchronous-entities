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
        instantiation-classifiers
        (:INSTANTIATION-CLASSIFIERS this-descriptors)
        initialization-port
        (a/chan)
        params
        (into params {:target-name         name
                      :initialization-port initialization-port
                      :descriptors         instantiation-descriptors
                      :classifiers         instantiation-classifiers})
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
  (let [context-name
        (:name params)
        target-name
        (k/entityContextName context-name)
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
        instantiation-classifiers
        (:INSTANTIATION-CLASSIFIERS this-descriptors)
        ]
    (into params {:target-requestid :REGISTER-ENTITY-REQUESTID
                  :target-name      target-name
                  :descriptors      instantiation-descriptors
                  :classifiers      instantiation-classifiers})))

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

(defn addDescriptorFunction
  [env this-map params]
  (let [descriptor
        (:descriptor params)
        _ (if (not (keyword? descriptor))
            (throw (Exception. "ADD DESCRIPTOR requires the descriptor be a keyword")))
        descriptor-value
        (:descriptor-value params)
        old-descriptor-value
        (get-in this-map [:DESCRIPTORS descriptor])
        _ (if (some? old-descriptor-value)
            (throw (Exception. (str "ADD DESCRIPTOR encountered a pre-existing value: " old-descriptor-value))))
        this-map
        (assoc-in this-map [:DESCRIPTORS descriptor] descriptor-value)]
    [this-map this-map]))

(defn addClassifierFunction
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        _ (if (s/blank? this-name)
            (throw (Exception. "ADD CLASSIFIER requires a name on the entity being assigned a classifier")))
        classifier
        (:classifier params)
        _ (if (not (keyword? classifier))
            (throw (Exception. "ADD CLASSIFIER requires the classifier be a keyword")))
        classifier-value
        (:classifier-value params)
        old-classifier-value
        (get-in this-map [:CLASSIFIERS classifier])
        _ (if (some? old-classifier-value)
            (throw (Exception. (str "ADD CLASSIFIER encountered a pre-existing value: " old-classifier-value))))
        this-map
        (assoc-in this-map [:CLASSIFIERS classifier] classifier-value)
        new-classifiers-voltile
        (:NEW-CLASSIFIERS-VOLATILE env)]
    (vswap! new-classifiers-voltile conj [this-name classifier classifier-value])
    [this-map this-map]))

(defn create-entity-operations
  [env]
  (create-instantiate-operation env)
  (k/register-function env {:operationid :ADD-DESCRIPTOR-OPERATIONID
                            :function    addDescriptorFunction})
  (k/register-function env {:operationid :ADD-CLASSIFIER-OPERATIONID
                            :function    addClassifierFunction})
  )
