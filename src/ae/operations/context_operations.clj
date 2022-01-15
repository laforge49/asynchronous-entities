(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
            [ae.later :as l]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn registerEntityOperation
  [env this-map params]
  (let [this-name
        (get this-map "SYS+facet-NAME&%")
        name
        (get params "SYS+param-NAME&%")
        _ (if (some? (get params "SYS+param-INITIALIZATIONport"))
            (throw (Exception. (str "An initialization port is not compatible with non-federated registration of entity "
                                    name))))
        _ (if (not (k/entity-exists? name))
            (k/create-entity env params))
        classifiers
        (get params "SYS+param_map-CLASSIFIERS^classifier")]
    (if (some? classifiers)
      (doseq [[classifier-kw classifier-value-kw] classifiers]
        (k/add-classifier-value this-name name classifier-kw classifier-value-kw)))
    [this-map nil]))

(defn register-entity-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (a/>! operation-return-port (registerEntityOperation env this-map params))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn context-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [this-name
              (get this-map "SYS+facet-NAME&%")
              file-name
              (str "ae-vault/9ROOT/CONTEXTS/" this-name ".md")
              heading
              (str "# Entity " this-name "\n\n")
              content
              (get this-map "SYS+facet-CONTENT$str")
              content
              (if (= (count content) 0)
                ""
                (str content "\n\n---\n"))
              report
              (str (r/front-matter this-map env)
                   heading
                   content
                   (r/context-classifier-values-report 1 this-name))
              [_ _ context-base-name]
              (kw/name-as-keyword this-name)
              context-base-name
              (if (s/starts-with? context-base-name "context-")
                (subs context-base-name 8)
                context-base-name)
              context-prefix
              (str context-base-name "+")
              requests
              (reduce
                (fn [requests entity-name]
                  (if (s/starts-with? entity-name context-prefix)
                    (conj requests {"SYS+request_map-REQUEST^param"
                                    {"SYS+param-REQUESTID&requestid" "SYS+requestid-ENTITYreport"
                                     "SYS+param-TARGETname&%"        entity-name}})
                    requests))
                []
                (k/get-entity-names))]
          (io/make-parents file-name)
          (spit file-name report)
          (l/push-later env requests)
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn load-script-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [this-name
              (get this-map "SYS+facet-NAME&%")
              script-path
              (str "scripts/" this-name ".yml")
              yaml-script
              (slurp script-path)
              edn-script
              (k/parse-bind-script yaml-script this-map env)
              this-map
              (assoc-in this-map
                        ["SYS+facet_map-DESCRIPTORS^descriptor"
                         "SYS+descriptor_vecmap-SCRIPT^request"]
                        edn-script)]
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn eval-script-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [edn-script
              (get-in this-map
                      ["SYS+facet_map-DESCRIPTORS^descriptor"
                       "SYS+descriptor_vecmap-SCRIPT^request"])]
          (l/push-later env edn-script)
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn validate-script-names-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [edn-script
              (get-in this-map
                      ["SYS+facet_map-DESCRIPTORS^descriptor"
                       "SYS+descriptor_vecmap-SCRIPT^request"])]
          (doseq [request-map edn-script]
            (k/validate-names request-map "map" "request" nil nil env))
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn create-context-operations
  [env]
  (k/register-function env {:operationid "REGISTER_ENTITYoperationid"
                            :goblock     register-entity-goblock})
  (k/register-function env {:operationid "CONTEXT_REPORToperationid"
                            :goblock     context-report-goblock})
  (k/register-function env {:operationid "LOAD_SCRIPToperationid"
                            :goblock     load-script-goblock})
  (k/register-function env {:operationid "EVAL_SCRIPToperationid"
                            :goblock     eval-script-goblock})
  (k/register-function env {:operationid "VALIDATE_SCRIPT_NAMESoperationid"
                            :goblock     validate-script-names-goblock})
  )
