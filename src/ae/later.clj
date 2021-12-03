(ns ae.later
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]))

(def later-chan (a/chan 100))

(defn create-later
  [env exit-chan]
  (a/go
    (let [[env request]
          (a/<! later-chan)
          target-name
          (get request "SYS+param-TARGETname&?")
          request-port
          (k/get-public-request-port target-name)
          subrequest-return-port
          (a/chan)
          ]
      (println :request-port request-port))
    (a/>! exit-chan [nil]))
  )