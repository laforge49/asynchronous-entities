(ns ae.operations.context-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn registerEntityOperation
  [env this-map params]
  (let [this-name
        (:NAME this-map)
        name
        (:name params)
        _ (if (some? (:initialization-port params))
            (throw (Exception. (str "An initialization port is not compatible with non-federated registration of entity "
                                    name))))
        entity-public-request-port
        (:entity-public-request-port params)
        entity-public-request-port
        (if (some? entity-public-request-port)
          entity-public-request-port
          (first (k/create-entity env params)))]
    (if (s/blank? name)
      [this-map nil entity-public-request-port]
      (let [[new-entity-kw _ _]
            (kw/name-as-keyword name)
            _ (if (some? (get-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw]))
                (throw (Exception. (str "Entity " name " already exists in " this-name))))
            this-map
            (assoc-in this-map [:ENTITY-PUBLIC-REQUEST-PORTS new-entity-kw] entity-public-request-port)
            classifiers
            (:classifiers params)]
        (if (some? classifiers)
          (doseq [[classifier-kw classifier-value-kw] classifiers]
            (k/add-classifier-value this-name name classifier-kw classifier-value-kw)))
        [this-map nil entity-public-request-port]))))

(defn create-register-entity-operation
  [env]
  (let [entity-registration-port
        (k/register-operation env {:operationid :REGISTER_ENTITY_OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! entity-registration-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (a/>! operation-return-port (registerEntityOperation env this-map params))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))))

(defn create-register-classifier-operation
  [env]
  (let [register-classifier-port
        (k/register-operation env {:operationid :REGISTER_CLASSIFIER_OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! register-classifier-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [name
                (:name params)
                classifier-kw
                (:classifier params)
                classifier-value-kw
                (:classifier-value params)
                this-name
                (:NAME this-map)]
            (k/add-classifier-value this-name name classifier-kw classifier-value-kw)
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil]))))
      (recur))
    ))

(defn create-route-operation
  [env]
  (let [route-to-local-entity-port
        (k/register-operation env {:operationid :ROUTE_OPERATIONID})]
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
                (:target_name params)
                _ (if (nil? target-entity-name)
                    (throw (Exception. (str ":target_name is nil\n"
                                            (prn-str params)
                                            (prn-str this-map)))))
                _ (if (not (string? target-entity-name))
                    (throw (Exception. (str ":target_name is not a string\n"
                                            (prn-str target-entity-name)
                                            (prn-str params)
                                            (prn-str this-map)))))
                [target-entity-kw target-context-base-name _]
                (kw/name-as-keyword target-entity-name)]
            (if (= this-name target-entity-name)
              (let [target-requestid
                    (:target_requestid params)]
                (a/>! operation-return-port [this-map nil :NO-RETURN])
                (a/>! active-request-port [env
                                           (assoc params :requestid target-requestid)]))
              (if (= this-base-name target-context-base-name)
                (let [entity-public-request-ports
                      (:ENTITY-PUBLIC-REQUEST-PORTS this-map)
                      target-entity-request-port
                      (target-entity-kw entity-public-request-ports)
                      target-requestid
                      (:target_requestid params)]
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
                    (throw (Exception. (str "Entity " this-base-name "+" target-context-base-name " is not registered in " this-name))))
                  (a/>! operation-return-port [this-map
                                               nil
                                               :NO-RETURN])
                  (a/>! target-entity-request-port [env
                                                    (assoc params :requestid :CONTEXTS/ROUTE_REQUESTID)])))))
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
                        _ (a/>! root-contexts-request-port [env {:requestid        :CONTEXTS/ROUTE_REQUESTID
                                                                 :target_requestid :PUSH-REQUEST-PORT
                                                                 :target_name      federation-name
                                                                 :new-request-port new-request-port
                                                                 :return_port      subrequest-return-port}])
                        [snap new-request-port]
                        (k/request-exception-check (a/<! subrequest-return-port))
                        federation-map
                        (assoc federation-map federation-name [snap new-request-port])]
                    [federation-names-vec federation-map])
                  (catch Exception e
                    (a/>! return-port [e nil])
                    [nil nil]))]
            (recur federation-names-vec federation-map)))))
    return-port))

(defn create-federation-acquire-operation
  [env]
  (let [federation-acquire-port
        (k/register-operation env {:operationid :FEDERATION_ACQUIRE_OPERATIONID})]
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
        (k/register-operation env {:operationid :FEDERATION_RELEASE_OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! federation-release-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [federation-map
                (:FEDERATION-MAP env)
                subrequest-return-port
                (a/chan)
                ]
            (doseq [en federation-map]
              (let [[snap entity-request-port]
                    (val en)]
                (if (some? entity-request-port)
                  (a/>! entity-request-port [env {:requestid   :RESET-REQUEST-PORT
                                                  :this-map    snap
                                                  :return_port subrequest-return-port}]))))
            (doseq [en federation-map]
              (let [[vsnap entity-request-port]
                    (val en)]
                (if (some? entity-request-port)
                  (k/request-exception-check (a/<! subrequest-return-port)))))
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn context-entities-report
  [n this-name this-map]
  (let [entities
        (keys (:ENTITY-PUBLIC-REQUEST-PORTS this-map))
        sorted-names
        (reduce
          (fn [sorted-names entity-kw]
            (let [[name context-base-name base-name]
                  (kw/keyword-as-name entity-kw)]
              (conj sorted-names name)))
          (sorted-set)
          entities)
        lines
        (reduce
          (fn [lines name]
            (conj lines
                  (str name
                       (if (some? (k/get-invariant-map name))
                         " (invariant)")
                       "\n")))
          []
          sorted-names)]
    (str n ". Registered Entities of " this-name "\n\n"
         (s/join lines) "\n"
         "Number of entities: " (count sorted-names) "\n\n")))

(defn context-classifiers-report
  [n this-name]
  (let [registry
        (k/get-classifier-values-map this-name)
        lines
        (reduce
          (fn [lines [classifier-kw values-map]]
            (let [[classifier-name _ _]
                  (kw/keyword-as-name classifier-kw)
                  line
                  (str "classifier:    " classifier-name "\n")
                  lines
                  (conj lines line)
                  lines
                  (reduce
                    (fn [lines [classifier-value entity-names]]
                      (let [line
                            (str "     value:        " classifier-value "\n")
                            lines
                            (conj lines line)
                            lines
                            (reduce
                              (fn [lines entity-name]
                                (conj lines (str "    entity:            " entity-name "\n")))
                              lines
                              (into (sorted-set) entity-names))]
                        lines))
                    lines
                    (into (sorted-map) values-map))]
              lines))
          []
          (into (sorted-map) registry))
        classifiers
        (keys registry)]
    (str n ". Classifier Values of " this-name "\n\n"
         (s/join lines) "\n"
         "Number of classifiers: " (count classifiers) "\n\n")))

(defn create-context-report-operation
  [env]
  (let [context-report-port
        (k/register-operation env {:operationid :CONTEXT_REPORT_OPERATIONID})]
    (a/go-loop []
      (let [[env this-map params]
            (a/<! context-report-port)
            operation-return-port
            (:operation-return-port params)]
        (try
          (let [this-name
                (:NAME this-map)
                file-name
                (str "./reports/contexts/" this-name ".txt")
                heading
                (str "Context Report for " this-name "\n\n")
                report
                (str heading
                     (context-entities-report 1 this-name this-map)
                     (context-classifiers-report 2 this-name))]
            (io/make-parents file-name)
            (spit file-name report)
            (a/>! operation-return-port [this-map nil this-map]))
          (catch Exception e
            (a/>! operation-return-port [this-map e nil])))
        (recur)))))

(defn create-context-operations
  [env]
  (create-register-entity-operation env)
  (create-register-classifier-operation env)
  (create-route-operation env)
  (create-federation-acquire-operation env)
  (create-federation-release-operation env)
  (create-context-report-operation env)
  )
