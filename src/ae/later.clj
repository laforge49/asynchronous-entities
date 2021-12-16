(ns ae.later
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]))

(def exit-chan
  (a/chan 1))

(def active-count-atom- (atom 0))

(def live-atom- (atom true))

(defn validate-requests-
  [requests]
  (if (not (vector? requests))
    (throw (Exception. (str "Requests is not a vector: " (prn-str requests)))))
  (if (= (count requests) 0)
    (throw (Exception. (str "Requests is empty: " (prn-str requests))))))

(defn push-later
  [env requests]
  (validate-requests- requests)
  (let [requestvecatom
        (get env "SYS+env_atmvec-requestvecatom$?")]
    (if (nil? requestvecatom)
      (throw (Exception. (str "unable to locate SYS+env_atmvec-requestvecatom$?\n"
                              (prn-str :env env)))))
    (swap! requestvecatom (fn [[request-on-deck requestseq-stack]]
                            [request-on-deck (conj requestseq-stack (seq requests))]))))

(defn pop-request-
  [env]
  (let [requestvecatom
        (get env "SYS+env_atmvec-requestvecatom$?")]
    (swap! requestvecatom (fn [[request-on-deck requestseq-stack]]
                            (if (empty? requestseq-stack)
                              [nil []]
                              (let [requestseq
                                    (peek requestseq-stack)
                                    requestseq-stack
                                    (pop requestseq-stack)]
                                (if (nil? requestseq)
                                  [nil requestseq-stack]
                                  (let [request-on-deck
                                        (first requestseq)
                                        requestseq
                                        (next requestseq)
                                        requestseq-stack
                                        (if (nil? requestseq)
                                          requestseq-stack
                                          (conj requestseq-stack requestseq))]
                                    [request-on-deck requestseq-stack]))))))
    (first @requestvecatom)))

(defn go-later
  [env requests]
  (if (some? (get env "SYS+env_atmvec-requestvecatom$?"))
    (throw (Exception. (str "SYS+env_atmvec-requeststackatom$seq is already present in env"))))
  (validate-requests- requests)
  (let [requestvecatom
        (atom [nil [(seq requests)]])
        env
        (assoc env "SYS+env_atmvec-requeststackatom$seq" requeststackatom)]
    (swap! active-count-atom- inc)
    (a/go-loop []
      (if @live-atom-
        (let [requeststack
              @requeststackatom]
          (if (empty? requeststack)
            (when (= (swap! active-count-atom- dec) 0)
              (reset! live-atom- false)
              (a/>! exit-chan [nil]))
            (do
              (try
                (let [requests
                      (peek requeststack)
                      _ (swap! requeststackatom pop)]
                  (if (and (not (empty? requests)) (not (empty? (first requests))))
                    (let [request
                          (first requests)
                          nextrequests
                          (next requests)
                          _ (if (some? nextrequests)
                              (swap! requeststackatom conj nextrequests))
                          params
                          (get request "SYS+request_map-REQUEST^param")
                          target-name
                          (get params "SYS+param-TARGETname&?")
                          request-port
                          (k/get-public-request-port target-name)
                          subrequest-return-port
                          (a/chan)
                          params
                          (assoc params "SYS+param-RETURN$chan" subrequest-return-port)]
                      (if (nil? request-port) (println request))
                      (a/>! request-port [env params])
                      (k/request-exception-check (a/<! subrequest-return-port)))))
                (catch Exception e
                  (a/>! exit-chan [e])
                  (reset! live-atom- false)))
              (recur))))))))
