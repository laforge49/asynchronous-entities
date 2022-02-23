(ns ae.internals
  (:require [ae.names :as n]))

(def gem-maps-atom
  (atom {}))

(defn get-gem-map
  [key]
  (get @gem-maps-atom (n/gem-name key)))

(defn assoc-gem-map
  [key gem-map]
  (swap! gem-maps-atom assoc (n/gem-name key) gem-map))
