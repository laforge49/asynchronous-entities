(ns ae.operations.reports
  (:require [clojure.string :as s]
            [tupelo.parse.yaml :as yaml]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn short-names
  [names-kws default-context-name]
  (reduce
    (fn [sorted-names name-or-kw]
      (if (keyword? name-or-kw)
        (let [[name context-base-name base-name]
              (kw/keyword-as-name name-or-kw)
              name2
              (if (= default-context-name context-base-name)
                [(str "+" base-name) name]
                [name name])]
          (conj sorted-names name2))
        (let [[name-kw context-base-name base-name]
              (kw/name-as-keyword name-or-kw)
              name2
              (if (= default-context-name context-base-name)
                [(str "+" base-name) name-or-kw]
                [name-or-kw name-or-kw])]
          (conj sorted-names name2))
        ))
    (sorted-set)
    names-kws))

(defn classifier-report
  [n this-name this-map]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        classifiers
        (keys (get this-map "CLASSIFIERS"))
        sorted-classifier-name2s
        (short-names classifiers this-context-base-name)
        lines
        (reduce
          (fn [lines [short-name name]]
            (let [value
                  (get-in this-map ["CLASSIFIERS" name])
                  [value-kw context-base-name base-name]
                  (kw/name-as-keyword value)
                  short-value
                  (if (= this-context-base-name context-base-name)
                    (str "+" base-name)
                    value)]
              (conj lines (str short-name " = " short-value "\n"))))
          []
          sorted-classifier-name2s)
        nbr
        (count sorted-classifier-name2s)]
    (str n ". Classifiers of entity " this-name "\n"
         "(Default context is " this-context-base-name ".)\n\n"
         (s/join lines)
         (if (> nbr 0)
           "\n")
         "Number of classifiers: " nbr "\n\n")))

(defn front-matter
  [this-map]
  (let [fm
        {"DESCRIPTORS"
         (get this-map "DESCRIPTORS")}]
    (str "---\n"
         (yaml/edn->yaml fm)
         "---\n")))

(defn context-entities-report
  [n this-name this-map]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        entities
        (keys (get this-map "ENTITY-PUBLIC-REQUEST-PORTS"))
        sorted-names
        (short-names entities this-base-name)
        lines
        (reduce
          (fn [lines short-name]
            (conj lines
                  (str (first short-name)
                       "\n")))
          []
          sorted-names)]
    (str n ". Registered Entities of context " this-name "\n"
         "(Default context is " this-base-name ".)\n\n"
         (s/join lines) "\n"
         "Number of entities: " (count sorted-names) "\n\n")))

(defn context-classifier-values-report
  [n this-name]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        classifier-registry
        (k/get-classifier-values-map this-name)
        classifier-names
        (keys classifier-registry)
        sorted-classifier-name2s
        (short-names classifier-names this-base-name)
        lines
        (reduce
          (fn [lines [short-classifier-name classifier-name]]
            (let [line
                  (str "classifier:    " short-classifier-name "\n")
                  lines
                  (conj lines line)
                  values-map
                  (get classifier-registry classifier-name)
                  values
                  (keys values-map)
                  sorted-value2s
                  (short-names values this-base-name)
                  lines
                  (reduce
                    (fn [lines [short-value value]]
                      (let [line
                            (str "     value:        " short-value "\n")
                            lines
                            (conj lines line)
                            entity-names
                            (get values-map value)
                            sorted-entity-name2s
                            (short-names entity-names this-base-name)
                            lines
                            (reduce
                              (fn [lines [entity-short-name entity-name]]
                                (conj lines (str "    entity:            " entity-short-name "\n")))
                              lines
                              sorted-entity-name2s)]
                        lines))
                    lines
                    sorted-value2s)]
              lines))
          []
          sorted-classifier-name2s)
        classifiers
        (keys classifier-registry)]
    (str n ". Classifier Values of context " this-name "\n"
         "(Default context is " this-base-name ".)\n\n"
         (s/join lines) "\n"
         "Number of classifiers: " (count classifiers) "\n\n")))
