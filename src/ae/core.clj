(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [clojure.java.io :as io]
            [tupelo.parse.yaml :as yaml]
            [ae.kernel :as k]
            [ae.operations.context-operations :as co]
            [ae.operations.entity-operations :as eo]
            [ae.operations.federator-operations :as fo]
            ))

(defn create-operations
  [env]
  (co/create-context-operations env)
  (eo/create-entity-operations env)
  (fo/create-federator-operations env))

(defn async-script
  [yaml-script env]
  (let [out
        (a/chan)]
    (a/go
      (try
        (let [edn-script
              (yaml/parse-raw yaml-script)
              return-port0
              (a/chan)
              context-request-port
              (get env "CONTEXT-REQUEST-PORT")]
          (doseq [request-params edn-script]
            (let [request-params
                  (assoc request-params "requestid" "SYS+ROUTErequestid")
                  request-params
                  (assoc request-params "return_port" return-port0)]
              (a/>! context-request-port [env request-params])
              (k/request-exception-check (a/<! return-port0)))))
        (a/>! out [nil])
        (catch Exception e
          (a/>! out [e]))))
    out))

(defn -main
  [& args]
  (println "go!")
  (try
    (let [env
          {}
          _ (create-operations env)
          context-request-port
          (first (k/create-entity env
                                  {"name"        "SYS"
                                   "descriptors" {"SYS+REQUESTID_MAP" {"SYS+REGISTER_ENTITYrequestid"     ["REGISTER_ENTITYoperationid"]
                                                                       "SYS+ROUTErequestid"               ["ROUTEoperationid"]
                                                                       "SYS+REGISTER_CLASSIFIERrequestid" ["REGISTER_CLASSIFIERoperationid"]
                                                                       "SYS+ENTITY_REPORTrequestid"       ["CONTEXT_REPORToperationid"]}}}))
          env
          (assoc env "CONTEXT-REQUEST-PORT" context-request-port)
          boot-script-path
          "./scripts/boot-script.yml"
          yaml-script
          (slurp boot-script-path)
          [e]
          (a/<!! (async-script yaml-script env))]
      (if (some? e)
        (throw e)))
    (catch Exception e
      (stacktrace/print-stack-trace e))))
