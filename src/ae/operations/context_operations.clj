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
            (:this-map env)]
        (try
          (let [this-name
                (:NAME this-map)
                new-entity-name
                (:name params)]
            (if (some? (:initialization-port params))
              (throw (Exception. (str "An initialization port is not compatible with registration of entity "
                                      new-entity-name))))
            (if (s/blank? new-entity-name)
              (let [new-entity-public-request-port
                    (k/create-entity env params)]
                (a/>! operation-return-port [this-map nil new-entity-public-request-port]))
              (let [[new-entity-kw _ _]
                    (kw/name-as-keyword new-entity-name)
                    _ (if (some? (get-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw]))
                        (throw (Exception. (str "Entity " new-entity-name " already exists in " this-name))))
                    new-entity-public-request-port
                    (k/create-entity env params)
                    this-map
                    (assoc-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw] new-entity-public-request-port)]
                (a/>! operation-return-port [this-map nil new-entity-public-request-port]))))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-route-operation
  [env]
  (let [route-to-local-entity-port
        (k/register-operation-port env {:operation-portid :ROUTE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! route-to-local-entity-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)]
        (try
          (let [active-request-port
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
                  (if (nil? target-entity-request-port)
                    (throw (Exception. (str "Entity " target-entity-name " is not registered in " this-name))))
                  (a/>! operation-return-port [this-map
                                               nil
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
                                                    (assoc params :requestid :ROUTE-REQUESTID)])))))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-federation-route-operation
  [env]
  (let [federation-route-port
        (k/register-operation-port env {:operation-portid :FEDERATION-ROUTE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! federation-route-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)]
        (try
          (let [federation-map
                (:FEDERATION-MAP this-map)
                target-entity-name
                (:target-name params)
                target-entity-request-port
                (get federation-map target-entity-name)
                _ (if (nil? target-entity-request-port)
                    (throw (Exception. (str "Entity " target-entity-name " is not federated"))))
                target-requestid
                (:target-requestid params)]
            (a/>! operation-return-port [this-map nil :NO-RETURN])
            (a/>! target-entity-request-port [env
                                              (assoc params :requestid target-requestid)]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn federation-acquire-loop
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
                        (assoc federation-map federation-name new-request-port)]
                    [federation-names-vec federation-map])
                  (catch Exception e
                    (a/>! return-port [e nil])
                    [nil nil]))]
            (recur federation-names-vec federation-map)))))
    return-port))

(defn create-federation-acquire-operation
  [env]
  (let [federation-acquire-port
        (k/register-operation-port env {:operation-portid :FEDERATION-ACQUIRE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! federation-acquire-port)
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
                (federation-acquire-loop root-contexts-request-port env federation-names)
                federation-map
                (k/request-exception-check (a/<! acquire-loop-port))
                this-map
                (assoc this-map :FEDERATION-MAP federation-map)]
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn create-federation-release-operation
  [env]
  (let [federation-release-port
        (k/register-operation-port env {:operation-portid :FEDERATION-RELEASE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! federation-release-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)]
        (try
          (let [federation-map
                (:FEDERATION-MAP this-map)
                subrequest-return-port
                (a/chan)
                ]
            (doseq [en federation-map]
              (let [entity-request-port
                    (val en)]
                (a/>! entity-request-port [env {:requestid   :POP-REQUEST-PORT
                                                :return-port subrequest-return-port}])
                ))
            (doseq [_ federation-map]
              (k/request-exception-check (a/<! subrequest-return-port)))
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn create-context-operations
  [env]
  (create-register-new-entity-operation env)
  (create-route-operation env)
  (create-federation-route-operation env)
  (create-federation-acquire-operation env)
  (create-federation-release-operation env)
  )
