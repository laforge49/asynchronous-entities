(ns ae.operations.contexts-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-context-registration-operation
  [env]
  (let [context-registration-port
        (k/register-operation-port (assoc env :PARAMS {:operation-port-kw :REGISTER-CONTEXT-PORT}))]
    (a/go-loop []
      (let [env
            (a/<! context-registration-port)
            params
            (:PARAMS env)
            contexts-entity
            (:master-entity env)
            contexts-volatile
            (second contexts-entity)
            context-name
            (get-in env [:PARAMS :name])
            [context-kw _ _]
            (kw/name-as-keyword context-name)
            new-context
            (k/create-entity env)
            operation-return-port
            (:operation-return-port params)
            ]
        (vswap! contexts-volatile assoc-in [:CONTEXT-ENTITIES context-kw] new-context)
        (a/>! operation-return-port new-context)
        (recur)))))

(defn create-route-to-context-operation
  [env]
  (let [route-to-context-port
        (k/register-operation-port (assoc env :PARANS {:operation-port-kw :ROUTE-TO-CONTEXT-PORT}))]
    (a/go-loop []
      (println 111111111 (pr-str route-to-context-port))
      (let [env
            (a/<! route-to-context-port)
            _ (println 222222222)
            params
            (:PARAMS env)
            contexts-entity
            (:master-entity env)
            contexts-volatile
            (second contexts-entity)
            context-name
            (get-in env [:PARAMS :target-context-name])
            [contex-kw _ _]
            (kw/name-as-keyword context-name)
            context-entities
            (:CONTEXT-ENTITIES @contexts-volatile)
            context-entity
            (contex-kw context-entities)
            target-request
            (:target-request params)
            ]
        (println 2468 (pr-str (first context-entity)))
        (a/>! (first context-entity) (assoc-in env [:PARAMS :request] target-request))
        (recur)))))

(defn create-contexts-operations
  [env]
  (create-context-registration-operation env)
  (create-route-to-context-operation env)
  )
