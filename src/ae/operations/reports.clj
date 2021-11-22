(ns ae.operations.reports
  (:require [clojure.string :as s]
            [tupelo.parse.yaml :as yaml]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn short-names
  [names default-context-name]
  (reduce
    (fn [sorted-names name-or-kw]
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
        (conj sorted-names name2)
        ))
    (sorted-set)
    names))

(defn front-matter
  [this-map env]
  (let [this-name
        (get this-map "SYS+facet-NAME&?")
        [name-kw context-base-name base-name]
        (kw/name-as-keyword this-name)
        _ (k/validate-names this-name this-map "map" "facet" nil nil env)
        facets
        (k/unbind-context (str context-base-name "+")
                          this-map
                          nil
                          env)
        fm
        {(if (= context-base-name "SYS")
           "gem_map-FACETS^facet"
           "SYS+gem_map-FACETS^facet")

         facets}]
    (str "---\n"
         (yaml/edn->yaml fm)
         "---\n")))

(defn context-entities-report
  [n this-name this-map]
  (let [[this-name-kw this-context-base-name this-base-name]
        (kw/name-as-keyword this-name)
        entities
        (keys (get this-map "SYS+facet_map?-ENTITYpublicREQUESTports^?$chan"))
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
