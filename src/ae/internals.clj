(ns ae.internals)

(def entities-map-atom
  (atom {}))

(defn get-entity-map
  [name]
  (get @entities-map-atom name))
