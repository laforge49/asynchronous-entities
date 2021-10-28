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
        (get this-map "NAME")
        name
        (get params "name")
        _ (if (some? (get params "initialization-port"))
            (throw (Exception. (str "An initialization port is not compatible with non-federated registration of entity "
                                    name))))
        entity-public-request-port
        (get params "entity-public-request-port")
        entity-public-request-port
        (if (some? entity-public-request-port)
          entity-public-request-port
          (first (k/create-entity env params)))]
    (if (s/blank? name)
      [this-map nil entity-public-request-port]
      (let [[new-entity-kw _ _]
            (kw/name-as-keyword name)
            _ (if (some? (get-in this-map ["ENTITY-PUBLIC-REQUEST-PORTS" new-entity-kw]))
                (throw (Exception. (str "Entity " name " already exists in " this-name))))
            this-map
            (assoc-in this-map ["ENTITY-PUBLIC-REQUEST-PORTS" new-entity-kw] entity-public-request-port)
            classifiers
            (get params "classifiers")]
        (if (some? classifiers)
          (doseq [[classifier-kw classifier-value-kw] classifiers]
            (k/add-classifier-value this-name name classifier-kw classifier-value-kw)))
        [this-map nil entity-public-request-port]))))

(defn register-entity-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "operation-return-port")]
      (try
        (a/>! operation-return-port (registerEntityOperation env this-map params))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn route-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "operation-return-port")]
      (try
        (let [active-request-port
              (get env "active-request-port")
              this-name
              (get this-map "NAME")
              _ (if (nil? this-name)
                  (throw (Exception. (str "NAME is nil\n"
                                          (prn-str params)
                                          (prn-str this-map)))))
              _ (if (not (string? this-name))
                  (throw (Exception. (str "NAME is not a string\n"
                                          (prn this-name)
                                          (prn-str params)
                                          (prn-str this-map)))))
              [_ _ local-context]
              (kw/name-as-keyword this-name)
              local-context
              (if (s/starts-with? local-context "context-")
                (subs local-context 8)
                (throw (Exception. (str "unrecognized context: " local-context))))
              target-entity-name
              (get params "target_name")
              _ (if (nil? target-entity-name)
                  (throw (Exception. (str "target_name is nil\n"
                                          (prn-str params)
                                          (prn-str this-map)))))
              _ (if (not (string? target-entity-name))
                  (throw (Exception. (str "target_name is not a string\n"
                                          (prn-str target-entity-name)
                                          (prn-str params)
                                          (prn-str this-map)))))
              [target-entity-kw target-context-base-name _]
              (kw/name-as-keyword target-entity-name)]
          (if (= this-name target-entity-name)
            (let [target-requestid
                  (get params "target_requestid")]
              (a/>! operation-return-port [this-map nil :NO-RETURN])
              (a/>! active-request-port [env
                                         (assoc params "requestid" target-requestid)]))
            (if (= local-context target-context-base-name)
              (let [entity-public-request-ports
                    (get this-map "ENTITY-PUBLIC-REQUEST-PORTS")
                    target-entity-request-port
                    (target-entity-kw entity-public-request-ports)
                    target-requestid
                    (get params "target_requestid")]
                (if (nil? target-entity-request-port)
                  (throw (Exception. (str "Entity " target-entity-name " is not registered in " this-name "\n"
                                          (prn-str params)))))
                (a/>! operation-return-port [this-map
                                             nil
                                             :NO-RETURN])
                (a/>! target-entity-request-port [env
                                                  (assoc params "requestid" target-requestid)]))
              (let [target-context-entity-kw
                    (keyword "SYS" (str "context-" target-context-base-name))
                    entity-public-request-ports
                    (get this-map "ENTITY-PUBLIC-REQUEST-PORTS")
                    target-entity-request-port
                    (target-context-entity-kw entity-public-request-ports)]
                (if (nil? target-entity-request-port)
                  (throw (Exception. (str "Entity " local-context "+context-" target-context-base-name " is not registered in " this-name))))
                (a/>! operation-return-port [this-map
                                             nil
                                             :NO-RETURN])
                (a/>! target-entity-request-port [env
                                                  (assoc params "requestid" "SYS+requestid-ROUTE")])))))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn context-report-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "operation-return-port")]
      (try
        (let [this-name
              (get this-map "NAME")
              [_ _ base-name]
              (kw/name-as-keyword this-name)
              file-name
              (str "ae-vault/9ROOT/CONTEXTS/" this-name ".md")
              heading
              (str "# Entity " this-name "\n\n")
              content
              (get this-map "CONTENT$ml")
              content
              (if (= (count content) 0)
                ""
                (str content "\n\n---\n"))
              report
              (str (r/front-matter this-map env)
                   heading
                   content
                   (r/context-entities-report 1 this-name this-map)
                   (r/context-classifier-values-report 2 this-name))
              entity-ports
              (get this-map "ENTITY-PUBLIC-REQUEST-PORTS")]
          (io/make-parents file-name)
          (spit file-name report)
          (doseq [[entity-kw entity-port] entity-ports]
            (let [subrequest-return-port
                  (a/chan)]
              (a/>! entity-port [env {"requestid"   "SYS+requestid-ENTITYreport"
                                      "return_port" subrequest-return-port}])
              (k/request-exception-check (a/<! subrequest-return-port))
              )
            )
          (a/>! operation-return-port [this-map nil this-map]))
        (catch Exception e
          (a/>! operation-return-port [this-map e nil]))))))

(defn load-script-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "operation-return-port")
          return-port
          (get params "return_port")]
      (a/>! operation-return-port [this-map nil :NO-RETURN])
      (try
        (let [this-name
              (get this-map "NAME")
              script-path
              (str "scripts/" this-name ".yml")
              yaml-script
              (slurp script-path)
              [e]
              (a/<! (k/async-script script-path yaml-script this-map env))]
          (if (some? e)
            (throw e))
          (a/>! return-port [nil nil]))
        (catch Exception e
          (a/>! return-port [e nil]))))))

(defn create-context-operations
  [env]
  (k/register-function env {:operationid "REGISTER_ENTITYoperationid"
                            :goblock     register-entity-goblock})
  (k/register-function env {:operationid "ROUTEoperationid"
                            :goblock     route-goblock})
  (k/register-function env {:operationid "CONTEXT_REPORToperationid"
                            :goblock     context-report-goblock})
  (k/register-function env {:operationid "LOAD_SCRIPToperationid"
                            :goblock     load-script-goblock})
  )
