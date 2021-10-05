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
          [context-request-port context-map]
          (k/create-entity env
                           {"name"        "ROOT+context-SYS"
                            "descriptors" {"SYS+descriptor_map-REQUESTID$operationid" {"SYS+requestid-REGISTER_ENTITY"     ["REGISTER_ENTITYoperationid"]
                                                                                      "SYS+requestid-ROUTE"               ["ROUTEoperationid"]
                                                                                      "SYS+requestid-ENTITY_REPORT"       ["CONTEXT_REPORToperationid"]
                                                                                      "SYS+requestid-LOAD_SCRIPT"         ["LOAD_SCRIPToperationid"]}}})
          env
          (assoc env "CONTEXT-REQUEST-PORT" context-request-port)
          script-path
          "scripts/ROOT+context-SYS.yml"
          yaml-script
          (slurp script-path)
          [e]
          (a/<!! (k/async-script script-path yaml-script context-map env))]
      (if (some? e)
        (throw e)))
    (catch Exception e
      (stacktrace/print-stack-trace e))))
