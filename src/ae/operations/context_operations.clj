(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn create-register-entity-operation
  [env]
  (let [entity-registration-port
        (k/register-operation-port env {:operation-portid :REGISTER-ENTITY-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! entity-registration-port)
            this-entity
            (:this-entity env)
            this-volatile-map
            (k/volatile-map this-entity)
            this-map
            @this-volatile-map
            this-name
            (:NAME this-map)
            new-entity-name
            (:name params)
            [new-entity-kw _ _]
            (kw/name-as-keyword new-entity-name)]
        (if (some? (get-in this-map [:ENTITIES new-entity-kw]))
          (throw (Exception. (str "Entity " new-entity-name "already exists in " this-name))))
        (let [new-entity
              (k/create-entity env params)
              operation-return-port
              (:operation-return-port params)]
          (vswap! this-volatile-map assoc-in [:ENTITIES new-entity-kw] new-entity)
          (a/>! operation-return-port [nil new-entity])
          (recur))))))

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
            - (a/>! operation-return-port [nil :NO-RETURN])
            this-entity
            (:this-entity env)
            this-volatile-map
            (k/volatile-map this-entity)
            this-map
            @this-volatile-map
            this-name
            (:NAME this-map)
            [_ _ this-base-name]
            (kw/name-as-keyword this-name)
            target-entity-name
            (:target-name params)
            [target-entity-kw target-context-base-name _]
            (kw/name-as-keyword target-entity-name)]
        (if (= this-name target-entity-name)
          (let [this-entity-request-port
                (k/request-port this-entity)
                target-request
                (:target-requestid params)
                ]
            (a/>! this-entity-request-port [env
                                            (assoc params :requestid target-request)]))
          (if (= this-base-name target-context-base-name)
            (let [entities
                  (:ENTITIES @this-volatile-map)
                  target-entity
                  (target-entity-kw entities)
                  target-entity-request-port
                  (k/request-port target-entity)
                  target-request
                  (:target-requestid params)
                  ]
              (a/>! target-entity-request-port [env
                                                (assoc params :requestid target-request)]))
            (let [target-context-entity-kw
                  (keyword this-base-name target-context-base-name)
                  context-entities
                  (:ENTITIES @this-volatile-map)
                  target-context-entity
                  (target-context-entity-kw context-entities)
                  target-context-entity-request-port
                  (k/request-port target-context-entity)
                  ]
              (a/>! target-context-entity-request-port [env
                                                        (assoc params :requestid :ROUTE-REQUESTID)]))))
        (recur)))))

(defn create-context-operations
  [env]
  (create-register-entity-operation env)
  (create-route-operation env)
  )
