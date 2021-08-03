(ns ae.operations.reports
  (:require [clojure.string :as s]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn descriptor-report
  [n this-name this-map]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        descriptors
        (keys (:DESCRIPTORS this-map))
        sorted-names
        (reduce
          (fn [sorted-names descriptor-kw]
            (let [[name context-base-name base-name]
                  (kw/keyword-as-name descriptor-kw)
                  name2
                  (if (= this-context-base-name context-base-name)
                    [(str "+" base-name) name]
                    [name name])]
              (conj sorted-names name2)))
          (sorted-set)
          descriptors)
        lines
        (reduce
          (fn [lines [short-name name]]
            (let [[name-kw context-base-name base-name]
                  (kw/name-as-keyword name)]
              (conj lines (str short-name " = " (prn-str (get-in this-map [:DESCRIPTORS name-kw]))))))
          []
          sorted-names)
        nbr
        (count sorted-names)]
    (str n ". Descriptors of entity " this-name "\n\n"
         (s/join lines)
         (if (> nbr 0)
           "\n")
         "Number of descriptors: " nbr "\n\n")))

(defn classifier-report
  [n this-name this-map]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        classifiers
        (keys (:CLASSIFIERS this-map))
        sorted-names
        (reduce
          (fn [sorted-names classifier-kw]
            (let [[name context-base-name base-name]
                  (kw/keyword-as-name classifier-kw)
                  name2
                  (if (= this-context-base-name context-base-name)
                    [(str "+" base-name) name]
                    [name name])]
              (conj sorted-names name2)))
          (sorted-set)
          classifiers)
        lines
        (reduce
          (fn [lines [short-name name]]
            (let [[name-kw context-base-name base-name]
                  (kw/name-as-keyword name)
                  value
                  (get-in this-map [:CLASSIFIERS name-kw])
                  [value-kw context-base-name base-name]
                  (kw/name-as-keyword value)
                  short-value
                  (if (= this-context-base-name context-base-name)
                    (str "+" base-name)
                    value)]
              (conj lines (str short-name " = " short-value "\n"))))
          []
          sorted-names)
        nbr
        (count sorted-names)]
    (str n ". Classifiers of entity " this-name "\n\n"
         (s/join lines)
         (if (> nbr 0)
           "\n")
         "Number of classifiers: " nbr "\n\n")))

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
