(ns ae.internals
  (:require [ae.keywords :as kw]))

(def gem-maps-atom
  (atom {}))

(defn get-gem-map
  [key]
  (get @gem-maps-atom (kw/gem-name key)))

(defn assoc-gem-map
  [key gem-map]
  (swap! gem-maps-atom assoc (kw/gem-name key) gem-map))
