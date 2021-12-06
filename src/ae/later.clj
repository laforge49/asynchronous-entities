(ns ae.later
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]))

(def later-chan (a/chan 100))

(defn create-later
  [env exit-chan]
  (a/go-loop [[more env requests] [true nil nil]]
    (if more
      (let [[[env requests] c]
            (a/alts! [later-chan] :default [nil nil])]
        (if (nil? requests)
          (a/>! exit-chan [nil])
          (recur (try
                   (let [request
                         (first requests)
                         requests
                         (next requests)
                         target-name
                         (get request "SYS+param-TARGETname&?")
                         request-port
                         (k/get-public-request-port target-name)
                         subrequest-return-port
                         (a/chan)
                         request
                         (assoc request "SYS+param-RETURN$chan" subrequest-return-port)]
                     (a/>! request-port [env request])
                     (k/request-exception-check (a/<! subrequest-return-port)))
                   [true env nil]
                   (catch Exception e
                     (a/>! exit-chan [e])
                     [false nil nil]))))))))