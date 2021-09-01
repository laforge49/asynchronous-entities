(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [clojure.java.io :as io]
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
                                                                       "SYS+ENTITY_REPORTrequestid"       ["CONTEXT_REPORToperationid"]
                                                                       "SYS+LOAD_SCRIPTrequestid"         ["LOAD_SCRIPToperationid"]}}}))
          env
          (assoc env "CONTEXT-REQUEST-PORT" context-request-port)
          boot-script-path
          "./scripts/SYS.yml"
          yaml-script
          (slurp boot-script-path)
          [e]
          (a/<!! (k/async-script yaml-script "SYS" env))]
      (if (some? e)
        (throw e)))
    (catch Exception e
      (stacktrace/print-stack-trace e))))
