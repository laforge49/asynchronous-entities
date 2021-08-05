(ns ae.core
  (:require [clojure.core.async :as a]
            [clojure.stacktrace :as stacktrace]
            [ae.kernel :as k]
            [ae.operations.context-operations :as co]
            [ae.operations.entity-operations :as eo]
            [ae.operations.federator-operations :as fo]
            [ae.script1 :as s1]
            ))

(defn create-operations
  [env]
  (co/create-context-operations env)
  (eo/create-entity-operations env)
  (fo/create-federator-operations env)
  )

(defn a-main
  []
  (let [main-in
        (a/chan)]
    (a/go
      (let [main-out
            (a/<! main-in)]
        (try
          (let [env
                {}
                _ (create-operations env)
                context-request-port
                (first (k/create-entity env {:name        "ROOT+SYSTEMcontext"
                                             :descriptors {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/REGISTER_ENTITYrequestid     [:REGISTER_ENTITYoperationid]
                                                                                    :SYSTEMcontext/ROUTErequestid               [:ROUTEoperationid]
                                                                                    :SYSTEMcontext/REGISTER_CLASSIFIERrequestid [:REGISTER_CLASSIFIERoperationid]
                                                                                    :SYSTEMcontext/ENTITY_REPORTrequestid       [:CONTEXT_REPORToperationid]}}
                                             }))
                env
                (assoc env :CONTEXT-REQUEST-PORT context-request-port)
                return-port0
                (a/chan)
                _ (doseq [request-params s1/script1]
                    (let [request-params
                          (assoc request-params :requestid :SYSTEMcontext/ROUTErequestid)
                          request-params
                          (assoc request-params :return_port return-port0)]
                      (a/>! context-request-port [env request-params])
                      (k/request-exception-check (a/<! return-port0))))
                ]
            (a/>! main-out [nil []]))
          (catch Exception e
            (a/>! main-out [e nil])))))
    main-in))

(defn -main
  [& args]
  (println "go!")
  (try
    (let [main-in
          (a-main)
          main-out
          (a/chan)]
      (a/>!! main-in main-out)
      (doseq [s (k/request-exception-check (a/<!! main-out))]
        (println "\n" (pr-str s))))
    (catch Exception e
      (stacktrace/print-stack-trace e))))
