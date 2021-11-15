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
        (get params "SYS+param-NAME&?")
        this-name
        (get this-map "NAME")
        this-descriptors
        (get this-map "DESCRIPTORS")
        instantiation-descriptors
        (get this-descriptors "SYS+descriptor_map-INSTANCE^descriptor")
        instantiation-descriptors
        (into instantiation-descriptors (get params "SYS+param_map-DESCRIPTORS^descriptor"))
        instantiation-classifiers
        (get this-descriptors "SYS+descriptor_map-INSTANCE^classifier")
        instantiation-classifiers
        (assoc instantiation-classifiers "SYS+classifier-CLASS" this-name)
        instantiation-classifiers
        (into instantiation-classifiers (get params "SYS+param_map-CLASSIFIERS^classifier"))
        initialization-port
        (a/chan)
        params
        (into params {"SYS+param-TARGETname&?"                 name
                      "SYS+param-INITIALIZATIONport"                  initialization-port
                      "SYS+param_map-DESCRIPTORS^descriptor" instantiation-descriptors
                      "SYS+param_map-CLASSIFIERS^classifier" instantiation-classifiers})
        [new-entity-public-request-port snap]
        (k/create-entity env params)
        new-children-volatile
        (get env "NEW-CHILDREN-VOLATILE")
        ]
    (k/assoc-entity-map name snap)
    (vswap! new-children-volatile assoc name new-entity-public-request-port)
    [this-map this-map]))

(defn instantiateOperation
  [env this-map params]
  (let [context-name
        (get params "SYS+param-NAME&?")
        target-name
        (k/entityContextName context-name)
        this-name
        (get this-map "NAME")
        this-descriptors
        (get this-map "DESCRIPTORS")
        instantiation-descriptors
        (get this-descriptors "SYS+descriptor_map-INSTANCE^descriptor")
        instantiation-descriptors
        (into instantiation-descriptors (get params "SYS+param_map-DESCRIPTORS^descriptor"))
        instantiation-classifiers
        (get this-descriptors "SYS+descriptor_map-INSTANCE^classifier")
        instantiation-classifiers
        (assoc instantiation-classifiers "SYS+classifier-CLASS" this-name)
        instantiation-classifiers
        (into instantiation-classifiers (get params "SYS+param_map-CLASSIFIERS^classifier"))]
    (into params {"SYS+param-TARGET&requestid"            "SYS+requestid-REGISTERentity"
                  "SYS+param-TARGETname&?"                 target-name
                  "SYS+param_map-DESCRIPTORS^descriptor" instantiation-descriptors
                  "SYS+param_map-CLASSIFIERS^classifier" instantiation-classifiers})))

(defn instantiate-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [context-request-port
              (get env "CONTEXT-REQUEST-PORT")
              route-params
              (instantiateOperation env this-map params)
              route-params
              (assoc route-params "SYS+param-REQUESTID" "SYS+requestid-ROUTE")]
          (a/>! operation-return-port [this-map nil :NO-RETURN])
          (a/>! context-request-port [env route-params]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn addDescriptorsFunction
  [env this-map params]
  (let [descriptors-map
        (get params "SYS+param_map-DESCRIPTORS^descriptor")
        this-map
        (reduce
          (fn [this-map [descriptor descriptor-value]]
            (let [old-descriptor-value
                  (get-in this-map ["DESCRIPTORS" descriptor])
                  _ (if (some? old-descriptor-value)
                      (throw (Exception. (str "ADD DESCRIPTOR encountered a pre-existing value: " old-descriptor-value))))
                  this-map
                  (assoc-in this-map ["DESCRIPTORS" descriptor] descriptor-value)]
              this-map))
          this-map
          descriptors-map)]
    [this-map this-map]))

(defn addRelationsFunction
  [env this-map params]
  (let [this-name
        (get this-map "NAME")
        _ (if (s/blank? this-name)
            (throw (Exception. "ADD RELATIONS requires a name on the entities being assigned a classifier")))
        [_ this-context _]
        (kw/name-as-keyword this-name)
        relations-map
        (get params "SYS+param_map-relations^relation&?")
        federation-vmap
        (get env "FEDERATION-MAP-VOLATILE")
        this-map
        (reduce
          (fn [this-map [relation new-relation-values]]
            (if (not (vector? new-relation-values))
              (throw (Exception. (str "ADD RELATIONS given a non-vector value for relation " relation ": " (prn-str new-relation-values)))))
            (let [relation-values
                  (get-in this-map ["RELATIONS" relation] [])
                  relation-values
                  (reduce
                    (fn [relation-values new-value]
                      (let [[_ value-context _]
                            (kw/name-as-keyword new-value)
                            _ (if (not= this-context value-context)
                                (throw (Exception. (str "Relation subject and object must have same context: "
                                                        this-name " " value-context))))
                            obj-map
                            (k/get-federated-map new-value env)
                            _ (if (nil? obj-map)
                                (throw (Exception. (str "Federation is required by addRelations for object " new-value))))
                            relation-subjects
                            (get-in obj-map ["INVERSE-RELATIONS" relation] [])
                            i
                            (.indexOf relation-values new-value)
                            relation-values
                            (if (= i -1)
                              (let [obj-map
                                    (assoc-in obj-map ["INVERSE-RELATIONS" relation] (conj relation-subjects this-name))]
                                (k/assoc-entity-map new-value obj-map)
                                (conj relation-values new-value))
                              relation-values)]
                        relation-values))
                    relation-values
                    new-relation-values)]
              (assoc-in this-map ["RELATIONS" relation] relation-values)))
          this-map
          relations-map)]
    [this-map this-map]))

(defn entity-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [this-name
              (get this-map "NAME")
              [name-kw context-base-name base-name]
              (kw/name-as-keyword this-name)
              file-name
              (if (= context-base-name "SYS")
                (str "ae-vault/9ROOT/SYS/" base-name ".md")
                (str "ae-vault/9ROOT/" context-base-name "/" base-name ".md"))
              heading
              (str "# Entity " this-name "\n\n")
              content
              (get this-map "CONTENT$ml")
              content
              (if (= (count content) 0)
                ""
                (str content "\n"))
              report
              (str (r/front-matter this-map env)
                   heading
                   content)]
          (io/make-parents file-name)
          (spit file-name report)
          (a/>! operation-return-port [this-map nil this-map]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn typeOfFunction
  [env this-map params]
  [this-map [(get-in this-map ["DESCRIPTORS" "SYS+descriptorDATA_TYPE"])
             (get-in this-map ["DESCRIPTORS" "SYS+descriptorKEY_ENTITY"])
             (get-in this-map ["DESCRIPTORS" "SYS+descriptorVALUE_ENTITY"])]])

(defn create-entity-operations
  [env]
  (k/register-function env {:operationid "INSTANTIATEoperationid"
                            :function    instantiateFunction
                            :goblock     instantiate-goblock})
  (k/register-function env {:operationid "ADD_DESCRIPTORSoperationid"
                            :function    addDescriptorsFunction})
  (k/register-function env {:operationid "ADD_RELATIONSoperationid"
                            :function    addRelationsFunction})
  (k/register-function env {:operationid "ENTITY_REPORToperationid"
                            :goblock     entity-report-goblock})
  (k/register-function env {:operationid "operationidTYPE_OF"
                            :function    typeOfFunction})
  )
