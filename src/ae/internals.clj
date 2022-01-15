(ns ae.internals)

(def gem-maps-atom
  (atom {}))

(defn get-gem-map
  [name]
  (get @gem-maps-atom name))

(defn assoc-gem-map
  [name gem-map]
  (swap! gem-maps-atom assoc name gem-map))
