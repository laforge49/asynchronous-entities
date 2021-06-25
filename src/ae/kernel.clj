(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [ae.keywords :as kw]))

(def operation-ports-atom
  (atom {}))

(defn register-operation-port
  [env]
  (let [operation-port-kw
        (get-in env [:PARAMS :operation-port-kw])
        port
        (a/chan)]
    (swap! operation-ports-atom assoc operation-port-kw port)
    port))

(defn create-operation-dispatcher
  [env params]
  (let [this-entity
        (:this-entity params)
        this-volatile-map
        (second this-entity)
        ]
    (a/go-loop []
      (let [this-request-port-stack
            (:REQUEST-PORT-STACK @this-volatile-map)
            this-request-port
            (peek this-request-port-stack)
            this-operation-ports
            (:OPERATION-PORTS @this-volatile-map)
            env
            (a/<! this-request-port)
            env
            (assoc env :this-entity this-entity)
            params
            (:PARAMS env)
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
              :ABORT
              (let [saved-entity-map
                    (:saved-entity-map params)]
                (vreset! this-volatile-map saved-entity-map))
              (let [operation-port
                    (operation-port-id @operation-ports-atom)
                    operation-return-port
                    (a/chan)
                    ]
                (a/>! operation-port (assoc-in env [:PARAMS :operation-return-port] operation-return-port))
                (a/<! operation-return-port)))
            ]
        (let [return-port
              (:return-port params)]
          (if (and (some? return-port)
                   (not= return-value :NO-RETURN))
            (a/>! return-port return-value))
          (recur))))))

(defn create-entity
  [env params]
  (let [new-request-port
        (a/chan)
        new-entity-map
        {:NAME (:name params)
         :OPERATION-PORTS (:operation-ports params)
         :CHILDVECTORS {}
         :PARENTVECTORS {}
         :REQUEST-PORT-STACK [new-request-port]}
        new-entity-volatile-map
        (volatile! new-entity-map)
        new-entity
        [new-request-port new-entity-volatile-map]
        ]
    (create-operation-dispatcher env {:this-entity new-entity})
    new-entity
    ))

