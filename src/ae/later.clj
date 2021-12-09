(ns ae.later
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]))

(def exit-chan
  (a/chan 1))

(def active-count-atom (atom 0))

(def live-atom (atom true))

(defn go-later
  [env requests]
  (let [requeststackatom
        (atom [(seq requests)])
        env
        (assoc env "SYS+env_atmvec-requeststackatom$seq" requeststackatom)]
    (swap! active-count-atom inc)
    (a/go-loop []
      (if @live-atom
        (let [requeststack
              @requeststackatom]
          (if (empty? requeststack)
            (when (= (swap! active-count-atom dec) 0)
              (reset! live-atom false)
              (a/>! exit-chan [nil]))
            (do
              (try
                (let [requests
                      (peek requeststack)
                      _ (swap! requeststackatom pop)]
                  (if (not (empty? requests))
                    (let [request
                          (first requests)
                          nextrequests
                          (next requests)
                          _ (if (some? nextrequests)
                              (swap! requeststackatom conj (next requests)))
                          params
                          (get request "request_map-REQUEST^param")
                          target-name
                          (get params "SYS+param-TARGETname&?")
                          request-port
                          (k/get-public-request-port target-name)
                          subrequest-return-port
                          (a/chan)
                          params
                          (assoc params "SYS+param-RETURN$chan" subrequest-return-port)]
                      (a/>! request-port [env params])
                      (k/request-exception-check (a/<! subrequest-return-port)))))
                (catch Exception e
                  (a/>! exit-chan [e])
                  (reset! live-atom false)))
              (recur))))))))
