(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
            [ae.kernel :as k]
            [ae.later :as l]
            [ae.names :as n]))

(defn instantiateFunction
  [env this-map params]
  (let [federated?
        (some? (get env "SYS+env-FEDERATORname&federator"))
        name
        (get params "SYS+param-NAME&%")
        this-name
        (get this-map "SYS+facet-NAME&%")
        this-descriptors
        (get this-map "SYS+facet-DESCRIPTORS_map^descriptor")
        instantiation-descriptors
        (get this-descriptors "SYS+descriptor-INSTANCE_map^descriptor")
        instantiation-descriptors
        (k/merge-maps instantiation-descriptors (get params "SYS+param-DESCRIPTORS_map^descriptor"))
        instantiation-classifiers
        (get this-descriptors "SYS+descriptor_map-INSTANCE^classifier")
        instantiation-classifiers
        (assoc instantiation-classifiers "SYS+classifier-CLASS&class" this-name)
        instantiation-classifiers
        (into instantiation-classifiers (get params "SYS+param-CLASSIFIERS_map^classifier"))
        initialization-port
        (if federated?
          (a/chan)
          nil)
        params
        (into params {"SYS+param-INITIALIZATIONport"         initialization-port
                      "SYS+param-DESCRIPTORS_map^descriptor" instantiation-descriptors
                      "SYS+param-CLASSIFIERS_map^classifier" instantiation-classifiers})]
    (if federated?
      (do
        (k/create-entity env params)
        (vswap! (get env "SYS+env_volmap-CHILDREN&%") assoc name true))
      (let [params
            (into params {"SYS+param-REQUESTID&requestid" "SYS+requestid-REGISTERgem"
                          "SYS+param-TARGETname&%"        (k/entityContextName name)})
            #_ (println :register-name name this-name)
            requests
            [{"SYS+request-REQUEST_map^param" params}]]
        (l/push-later env requests)))
    [this-map this-map]))

(defn addDescriptorsFunction
  [env this-map params]
  (let [descriptors-map
        (get params "SYS+param-DESCRIPTORS_map^descriptor")
        this-map
        (reduce
          (fn [this-map [descriptor descriptor-value]]
            (let [old-descriptor-value
                  (get-in this-map ["SYS+facet-DESCRIPTORS_map^descriptor" descriptor])
                  _ (if (some? old-descriptor-value)
                      (throw (Exception. (str "ADD DESCRIPTOR encountered a pre-existing value: " old-descriptor-value))))
                  this-map
                  (assoc-in this-map ["SYS+facet-DESCRIPTORS_map^descriptor" descriptor] descriptor-value)]
              this-map))
          this-map
          descriptors-map)]
    [this-map this-map]))

(defn addRelationsFunction
  [env this-map params]
  (let [this-name
        (get this-map "SYS+facet-NAME&%")
        _ (if (s/blank? this-name)
            (throw (Exception. "ADD RELATIONS requires a name on the entities being assigned relations")))
        [this-context _]
        (n/parse-into-2 this-name)
        new-relations-map
        (get params "FED+param-RELATIONS_map^relation&%")
        this-map
        (reduce
          (fn [this-map [relation new-relation-values]]
            (if (not (vector? new-relation-values))
              (throw (Exception. (str "ADD RELATIONS given a non-vector value for relation " relation ": " (prn-str new-relation-values)))))
            (let [relation-values
                  (get-in this-map ["FED+facet-RELATIONS_map^relation&%" relation] [])
                  relation-values
                  (reduce
                    (fn [relation-values new-value]
                      (let [[value-context _]
                            (n/parse-into-2 new-value)
                            _ (if (not= this-context value-context)
                                (throw (Exception. (str "Relation subject and object must have same context: "
                                                        this-name " " value-context))))
                            obj-map
                            (k/get-federated-map new-value env)
                            _ (if (nil? obj-map)
                                (throw (Exception. (str "Federation is required by addRelations for object gem " new-value))))
                            relation-subjects
                            (get-in obj-map ["FED+facet-INVERSErelations_map^relation&%" relation] [])
                            i
                            (.indexOf relation-values new-value)
                            relation-values
                            (if (= i -1)
                              (let [obj-map
                                    (assoc-in obj-map
                                              ["FED+facet-INVERSErelations_map^relation&%" relation]
                                              (conj relation-subjects this-name))]
                                (k/assoc-federated-entity-map new-value obj-map env)
                                (conj relation-values new-value))
                              relation-values)]
                        relation-values))
                    relation-values
                    new-relation-values)]
              (assoc-in this-map ["FED+facet-RELATIONS_map^relation&%" relation] relation-values)))
          this-map
          new-relations-map)]
    [this-map this-map]))

(defn entity-report-function
  [env this-map params]
  (let [this-name
        (get this-map "SYS+facet-NAME&%")
        [context-base-name base-name]
        (n/parse-into-2 this-name)
        file-name
        (if (= context-base-name "SYS")
          (str "ae-vault/9ROOT/SYS/" base-name ".md")
          (str "ae-vault/9ROOT/" context-base-name "/" base-name ".md"))
        heading
        (str "# Gem " this-name "\n\n")
        content
        (get this-map "SYS+facet-CONTENT$str")
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
    [this-map nil]))

(defn entity-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [[this-map rv]
              (entity-report-function env this-map params)]
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn create-entity-operations
  [env]
  (k/register-function env {:operationid "INSTANTIATEoperationid"
                            :function    instantiateFunction})
  (k/register-function env {:operationid "ADD_DESCRIPTORSoperationid"
                            :function    addDescriptorsFunction})
  (k/register-function env {:operationid "ADD_RELATIONSoperationid"
                            :function    addRelationsFunction})
  (k/register-function env {:operationid "GEM_REPORToperationid"
                            :function    entity-report-function
                            :goblock     entity-report-goblock})
  )
