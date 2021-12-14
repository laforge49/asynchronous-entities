(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [clojure.java.io :as io]
            [ae.kernel :as k]
            [ae.later :as l]
            [ae.operations.context-operations :as co]
            [ae.operations.entity-operations :as eo]
            [ae.operations.federator-operations :as fo]))


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
          (k/create-entity env {"SYS+param-NAME&?"
                                "ROOT+context-SYS"
                                "SYS+param_map-DESCRIPTORS^descriptor"
                                {"SYS+descriptor_mapvec-REQUESTS^requestid$str"
                                 {"SYS+requestid-REGISTERentity"      ["REGISTER_ENTITYoperationid"]
                                  "SYS+requestid-ENTITYreport"        ["CONTEXT_REPORToperationid"]
                                  "SYS+requestid-LOADscript"          ["LOAD_SCRIPToperationid"]
                                  "SYS+requestid-EVALscript"          ["EVAL_SCRIPToperationid"]
                                  "SYS+requestid-VALIDATEscriptNAMES" ["VALIDATE_SCRIPT_NAMESoperationid"]}}})
          _ (l/go-later env [{"SYS+request_map-REQUEST^param" {"SYS+param-REQUESTID&requestid" "SYS+requestid-LOADscript"
                                                               "SYS+param-TARGETname&?"        "ROOT+context-SYS"}}])
          _ (l/go-later env [{"SYS+request_map-REQUEST^param" {"SYS+param-REQUESTID&requestid" "SYS+requestid-EVALscript"
                                                               "SYS+param-TARGETname&?"        "ROOT+context-SYS"}}])
          _ (l/go-later env [{"SYS+request_map-REQUEST^param" {"SYS+param-REQUESTID&requestid" "SYS+requestid-VALIDATEscriptNAMES"
                                                               "SYS+param-TARGETname&?"        "ROOT+context-SYS"}}])
          e
          (first (a/<!! l/exit-chan))
          _ (if (some? e)
              (throw e))])
    (catch Exception e
      (stacktrace/print-stack-trace e))))
