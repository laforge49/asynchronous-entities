(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
            [ae.later :as l]
            [ae.kernel :as k]
            [ae.names :as n]
            [ae.transform :as t]))

(defn register-entity-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [federated?
              (some? (get env "SYS+env-FEDERATORname&federator"))
              _ (if (not federated?)
                  (k/create-entity env params))
              this-name
              (get this-map "SYS+facet-NAME&%")
              name
              (get params "SYS+param-NAME&%")
              classifiers
              (get params "SYS+param-CLASSIFIERS_map^classifier")]
          (if (some? classifiers)
            (doseq [[classifier-kw classifier-value-kw] classifiers]
              (k/add-classifier-value this-name name classifier-kw classifier-value-kw)))
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn context-report-function
  [env this-map params]
  (let [this-name
        (get this-map "SYS+facet-NAME&%")
        file-name
        (str "ae-vault/9ROOT/CONTEXTS/" this-name ".md")
        heading
        (str "# Gem " this-name "\n\n")
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
        [_ context-base-name]
        (n/parse-into-2 this-name)
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
              (conj requests {"SYS+request-REQUEST_map^param"
                              {"SYS+param-REQUESTID&requestid" "SYS+requestid-GEMreport"
                               "SYS+param-TARGETname&%"        entity-name}})
              requests))
          []
          (k/get-entity-names))]
    (io/make-parents file-name)
    (spit file-name report)
    (l/push-later env requests)
    [this-map nil]))

(defn context-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [[this-map rv]
              (context-report-function env this-map params)]
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
              _ (if (= yaml-script "")
                (throw (Exception. (str script-path " is an empty file"))))
              edn-script
              (t/parse-bind-script yaml-script this-map env)
              this-map
              (assoc-in this-map
                        ["SYS+facet-DESCRIPTORS_map^descriptor"
                         "SYS+descriptor-SCRIPT_map^request"]
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
                      ["SYS+facet-DESCRIPTORS_map^descriptor"
                       "SYS+descriptor-SCRIPT_map^request"])
              requests
              (reduce
                (fn [requests [k v]]
                  (conj requests {k v}))
                []
                edn-script)]
          ;(println :requests (prn-str requests))
          (l/push-later env requests)
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
                      ["SYS+facet-DESCRIPTORS_map^descriptor"
                       "SYS+descriptor-SCRIPT_map^request"])]
          (t/validate-names edn-script "map" "request" nil nil nil env)
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn create-context-operations
  [env]
  (k/register-function env {:operationid "REGISTER_GEMoperationid"
                            :goblock     register-entity-goblock})
  (k/register-function env {:operationid "CONTEXT_REPORToperationid"
                            :function    context-report-function
                            :goblock     context-report-goblock})
  (k/register-function env {:operationid "LOAD_SCRIPToperationid"
                            :goblock     load-script-goblock})
  (k/register-function env {:operationid "EVAL_SCRIPToperationid"
                            :goblock     eval-script-goblock})
  (k/register-function env {:operationid "VALIDATE_SCRIPT_NAMESoperationid"
                            :goblock     validate-script-names-goblock})
  )
