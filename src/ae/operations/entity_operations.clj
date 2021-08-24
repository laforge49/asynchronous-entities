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
        (get params "name")
        this-name
        (get this-map "NAME")
        this-descriptors
        (get this-map "DESCRIPTORS")
        instantiation-descriptors
        (get this-descriptors "SYS+INSTANTIATION_DESCRIPTORSdescriptor")
        instantiation-descriptors
        (assoc instantiation-descriptors "SYS+INSTANTIATORdescriptor" this-name)
        instantiation-descriptors
        (into instantiation-descriptors (get params "descriptors"))
        instantiation-classifiers
        (get this-descriptors "SYS+INSTANTIATION_CLASSIFIERSdescriptor")
        initialization-port
        (a/chan)
        params
        (into params {"target_name"        name
                      :initialization-port initialization-port
                      "descriptors"        instantiation-descriptors
                      "classifiers"        instantiation-classifiers})
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
        (get params "name")
        target-name
        (k/entityContextName context-name)
        this-name
        (get this-map "NAME")
        this-descriptors
        (get this-map "DESCRIPTORS")
        instantiation-descriptors
        (get this-descriptors "SYS+INSTANTIATION_DESCRIPTORSdescriptor")
        instantiation-descriptors
        (assoc instantiation-descriptors "SYS+INSTANTIATORdescriptor" this-name)
        instantiation-descriptors
        (into instantiation-descriptors (get params "descriptors"))
        instantiation-classifiers
        (get this-descriptors "SYS+INSTANTIATION_CLASSIFIERSdescriptor")
        ]
    (into params {"target_requestid" "SYS+REGISTER_ENTITYrequestid"
                  "target_name"      target-name
                  "descriptors"      instantiation-descriptors
                  "classifiers"      instantiation-classifiers})))

(defn instantiate-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (:operation-return-port params)]
      (try
        (let [context-request-port
              (get env "CONTEXT-REQUEST-PORT")
              route-params
              (instantiateOperation env this-map params)
              route-params
              (assoc route-params "requestid" "SYS+ROUTErequestid")]
          (a/>! operation-return-port [this-map nil :NO-RETURN])
          (a/>! context-request-port [env route-params]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn addDescriptorFunction
  [env this-map params]
  (let [descriptor
        (get params "descriptor")
        descriptor-value
        (get params "descriptor-value")
        old-descriptor-value
        (get-in this-map ["DESCRIPTORS" descriptor])
        _ (if (some? old-descriptor-value)
            (throw (Exception. (str "ADD DESCRIPTOR encountered a pre-existing value: " old-descriptor-value))))
        this-map
        (assoc-in this-map ["DESCRIPTORS" descriptor] descriptor-value)]
    (if (= descriptor "SYS+INVARIANTdescriptor")
      (if (= descriptor-value true)
        (k/add-invariant-map name this-map)))
    [this-map this-map]))

(defn addClassifierFunction
  [env this-map params]
  (let [this-name
        (get this-map "NAME")
        _ (if (s/blank? this-name)
            (throw (Exception. "ADD CLASSIFIER requires a name on the entity being assigned a classifier")))
        classifier
        (get params "classifier")
        classifier-value
        (get params "classifier-value")
        old-classifier-value
        (get-in this-map ["CLASSIFIERS" classifier])
        _ (if (some? old-classifier-value)
            (throw (Exception. (str "ADD CLASSIFIER encountered a pre-existing value: " old-classifier-value))))
        this-map
        (assoc-in this-map ["CLASSIFIERS" classifier] classifier-value)
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
              (get this-map "NAME")
              [name-kw context-base-name base-name]
              (kw/name-as-keyword this-name)
              context-name
              (k/entityContextName this-name)
              file-name
              (if (= context-base-name "SYS")
                (str "ae-vault/9ROOT/SYS/" this-name ".md")
                (str "ae-vault/9ROOT/" context-base-name "/" this-name ".md"))
              heading
              (str "# Entity " this-name "\n\n")
              report
              (str (r/front-matter this-map)
                   heading)]
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
