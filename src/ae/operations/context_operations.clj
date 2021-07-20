(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn register-new-entity-function
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        new-entity-name
        (:name params)
        _ (if (some? (:initialization-port params))
            (throw (Exception. (str "An initialization port is not compatible with registration of entity "
                                    new-entity-name))))]
    (if (s/blank? new-entity-name)
      (let [new-entity-public-request-port
            (first (k/create-entity env params))]
        [this-map nil new-entity-public-request-port])
      (let [[new-entity-kw _ _]
            (kw/name-as-keyword new-entity-name)
            _ (if (some? (get-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw]))
                (throw (Exception. (str "Entity " new-entity-name " already exists in " this-name))))
            new-entity-public-request-port
            (first (k/create-entity env params))
            this-map
            (assoc-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw] new-entity-public-request-port)]
        [this-map nil new-entity-public-request-port]))))

(defn create-register-new-entity-operation
  [env]
  (let [new-entity-registration-port
        (k/register-operation env {:operationid :REGISTER-NEW-ENTITY-OPERATIONID
                                   :function    register-new-entity-function})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! new-entity-registration-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (a/>! operation-return-port (register-new-entity-function env this-map params))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-route-operation
  [env]
  (let [route-to-local-entity-port
        (k/register-operation env {:operationid :ROUTE-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! route-to-local-entity-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [active-request-port
                (:active-request-port env)
                this-name
                (:NAME this-map)
                _ (if (nil? this-name)
                    (throw (Exception. (str ":NAME is nil\n"
                                            (prn-str params)
                                            (prn-str this-map)))))
                _ (if (not (string? this-name))
                    (throw (Exception. (str ":NAME is not a string\n"
                                            (prn this-name)
                                            (prn-str params)
                                            (prn-str this-map)))))
                [_ _ this-base-name]
                (kw/name-as-keyword this-name)
                target-entity-name
                (:target-name params)
                _ (if (nil? target-entity-name)
                    (throw (Exception. (str ":target-name is nil\n"
                                            (prn-str params)
                                            (prn-str this-map)))))
                _ (if (not (string? target-entity-name))
                    (throw (Exception. (str ":target-name is not a string\n"
                                            (prn-str target-entity-name)
                                            (prn-str params)
                                            (prn-str this-map)))))
                [target-entity-kw target-context-base-name _]
                (kw/name-as-keyword target-entity-name)]
            (if (= this-name target-entity-name)
              (let [target-requestid
                    (:target-requestid params)]
                (a/>! operation-return-port [this-map nil :NO-RETURN])
                (a/>! active-request-port [env
                                           (assoc params :requestid target-requestid)]))
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
                  (if (nil? target-entity-request-port)
                    (throw (Exception. (str "Entity " this-base-name "/" target-context-base-name " is not registered in " this-name))))
                  (a/>! operation-return-port [this-map
                                               nil
                                               :NO-RETURN])
                  (a/>! target-entity-request-port [env
                                                    (assoc params :requestid :ROUTE-REQUESTID)])))))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-federation-route-operation
  [env]
  (let [federation-route-port
        (k/register-operation env {:operationid :FEDERATION-ROUTE-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! federation-route-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [federation-map-volatile
                (:FEDERATION-MAP-VOLATILE env)
                target-name
                (:target-name params)
                target-request-port
                (second (get @federation-map-volatile target-name))
                _ (if (nil? target-request-port)
                    (throw (Exception. (str "Entity " target-name " is not federated"))))
                target-requestid
                (:target-requestid params)]
            (a/>! operation-return-port [this-map nil :NO-RETURN])
            (a/>! target-request-port [env
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
                        [snap new-request-port]
                        (k/request-exception-check (a/<! subrequest-return-port))
                        federation-map
                        (assoc federation-map federation-name [(volatile! snap) new-request-port])]
                    [federation-names-vec federation-map])
                  (catch Exception e
                    (a/>! return-port [e nil])
                    [nil nil]))]
            (recur federation-names-vec federation-map)))))
    return-port))

(defn create-federation-acquire-operation
  [env]
  (let [federation-acquire-port
        (k/register-operation env {:operationid :FEDERATION-ACQUIRE-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! federation-acquire-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [federation-names
                (:federation-names params)
                root-contexts-request-port
                (:CONTEXT-REQUEST-PORT env)
                acquire-loop-port
                (federation-acquire-loop root-contexts-request-port env federation-names)
                federation-map
                (k/request-exception-check (a/<! acquire-loop-port))]
            (a/>! operation-return-port [this-map nil federation-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn create-federation-release-operation
  [env]
  (let [federation-release-port
        (k/register-operation env {:operationid :FEDERATION-RELEASE-OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! federation-release-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [federation-map
                @(:FEDERATION-MAP-VOLATILE env)
                subrequest-return-port
                (a/chan)
                ]
            (doseq [en federation-map]
              (let [[vsnap entity-request-port]
                    (val en)]
                (if (some? entity-request-port)
                  (a/>! entity-request-port [env {:requestid   :RESET-REQUEST-PORT
                                                  :this-map    @vsnap
                                                  :return-port subrequest-return-port}]))))
            (doseq [en federation-map]
              (let [[vsnap entity-request-port]
                    (val en)]
                (if (some? entity-request-port)
                  (k/request-exception-check (a/<! subrequest-return-port)))))
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
