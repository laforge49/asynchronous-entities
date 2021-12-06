(ns ae.later
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]))

(def exit-chan
  (a/chan 1))

(defn go-later
  [env requests]
  (a/go-loop [[more env requests] [true env requests]]
    (if more
      (if (nil? requests)
        (a/>! exit-chan [nil])
        (recur (try
                 (let [request
                       (first requests)
                       params
                       (get request "request_map-REQUEST^param")
                       requests
                       (next requests)
                       target-name
                       (get params "SYS+param-TARGETname&?")
                       request-port
                       (k/get-public-request-port target-name)
                       subrequest-return-port
                       (a/chan)
                       params
                       (assoc params "SYS+param-RETURN$chan" subrequest-return-port)]
                   (a/>! request-port [env params])
                   (k/request-exception-check (a/<! subrequest-return-port))
                   [true env requests])
                 (catch Exception e
                   (a/>! exit-chan [e])
                   [false nil nil])))))))