(ns ae.operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]))

(defn register-entity
  [env]
  (let [name
        (get-in env [:PARAMS :name])
        [name-kw context-name base-name]
        (k/name-as-keyword name)
        context-kw
        (keyword context-name)
        contexts-atom
        (:CONTEXTS-ATOM env)
        context-entity
        (context-kw @contexts-atom)
        new-entity
        (k/create-entity (assoc env :PARAMS {:name name}))
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

(defn entity-registration-operation
  [env]
  (let [entity-registration-port
        (k/create-operation-port (assoc env :PARAMS {:operation-kw :REGISTER-ENTITY-PORT}))]
    (a/go
      (let [env
            (a/<! entity-registration-port)
            params
            (:PARAMS env)
            context-entity
            (:master-entity env)
            context-volatile
            @(second context-entity)
            new-entity
            (:new-entity params)
            name-kw
            (:name-kw params)
            operation-return-port
            (:operation-return-port params)
            ]
        (swap! context-volatile assoc-in [:ENTITIES name-kw] new-entity)
        (a/>! operation-return-port new-entity)
        ))))

(defn create-operations
  [env]
  (entity-registration-operation env))
