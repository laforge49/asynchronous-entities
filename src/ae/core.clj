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
          [_ context-map]
          (k/create-entity env
                           {"SYS+param-NAME&?"                       "ROOT+context-SYS"
                            "SYS+param_map-DESCRIPTORS^descriptor" {"SYS+descriptor_mapvec-REQUESTS^requestid$str" {"SYS+requestid-REGISTERentity" ["REGISTER_ENTITYoperationid"]
                                                                                                                 "SYS+requestid-ROUTE"          ["ROUTEoperationid"]
                                                                                                                 "SYS+requestid-ENTITYreport"   ["CONTEXT_REPORToperationid"]
                                                                                                                 "SYS+requestid-LOADscript"     ["LOAD_SCRIPToperationid"]}}})
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
