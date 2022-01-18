(ns ae.operations.util-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn println-function
  [env this-map params]
  (println (get params "SYS+param-TEXT$str"))
  [this-map nil])

(defn println-goblock
  [env this-map params]
  (a/go
    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (let [[this-map rv]
              (println-function env this-map params)]
          (a/>! operation-return-port [this-map nil]))
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))))

(defn create-util-operations
  [env]
  (k/register-function env {:operationid "PRINTLNoperationid"
                            :function    println-function
                            :goblock     println-goblock})
  )
