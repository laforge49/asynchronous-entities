(ns ae.internals)

(def entities-map-atom
  (atom {}))

(defn get-entity-map
  [name]
  (get @entities-map-atom name))

(defn assoc-entity-map
  [name entity-map]
  (swap! entities-map-atom assoc name entity-map))
