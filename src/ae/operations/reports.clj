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
              +pos
              (s/index-of name "+")
              name2
              (if (= default-context-name context-base-name)
                [(if (nil? name)
                   base-name
                   (str "+" base-name))
                 name]
                [name name])]
          (conj sorted-names name2))
        (let [[name-kw context-base-name base-name]
              (kw/name-as-keyword name-or-kw)
              +pos
              (s/index-of name-or-kw "+")
              name2
              (if (= default-context-name context-base-name)
                [(if (nil? +pos)
                   base-name
                   (str "+" base-name))
                 name-or-kw]
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
  [this-map env]
  (let [this-name
        (get this-map "NAME")
        [name-kw context-base-name base-name]
        (kw/name-as-keyword this-name)
        classifiers
        (get this-map "CLASSIFIERS")
        descriptors
        (get this-map "DESCRIPTORS")
        descriptors
        (k/unbind-context (str context-base-name "+")
                          descriptors
                          false
                          env)
        relations
        (get this-map "RELATIONS")
        fm
        {"DESCRIPTORS"
         descriptors}
        classifier-tags
        (reduce
          (fn [tags [n v]]
            (if (vector? v)
              (reduce
                (fn [tags i]
                  (conj tags (str n "/" i)))
                tags
                v)
              (conj tags (str n "/" v))))
          []
          classifiers)
        relation-tags
        (reduce
          (fn [tags [n vs]]
            (reduce
              (fn [tags v]
                (conj tags (str n "/" v)))
              tags
              vs))
          []
          relations)
        tags
        (into classifier-tags relation-tags)
        fm
        (if (empty? tags)
          fm
          (assoc fm "TAGS" tags))
        ]
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
                  (str "classifier:  " short-classifier-name "\n")
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
                            (str "  value:       " short-value "\n")
                            lines
                            (conj lines line)
                            entity-names
                            (get values-map value)
                            sorted-entity-name2s
                            (short-names entity-names this-base-name)
                            lines
                            (reduce
                              (fn [lines [entity-short-name entity-name]]
                                (conj lines (str "    entity:      " entity-short-name "\n")))
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

(defn context-relation-values-report
  [n this-name]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        relation-registry
        (k/get-relation-values-map this-name)
        relation-names
        (keys relation-registry)
        sorted-relation-name2s
        (short-names relation-names this-base-name)
        lines
        (reduce
          (fn [lines [short-relation-name relation-name]]
            (let [line
                  (str "relation:  " short-relation-name "\n")
                  lines
                  (conj lines line)
                  values-map
                  (get relation-registry relation-name)
                  values
                  (keys values-map)
                  sorted-value2s
                  (short-names values this-base-name)
                  lines
                  (reduce
                    (fn [lines [short-value value]]
                      (let [line
                            (str "  value:     " short-value "\n")
                            lines
                            (conj lines line)
                            entity-names
                            (get values-map value)
                            sorted-entity-name2s
                            (short-names entity-names this-base-name)
                            lines
                            (reduce
                              (fn [lines [entity-short-name entity-name]]
                                (conj lines (str "    entity:    " entity-short-name "\n")))
                              lines
                              sorted-entity-name2s)]
                        lines))
                    lines
                    sorted-value2s)]
              lines))
          []
          sorted-relation-name2s)
        relations
        (keys relation-registry)]
    (str n ". Relation Values of context " this-name "\n"
         "(Default context is " this-base-name ".)\n\n"
         (s/join lines) "\n"
         "Number of relations: " (count relations) "\n\n")))
