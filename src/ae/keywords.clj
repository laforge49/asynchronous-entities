(ns ae.keywords
  (:require [clojure.string :as s]))

(defn keyword-encode-
  [s i d e]
  (let [j
        (s/index-of s d i)]
    (if (nil? j)
      s
      (recur (str (subs s 0 j) e (subs s (inc j))) (+ j 3) d e))))

(defn keyword-encode
  [context-name base-name]
  (keyword context-name (-> base-name
                  (keyword-encode- 0 " " "$$s")
                  (keyword-encode- 0 "(" "$$l")
                  (keyword-encode- 0 ")" "$$r"))))

(defn name-as-keyword
  [name]
  (let [slashindex
        (s/index-of name "/")
        base-name
        (subs name (inc slashindex))
        context-name
        (subs name 0 slashindex)
        name-kw
        (keyword-encode context-name base-name)
        ]
    [name-kw context-name base-name]))

(defn keyword-decode-
  [s i e d]
  (let [j
        (s/index-of s e i)]
    (if (nil? j)
      s
      (recur (str (subs s 0 j) d (subs s (+ j 3))) (inc j) e d))))

(defn keyword-as-name
  [kw]
  (let [context-name
        (namespace kw)
        base-name
        (-> (name kw)
            (keyword-decode- 0 "$$s" " ")
            (keyword-decode- 0 "$$l" "(")
            (keyword-decode- 0 "$$r" ")"))
        name
        (str context-name
             "/"
             base-name)
        ]
    [name context-name base-name]
  ))
