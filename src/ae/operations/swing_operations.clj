(ns ae.operations.swing-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
            [ae.later :as l]
            [ae.kernel :as k]
            [ae.keywords :as kw])
  (:import (javax.swing SwingUtilities JFrame JLabel)))

(defn create-and-show-gui
  []
  (let [my-frame (doto (JFrame. "My Frame")
                   (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE))
        my-label (JLabel. "Hello UI")
        content-pane (.getContentPane  my-frame)]

    (.add content-pane my-label)
    (.pack my-frame)
    (.setVisible my-frame true)))

(defn ui-creation-goblock
  [env this-map params]
  (println :!!!!!!!!!!!!!)
  (a/go
    (SwingUtilities/invokeLater create-and-show-gui)
#_    (let [operation-return-port
          (get params "SYS+param-OPERATIONreturnport")]
      (try
        (a/>! operation-return-port [this-map nil])
        (catch Exception e
          (a/>! operation-return-port [this-map e]))))
    ))

(defn create-swing-operations
  [env]
  (k/register-function env {:operationid "UI_CREATIONoperationid"
                            :goblock     ui-creation-goblock})
  )
