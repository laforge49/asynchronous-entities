(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
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
        (:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor this-descriptors)
        instantiation-descriptors
        (assoc instantiation-descriptors :SYSTEMcontext/INSTANTIATOR this-name)
        instantiation-descriptors
        (into instantiation-descriptors (:descriptors params))
        instantiation-classifiers
        (:SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor this-descriptors)
        initialization-port
        (a/chan)
        params
        (into params {:target_name         name
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
        (:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor this-descriptors)
        instantiation-descriptors
        (assoc instantiation-descriptors :SYSTEMcontext/INSTANTIATOR this-name)
        instantiation-descriptors
        (into instantiation-descriptors (:descriptors params))
        instantiation-classifiers
        (:SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor this-descriptors)
        ]
    (into params {:target_requestid "SYSTEMcontext+REGISTER_ENTITYrequestid"
                  :target_name      target-name
                  :descriptors      instantiation-descriptors
                  :classifiers      instantiation-classifiers})))

(defn instantiate-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (:operation-return-port params)]
      (try
        (let [context-request-port
              (:CONTEXT-REQUEST-PORT env)
              route-params
              (instantiateOperation env this-map params)
              route-params
              (assoc route-params :requestid "SYSTEMcontext+ROUTErequestid")]
          (a/>! operation-return-port [this-map nil :NO-RETURN])
          (a/>! context-request-port [env route-params]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

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
    (if (= descriptor :SYSTEMcontext/INVARIANTdescriptor)
      (if (= descriptor-value true)
        (k/add-invariant-map name this-map)))
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

(defn entity-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (:operation-return-port params)]
      (try
        (let [this-name
              (:NAME this-map)
              context-name
              (k/entityContextName this-name)
              file-name
              (str "./reports/" context-name "/" this-name ".txt")
              heading
              (str "Entity Report for " this-name "\n\n")
              report
              (str heading
                   (r/classifier-report 1 this-name this-map)
                   (r/descriptor-report 2 this-name this-map))]
          (io/make-parents file-name)
          (spit file-name report)
          (a/>! operation-return-port [this-map nil this-map]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn create-entity-operations
  [env]
  (k/register-function env {:operationid "INSTANTIATEoperationid"
                            :function    instantiateFunction
                            :goblock     instantiate-goblock})
  (k/register-function env {:operationid "ADD_DESCRIPTORoperationid"
                            :function    addDescriptorFunction})
  (k/register-function env {:operationid "ADD_CLASSIFIERoperationid"
                            :function    addClassifierFunction})
  (k/register-function env {:operationid "ENTITY_REPORToperationid"
                            :goblock     entity-report-goblock})
  )
