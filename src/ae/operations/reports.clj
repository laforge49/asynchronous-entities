(ns ae.operations.reports
  (:require [clojure.string :as s]
            [tupelo.parse.yaml :as yaml]
            [ae.kernel :as k]
            [ae.names :as n]
            [ae.transform :as t]))

(defn short-names
  [names default-context-name]
  (reduce
    (fn [sorted-names name-or-kw]
      (let [[context-base-name base-name]
            (n/parse-into-2 name-or-kw)
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
        (get this-map "SYS+facet-NAME&%")
        [context-base-name base-name]
        (n/parse-into-2 this-name)
        _ (t/validate-names this-map "map" "facet" nil nil nil env)
        facets
        (t/unbind-context this-map
                          nil
                          env)
        fm
        (sorted-map "SYS+gem-FACETS_map^facet"
         facets)]
    (str "---\n"
         (t/edn-to-yaml fm)
         "---\n")))

(defn context-classifier-values-report
  [n this-name]
  (let [[_ this-base-name]
        (n/parse-into-2 this-name)
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
