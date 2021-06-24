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
  [env]
  (let [params
        (:PARAMS env)
        master-entity
        (:master-entity params)
        entity-map-volatile
        (second master-entity)
        ]
    (a/go-loop []
      (let [request-port-stack
            (:REQUEST-PORT-STACK @entity-map-volatile)
            request-port
            (peek request-port-stack)
            operations
            (:OPERATION-PORTS @entity-map-volatile)
            env
            (a/<! request-port)
            env
            (assoc env :master-entity master-entity)
            params
            (:PARAMS env)
            request
            (:request params)
            operation-port-id
            (request operations)
            return-value
            (case request
              :SNAPSHOT
              @entity-map-volatile
              :PUSH-REQUEST-PORT
              (let [new-request-port
                    (:new-request-port params)
                    saved-entity-map
                    @entity-map-volatile]
                (vswap! entity-map-volatile assoc :REQUEST-PORT (conj request-port-stack new-request-port))
                saved-entity-map)
              :POP-REQUEST-PORT
              (let []
                (vswap! entity-map-volatile assoc :REQUEST-PORT (pop request-port-stack))
                true)
              :ABORT
              (let [saved-entity-map
                    (:saved-entity-map params)]
                (vreset! entity-map-volatile saved-entity-map))
              (let [operation-port
                    (operation-port-id @operation-ports-atom)
                    operation-return-port
                    (a/chan)
                    ]
                (println 33333 (pr-str operation-port))
                (Thread/sleep 1000)
                (a/>! operation-port (assoc-in env [:PARAMS :operation-return-port] operation-return-port))
                (a/<! operation-return-port)))
            ]
        (let [return-port
              (:return-port params)]
          (if (some? return-port)
            (a/>! return-port return-value))
          (recur))))))

(defn create-entity
  [env]
  (let [request-port
        (a/chan)
        params
        (:PARAMS env)
        entity-map
        (-> {}
            (assoc :NAME (:name params))
            (assoc :OPERATION-PORTS (:operation-ports params))
            (assoc :CHILDVECTORS {})
            (assoc :PARENTVECTORS {})
            (assoc :REQUEST-PORT-STACK [request-port])
            )
        entity-map-volatile
        (volatile! entity-map)
        new-entity
        [request-port entity-map-volatile]
        ]
    (create-operation-dispatcher (assoc env :PARAMS {:master-entity new-entity}))
    new-entity
    ))

