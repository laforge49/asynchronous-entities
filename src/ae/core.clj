(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [clojure.java.io :as io]
            [ae.kernel :as k]
            [ae.later :as l]
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
                           {"SYS+param-NAME&?"                     "ROOT+context-SYS"
                            "SYS+param_map-DESCRIPTORS^descriptor" {"SYS+descriptor_mapvec-REQUESTS^requestid$str"
                                                                    {"SYS+requestid-REGISTERentity" ["REGISTER_ENTITYoperationid"]
                                                                     "SYS+requestid-ROUTE"          ["ROUTEoperationid"]
                                                                     "SYS+requestid-ENTITYreport"   ["CONTEXT_REPORToperationid"]
                                                                     "SYS+requestid-LOADscript"     ["LOAD_SCRIPToperationid"]}}})
          exit-chan
          (a/chan 1)
          _ (l/create-later env exit-chan)
          e
          (first (a/<!! exit-chan))
          _ (if (some? e)
              (throw e))
          subrequest-return-port
          (a/chan)
          _ (a/>!! context-request-port [env {"SYS+param-REQUESTID"   "SYS+requestid-LOADscript"
                                              "SYS+param-NAME&?"      "ROOT+context-SYS"
                                              "SYS+param-RETURN$chan" subrequest-return-port}])
          _ (k/request-exception-check (a/<!! subrequest-return-port))])
    (catch Exception e
      (stacktrace/print-stack-trace e))))
