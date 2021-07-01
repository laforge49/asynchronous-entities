(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.keywords :as kw]))

(defn request-port
  [entity]
  (first entity))

(defn volatile-map
  [entity]
  (second entity))

(def operation-ports-atom
  (atom {}))

(defn register-operation-port
  [env params]
  (let [operation-port-kw
        (:operation-port-kw params)
        port
        (a/chan)]
    (swap! operation-ports-atom assoc operation-port-kw port)
    port))

(defn create-operation-dispatcher
  [env params]
  (let [this-entity
        (:this-entity params)
        this-volatile-map
        (volatile-map this-entity)
        this-name
        (:NAME @this-volatile-map)
        ]
    (a/go-loop []
      (let [this-request-port-stack
            (:REQUEST-PORT-STACK @this-volatile-map)
            this-request-port
            (peek this-request-port-stack)
            [env params]
            (a/<! this-request-port)
            descriptors
            (:DESCRIPTORS @this-volatile-map)
            this-operation-ports
            (:OPERATION-PORTS descriptors)
            ]
        (let [env
              (assoc env :this-entity this-entity)
              request
              (:request params)
              operation-port-id
              (request this-operation-ports)
              return-value
              (case request
                :SNAPSHOT
                @this-volatile-map
                :PUSH-REQUEST-PORT
                (let [new-request-port
                      (:new-request-port params)
                      saved-entity-map
                      @this-volatile-map]
                  (vswap! this-volatile-map assoc :REQUEST-PORT (conj this-request-port-stack new-request-port))
                  saved-entity-map)
                :POP-REQUEST-PORT
                (let []
                  (vswap! this-volatile-map assoc :REQUEST-PORT (pop this-request-port-stack))
                  true)
                :RESET
                (let [saved-entity-map
                      (:saved-entity-map params)]
                  (vreset! this-volatile-map saved-entity-map))
                (let [operation-port
                      (operation-port-id @operation-ports-atom)
                      operation-return-port
                      (a/chan)
                      ]
                  (a/>! operation-port [env (assoc params :operation-return-port operation-return-port)])
                  (a/<! operation-return-port)))
              return-port
              (:return-port params)]
          (if (and (some? return-port)
                   (not= return-value :NO-RETURN))
            (a/>! return-port return-value))
          (recur))))))

(defn create-entity
  [env params]
  (let [new-request-port
        (a/chan)
        request-port-stack
        [new-request-port]
        initialization-port
        (:initialization-port params)
        request-port-stack
        (if (nil? initialization-port)
          request-port-stack
          (conj request-port-stack initialization-port))
        new-entity-map
        {:NAME               (:name params)
         :DESCRIPTORS        (:descriptors params)
         :CLASSIFIERS        (:classifiers params)
         :CHILDVECTORS       {}
         :PARENTVECTORS      {}
         :REQUEST-PORT-STACK request-port-stack}
        new-entity-volatile-map
        (volatile! new-entity-map)
        new-entity
        [new-request-port new-entity-volatile-map]
        ]
    (create-operation-dispatcher env {:this-entity new-entity})
    new-entity
    ))

