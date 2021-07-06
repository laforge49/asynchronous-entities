(ns ae.operations.entity-operations
  (:require [clojure.core.async :as a]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

#_ (defn create-add-parent-operation
  [env]
  (let [add-parent-port
        (k/register-operation-port env {:operation-portid :ADD-PARENT-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! add-parent-port)
            this-entity
            (:this-entity env)
            this-volatile-map
            (k/volatile-map this-entity)
            this-name
            (:NAME @this-volatile-map)
            parent-entity-name
            (:parent-entity-name params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)]
        (vswap! this-volatile-map (fn [old]
                                    (let [relationship-parents
                                          (get-in this-entity [:PARENTVECTORS relationship] [])
                                          relationship-parents
                                          (conj relationship-parents parent-entity-name)]
                                      (assoc-in old [:PARENTVECTORS relationship] relationship-parents))))
        (a/>! operation-return-port [nil this-entity])
        (recur)))))

#_ (defn create-add-relationship-operation
  [env]
  (let [add-relationship-port
        (k/register-operation-port env {:operation-portid :ADD-RELATIONSHIP-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! add-relationship-port)
            this-entity
            (:this-entity env)
            this-volatile-map
            (k/volatile-map this-entity)
            this-map
            @this-volatile-map
            this-entity-name
            (:NAME this-map)
            child-entity-name
            (:child-entity-name params)
            relationship
            (:relationship params)
            operation-return-port
            (:operation-return-port params)
            contexts-request-port
            (:CONTEXTS-PUBLIC-REQUEST-PORT env)
            add-parent-return-port
            (a/chan)]
        (println :??????)
        (vswap! this-volatile-map (fn [old]
                                    (let [relationship-children
                                          (get-in this-entity [:CHILDVECTORS relationship] [])
                                          _ (println 1 relationship-children)
                                          _ (if (> (.indexOf relationship-children child-entity-name) -1)
                                              (throw (Exception. (str "Entity " child-entity-name " is already a " relationship " child of " this-entity-name))))
                                          relationship-children
                                          (conj relationship-children child-entity-name)]
                                      (println 2 relationship-children)
                                      (assoc-in old [:CHILDVECTORS relationship] relationship-children))))
        (a/>! contexts-request-port [env
                                     {:requestid          :ROUTE-REQUESTID
                                      :target-requestid   :ADD-PARENT-REQUESTID
                                      :target-name        child-entity-name
                                      :relationship       :BASIC
                                      :parent-entity-name this-entity-name
                                      :return-port        add-parent-return-port
                                      }])
        (k/request-exception-check (a/<! add-parent-return-port))
        (a/>! operation-return-port [nil this-entity])
        (recur)))))

(defn create-instantiate-operation
  [env]
  (let [instantiate-port
        (k/register-operation-port env {:operation-portid :INSTANTIATE-PORTID})]
    (a/go-loop []
      (let [[env params]
            (a/<! instantiate-port)
            operation-return-port
            (:operation-return-port params)
            this-map
            (:this-map env)
            - (a/>! operation-return-port [this-map nil :NO-RETURN])
            new-entity-name
            (:name params)
            [_ new-entity-context-base-name _]
            (kw/name-as-keyword new-entity-name)
            this-descriptors
            (:DESCRIPTORS this-map)
            prototype-descriptors
            (:PROTOTYPE-DESCRIPTORS this-descriptors)
            prototype-classifiers
            (:PROTOTYPE-CLASSIFIERS this-descriptors)
            target-name
            (if (= new-entity-context-base-name "CONTEXTS")
              (str "ROOT/CONTEXTS")
              (str "CONTEXTS/" new-entity-context-base-name))
            contexts-public-request-port
            (:CONTEXTS-PUBLIC-REQUEST-PORT env)
            params
            (-> params
                (assoc :requestid :ROUTE-REQUESTID)
                (assoc :target-requestid :REGISTER-ENTITY-REQUESTID)
                (assoc :target-name target-name)
                (assoc :descriptors prototype-descriptors)
                (assoc :classifiers prototype-classifiers)
                )]
        (a/>! contexts-public-request-port [env params])
        (recur)))))

(defn create-entity-operations
  [env]
  ;(create-add-parent-operation env)
  ;(create-add-relationship-operation env)
  (create-instantiate-operation env)
  )
