(ns ae.operations.swing-operations
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [ae.operations.reports :as r]
            [ae.later :as l]
            [ae.kernel :as k]
            [ae.keywords :as kw]))

(defn ui-creation-goblock
  [env this-map params])

(defn create-swing-operations
  [env]
  (k/register-function env {:operationid "UI_CREATIONoperationid"
                            :goblock     ui-creation-goblock})
  )
