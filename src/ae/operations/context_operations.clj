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

(defn create-context-operations
  [env]
  (create-register-new-entity-operation env)
  (create-route-operation env)
  )
