(ns ae.operations.util-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn println-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (println (get params "SYS+param-TEXT$str"))
        (a/>! operation-return-port [this-map nil])
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn create-util-operations
  [env]
  (k/register-function env {:operationid "PRINTLNoperationid"
                            :goblock     println-goblock})
  )
