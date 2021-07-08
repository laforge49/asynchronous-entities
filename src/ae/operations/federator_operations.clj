(ns ae.operations.federator-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-run-federation-operation
  [env]
  (let [new-run-federation-port
        (k/register-operation-port env {:operation-portid :RUN-FEDERATION-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! new-run-federation-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)]
        )
      (recur))))

(defn create-federator-operations
  [env]
  (create-run-federation-operation env))
