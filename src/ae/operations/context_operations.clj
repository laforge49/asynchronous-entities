(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn registerEntityOperation
  [env this-map params]
  (let [this-name
        (get this-map "SYS+facet-NAME&?")
        name
        (get params "SYS+param-NAME&?")
        _ (if (some? (get params "SYS+param-INITIALIZATIONport"))
            (throw (Exception. (str "An initialization port is not compatible with non-federated registration of entity "
                                    name))))
        entity-public-request-port
        (get params "SYS+param-ENTITYpublicREQUESTPORT")
        entity-public-request-port
        (if (some? entity-public-request-port)
          entity-public-request-port
          (first (k/create-entity env params)))]
    (let [classifiers
          (get params "SYS+param_map-CLASSIFIERS^classifier")]
      (if (some? classifiers)
        (doseq [[classifier-kw classifier-value-kw] classifiers]
          (k/add-classifier-value this-name name classifier-kw classifier-value-kw)))
      [this-map nil entity-public-request-port])))

(defn register-entity-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (a/>! operation-return-port (registerEntityOperation env this-map params))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn context-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [this-name
              (get this-map "SYS+facet-NAME&?")
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
              entities-map
              @k/entities-map-atom]
          (io/make-parents file-name)
          (spit file-name report)
          (doseq [[entity-name entity-map] entities-map]
            (if (s/starts-with? entity-name context-prefix)
              (let [request-port-stack
                    (get entity-map "SYS+facet_vec-REQUESTportSTACK$chan")
                    entity-port
                    (first request-port-stack)
                    subrequest-return-port
                    (a/chan)]
                (a/>! entity-port [env {"SYS+param-REQUESTID&requestid"   "SYS+requestid-ENTITYreport"
                                        "SYS+param-RETURN$chan" subrequest-return-port}])
                (k/request-exception-check (a/<! subrequest-return-port)))))
          (a/>! operation-return-port [this-map nil this-map]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn load-script-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")
          return-port
          (get params "SYS+param-RETURN$chan")]
      (try
        (let [this-name
              (get this-map "SYS+facet-NAME&?")
              script-path
              (str "scripts/" this-name ".yml")
              yaml-script
              (slurp script-path)
              edn-script
              (k/parse-bind-script yaml-script this-map env)
              this-map
              (assoc-in this-map
                        ["SYS+facet_map-DESCRIPTORS^descriptor" "SYS+descriptor_vecmap-SCRIPT^request"]
                        edn-script)]
          (a/>! operation-return-port [this-map nil nil]))
        (catch Exception e
          (a/>! return-port [e nil]))))))

(defn eval-script-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")
          return-port
          (get params "SYS+param-RETURN$chan")]
      (a/>! operation-return-port [this-map nil :NO-RETURN])
      (try
        (let [edn-script
              (get-in this-map
                      ["SYS+facet_map-DESCRIPTORS^descriptor" "SYS+descriptor_vecmap-SCRIPT^request"])
              [e]
              (a/<! (k/eval-async-script edn-script env))]
          (if (some? e)
            (throw e))
          (doseq [request-map edn-script]
            (k/validate-names request-map "map" "request" nil nil env))
          (a/>! return-port [nil nil]))
        (catch Exception e
          (a/>! return-port [e nil]))))))

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
  )
