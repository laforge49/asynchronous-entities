(ns ae.operations.reports
  (:require [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

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
    (str n ". Registered Entities of context " this-name "\n\n"
         (s/join lines) "\n"
         "Number of entities: " (count sorted-names) "\n\n")))

(defn context-classifier-values-report
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
    (str n ". Classifier Values of context " this-name "\n\n"
         (s/join lines) "\n"
         "Number of classifiers: " (count classifiers) "\n\n")))
