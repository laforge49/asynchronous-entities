(ns ae.kernel
  (:require [clojure.core.async :as a]
            [clojure.string :as s]))

(def operation-ports
  (atom {}))

(defn create-operation-port
  [env]
  (let [operation-kw
        (get-in env [:PARAMS :operation-kw])
        port
        (a/chan)]
    (swap! operation-ports assoc operation-kw port)
    port))

(defn operation-dispatcher
  [env]
  (let [params
        (:PARAMS env)
        master-entity
        (:master-entity params)
        entity-port
        (first master-entity)
        ]
    (a/go-loop []
      (let [entity-map
            @(second master-entity)
            operations
            (:OPERATIONS entity-map)
            env
            (a/<! entity-port)
            env
            (assoc env :master-entity master-entity)
            params
            (:PARAMS env)
            request
            (:request params)
            operation-port
            (request operations)
            operation-return-port
            (a/chan)
            ]
        (a/>! operation-port (assoc-in env [:PARAMS :operation-return-port] operation-return-port))
        (let [return-value
              (a/<! operation-return-port)
              return-port
              (:return-port env)]
          (if (not= return-value :BLOCK-CLIENT)
            (a/>! return-port return-value))
          (if (not= return-value :BLOCK-SERVICE)
            (recur)))))))

(defn name-as-keyword
  [name]
  (let [slashindex
        (s/index-of name "/")
        base-name
        (subs name (inc slashindex))
        context-name
        (subs name 0 slashindex)
        name-kw
        (keyword context-name base-name)
        ]
    [name-kw context-name base-name]))

(defn create-entity
  [env]
  (let [entity-port
        (a/chan)
        params
        (:PARAMS env)
        name
        (:name params)
        entity-map
        (:entity-map params)
        entity-map
        (assoc entity-map :NAME name)
        entity-volatile
        (volatile! entity-map)
        new-entity
        [entity-port entity-volatile]
        _ (operation-dispatcher (assoc env :PARAMS {:master-entity new-entity}))
        ]
    new-entity
    ))

(defn register-entity
  [env]
  (let [name
        (get-in env [:PARAMS :name])
        [name-kw context-name base-name]
        (name-as-keyword name)
        context-kw
        (keyword context-name)
        contexts-atom
        (:CONTEXTS-ATOM env)
        context-entity
        (context-kw @contexts-atom)
        new-entity
        (create-entity (assoc env :PARAMS {:name name}))
        new-entity-port
        (first new-entity)]
    (let [return-port
          (a/chan)
          ]
      (a/>! new-entity-port (assoc env :PARAMS {:master-entity context-entity
                                            :request       :REGISTER-ENTITY
                                            :new-entity    new-entity
                                            :name-kw       name-kw
                                            :return-port   return-port}))
      (a/<! return-port))))

(defn register-context
  [env]
  (let [name
        (get-in env [:PARAMS :name])
        new-context
        (create-entity (assoc env :PARAMS {:name             name
                                           :operations-ports {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT}
                                           :childvectors     {}
                                           :parentvectors    {}
                                           }))
        [name-kw context-name base-name]
        (name-as-keyword name)
        contexts-atom
        (:CONTEXTS-ATOM env)
        ]
    (swap! contexts-atom assoc name-kw new-context)
    new-context
    ))
