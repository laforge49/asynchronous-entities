(ns ae.internals
  (:require [ae.names :as n]))

(def gem-maps-atom
  (atom {}))

(defn get-gem-map
  [key]
  (get @gem-maps-atom (n/gem-identity key)))

(defn assoc-gem-map
  [key gem-map]
  (swap! gem-maps-atom assoc (n/gem-identity key) gem-map))
