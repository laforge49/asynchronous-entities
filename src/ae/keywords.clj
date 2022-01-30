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
  [context-base-name base-name]
  (if (s/blank? context-base-name)
    (keyword base-name)
    (keyword context-base-name (-> base-name
                                   (keyword-encode- 0 " " "$$s")
                                   (keyword-encode- 0 "(" "$$l")
                                   (keyword-encode- 0 ")" "$$r")))))

(defn name-as-keyword
  [name]
  (let [plus-index
        (s/index-of name "+")
        base-name
        (if (nil? plus-index)
          name
          (subs name (inc plus-index)))
        context-base-name
        (if (nil? plus-index)
          "SYS"
          (subs name 0 plus-index))
        name-kw
        (keyword-encode context-base-name base-name)
        name-kw
        (if (nil? plus-index)
          (keyword name)
          name-kw)
        ]
    [name-kw context-base-name base-name]))

#_ (defn keyword-decode-
  [s i e d]
  (let [j
        (s/index-of s e i)]
    (if (nil? j)
      s
      (recur (str (subs s 0 j) d (subs s (+ j 3))) (inc j) e d))))

#_ (defn keyword-as-name
  [kw]
  (let [context-base-name
        (namespace kw)
        base-name
        (-> (name kw)
            (keyword-decode- 0 "$$s" " ")
            (keyword-decode- 0 "$$l" "(")
            (keyword-decode- 0 "$$r" ")"))
        name
        (if (s/blank? context-base-name)
          base-name
          (str context-base-name
               "+"
               base-name))
        ]
    [name context-base-name base-name]
    ))
