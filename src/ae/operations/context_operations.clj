(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-register-new-entity-operation
  [env]
  (let [new-entity-registration-port
        (k/register-operation-port env {:operation-portid :REGISTER-NEW-ENTITY-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! new-entity-registration-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)
            this-name
            (:NAME this-map)
            new-entity-name
            (:name params)]
        (if (s/blank? new-entity-name)
          (let [new-entity-public-request-port
                (k/create-entity env params)]
            (a/>! operation-return-port [this-map
                                         nil
                                         new-entity-public-request-port]))
          (let [[new-entity-kw _ _]
                (kw/name-as-keyword new-entity-name)]
            (if (some? (get-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw]))
              (a/>! operation-return-port [this-map
                                           (Exception. (str "Entity " new-entity-name " already exists in " this-name))
                                           nil])
              (let [
                    new-entity-public-request-port
                    (k/create-entity env params)
                    this-map
                    (assoc-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw] new-entity-public-request-port)]
                (a/>! operation-return-port [this-map
                                             nil
                                             new-entity-public-request-port])
                (recur)))))))))

(defn create-route-operation
  [env]
  (let [route-to-local-entity-port
        (k/register-operation-port env {:operation-portid :ROUTE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! route-to-local-entity-port)
            ;_ (println (prn-str :route params))
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)
            active-request-port
            (:active-request-port env)
            this-name
            (:NAME this-map)
            [_ _ this-base-name]
            (kw/name-as-keyword this-name)
            target-entity-name
            (:target-name params)
            [target-entity-kw target-context-base-name _]
            (kw/name-as-keyword target-entity-name)]
        (if (= this-name target-entity-name)
          (let [target-request
                (:target-requestid params)]
            (a/>! operation-return-port [this-map nil :NO-RETURN])
            (a/>! active-request-port [env
                                       (assoc params :requestid target-request)]))
          (if (= this-base-name target-context-base-name)
            (let [entity-public-request-ports
                  (:ENTITY-PUBLIC-REQUEST-PORTS this-map)
                  target-entity-request-port
                  (target-entity-kw entity-public-request-ports)
                  target-requestid
                  (:target-requestid params)]
              (a/>! operation-return-port [this-map
                                           (if (nil? target-entity-request-port)
                                             (Exception. (str "Entity " target-entity-name " is not registered in " this-name))
                                             nil)
                                           :NO-RETURN])
              (a/>! target-entity-request-port [env
                                                (assoc params :requestid target-requestid)]))
            (let [target-context-entity-kw
                  (keyword this-base-name target-context-base-name)
                  entity-public-request-ports
                  (:ENTITY-PUBLIC-REQUEST-PORTS this-map)
                  target-entity-request-port
                  (target-context-entity-kw entity-public-request-ports)]
              (a/>! operation-return-port [this-map
                                           (if (nil? target-entity-request-port)
                                             (Exception. (str "Entity " this-base-name "/" target-context-base-name " is not registered in " this-name))
                                             nil)
                                           :NO-RETURN])
              (a/>! target-entity-request-port [env
                                                (assoc params :requestid :ROUTE-REQUESTID)]))))
        (recur)))))

(defn acquire-loop
  [root-contexts-request-port env federation-names]
  (let [return-port
        (a/chan)]
    (a/go-loop [federation-names-vec (reverse (sort federation-names))
                federation-map {}]
      (if (some? federation-names-vec)
        (if (empty? federation-names-vec)
          (a/>! return-port [nil federation-map])
          (let [federation-name
                (peek federation-names-vec)
                federation-names-vec
                (pop federation-names-vec)
                [federation-names-vec federation-map]
                (try
                  (let [new-request-port
                        (a/chan)
                        subrequest-return-port
                        (a/chan)
                        _ (a/>! root-contexts-request-port [env {:requestid        :ROUTE-REQUESTID
                                                                 :target-requestid :PUSH-REQUEST-PORT
                                                                 :target-name      federation-name
                                                                 :new-request-port new-request-port
                                                                 :return-port      subrequest-return-port}])
                        snap
                        (k/request-exception-check (a/<! subrequest-return-port))
                        federation-map
                        (assoc federation-map federation-name [snap new-request-port])]
                    [federation-names-vec federation-map])
                  (catch Exception e
                    (a/>! return-port [e nil])
                    [nil nil]))]
            (recur federation-names-vec federation-map)))))
    return-port))

(defn create-acquire-operation
  [env]
  (let [acquire-port
        (k/register-operation-port env {:operation-portid :ACQUIRE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! acquire-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)]
        (try
          (let [federation-names
                (:federation-names params)
                root-contexts-request-port
                (:CONTEXT-REQUEST-PORT env)
                acquire-loop-port
                (acquire-loop root-contexts-request-port env federation-names)
                federation-map
                (k/request-exception-check (a/<! acquire-loop-port))
                this-map
                (assoc this-map :FEDERATION-MAP federation-map)]
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn release-loop
  [env federation-map]
  (let [return-port
        (a/chan)]
    (a/go-loop [federation-names-vec (into [] (sort (keys federation-map)))
                federation-map federation-map]
      (if (some? federation-names-vec)
        (if (empty? federation-names-vec)
          (a/>! return-port [nil true])
          (let [federation-name
                (peek federation-names-vec)
                federation-names-vec
                (pop federation-names-vec)
                [federation-names-vec federation-map]
                (try
                  (let [entity-request-port
                        (last (get federation-map federation-name))
                        subrequest-return-port
                        (a/chan)
                        _ (a/>! entity-request-port [env {:requestid   :POP-REQUEST-PORT
                                                          :return-port subrequest-return-port}])]
                    (k/request-exception-check (a/<! subrequest-return-port))
                    [federation-names-vec federation-map])
                  (catch Exception e
                    (a/>! return-port [e nil])
                    [nil nil]))]
            (recur federation-names-vec federation-map)))))
    return-port))

(defn create-release-operation
  [env]
  (let [release-port
        (k/register-operation-port env {:operation-portid :RELEASE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! release-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)]
        (try
          (let [root-contexts-request-port
                (:CONTEXT-REQUEST-PORT env)
                federation-map
                (:FEDERATION-MAP this-map)
                release-loop-port
                (release-loop env federation-map)
                _ (k/request-exception-check (a/<! release-loop-port))
                ]
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn create-context-operations
  [env]
  (create-register-new-entity-operation env)
  (create-route-operation env)
  (create-acquire-operation env)
  (create-release-operation env)
  )
