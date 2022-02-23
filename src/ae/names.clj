(ns ae.names
  (:require [clojure.string :as s]))

(defn gem-name
  [key]
  (if (nil? key)
    nil
    (let [i
          (s/index-of key " ")]
      (if (nil? i)
        key
        (let [j
              (s/last-index-of key " ")]
          (if (not= i j)
            (throw (Exception. (str "A key can have one space at most: " key))))
          (subs key (inc i)))))))

(def styp-set
  #{"map" "vec" "mapmap" "mapvec"})

(def dtyp-set
  #{"bool" "str" "int" "chan"})

(defn parse-gem-name
  [key]
  (let [s
        (gem-name key)
        _ (if (some? (s/index-of s "."))
            (throw (Exception. (str "Gem name " s " should not contain a ."))))
        _ (if (some? (s/index-of s "/"))
            (throw (Exception. (str "Gem name " s " should not contain a /"))))
        p-ndx
        (s/index-of s "+")
        _ (if (not= p-ndx (s/last-index-of s "+"))
            (throw (Exception. (str "Gem name " s " should not contain two +'s"))))
        u-ndx
        (s/index-of s "_")
        _ (if (not= u-ndx (s/last-index-of s "_"))
            (throw (Exception. (str "Gem name " s " should not contain two _'s"))))
        h-ndx
        (s/index-of s "-")
        _ (if (not= h-ndx (s/last-index-of s "-"))
            (throw (Exception. (str "Gem name " s " should not contain two -'s"))))
        _ (if (nil? h-ndx)
            (throw (Exception. (str "Gem name " s " is missing a -"))))
        c-ndx
        (s/index-of s "^")
        _ (if (not= c-ndx (s/last-index-of s "^"))
            (throw (Exception. (str "Gem name " s " should not contain two ^'s"))))
        t-ndx
        (s/index-of s "@")
        _ (if (not= t-ndx (s/last-index-of s "@"))
            (throw (Exception. (str "Gem name " s " should not contain two @'s"))))
        _ (if (and (some? c-ndx) (some? t-ndx))
            (throw (Exception. (str "Gem name " s " can not have both ^ and @"))))
        a-ndx
        (s/index-of s "&")
        _ (if (not= a-ndx (s/last-index-of s "&"))
            (throw (Exception. (str "Gem name " s " should not contain two &'s"))))
        d-ndx
        (s/index-of s "$")
        _ (if (not= d-ndx (s/last-index-of s "$"))
            (throw (Exception. (str "Gem name " s " should not contain two $'s"))))
        _ (if (and (some? a-ndx) (some? d-ndx))
            (throw (Exception. (str "Gem name " s " can not have both & and $"))))
        typ-start
        (if (some? p-ndx)
          (inc p-ndx)
          0)
        typ-end
        (if (some? u-ndx)
          u-ndx
          h-ndx)
        _ (if (> typ-start typ-end)
            (throw (Exception. (str "Gem name " s " lacks a properly delineated gem type"))))
        typ
        (subs s typ-start typ-end)
        _ (if (empty? typ)
            (throw (Exception. (str "Gem name " s " has an empty gem type"))))
        styp
        (if (nil? u-ndx)
          nil
          (if (> u-ndx h-ndx)
            (throw (Exception. (str "Gem name " s " has an improperly delineated structure type")))
            (subs s (inc u-ndx) h-ndx)))
        _ (if (and (some? u-ndx) (empty? styp))
            (throw (Exception. (str "Gem name " s " has a _ but the structure type is empty"))))
        _ (if (not= (and (some? styp)
                         (or (s/starts-with? styp "map") (s/ends-with? styp "map")))
                    (or (some? c-ndx) (some? t-ndx)))
            (throw (Exception.
                     (str "Gem name " s " Can include a ^ or @ if and only if the structure type contains a map"))))
        root-end
        (if (some? c-ndx)
          c-ndx
          (if (some? t-ndx)
            t-ndx
            (if (some? a-ndx)
              a-ndx
              (if (some? d-ndx)
                d-ndx
                (count s)))))
        _ (if (> h-ndx root-end)
            (throw (Exception. (str "Gem name " s " lacks a properly delineated base name"))))
        root
        (subs s (inc h-ndx) root-end)
        _ (if (empty? root)
            (throw (Exception. (str "Gem name " s " has an empty base name"))))
        ktyp
        (if (nil? c-ndx)
          nil
          (if (some? a-ndx)
            (if (> a-ndx c-ndx)
              (subs s (inc c-ndx) a-ndx)
              (throw (Exception. (str "Gem name " s " has an improperly delineated key name type"))))
            (if (some? d-ndx)
              (if (> d-ndx c-ndx)
                (subs s (inc c-ndx) d-ndx)
                (throw (Exception. (str "Gem name " s " has an improperly delineated key name type"))))
              (subs s (inc c-ndx)))))
        _ (if (and (some? c-ndx) (empty? ktyp))
            (throw (Exception. (str "Gem name " s " has a ^ but the key name type is empty"))))
        ttyp
        (if (nil? t-ndx)
          nil
          (if (some? a-ndx)
            (if (> a-ndx t-ndx)
              (subs s (inc t-ndx) a-ndx)
              (throw (Exception. (str "Gem name " s " has an improperly delineated key data type"))))
            (if (some? d-ndx)
              (if (> d-ndx t-ndx)
                (subs s (inc t-ndx) d-ndx)
                (throw (Exception. (str "Gem name " s " has an improperly delineated key data type"))))
              (subs s (inc t-ndx)))))
        _ (if (and (some? t-ndx) (empty? ttyp))
            (throw (Exception. (str "Gem name " s " has an @ but the key data type is empty"))))
        ntyp
        (if (nil? a-ndx)
          nil
          (subs s (inc a-ndx)))
        _ (if (and (some? a-ndx) (empty? ntyp))
            (throw (Exception. (str "Gem name " s " has a & but the value named type is empty"))))
        dtyp
        (if (nil? d-ndx)
          nil
          (subs s (inc d-ndx)))
        _ (if (and (some? d-ndx) (empty? dtyp))
            (throw (Exception. (str "Gem name " s " has a $ but the value data type is empty"))))]
    (if (and (some? styp) (not (contains? styp-set styp)))
      (throw (Exception. (str "Gem name " s " has an unknown structure type: " styp))))
    (if (and (some? ttyp) (not (contains? dtyp-set ttyp)))
      (throw (Exception. (str "Gem name " s " has an unknown key data type: " ttyp))))
    (if (and (some? dtyp) (not (contains? dtyp-set dtyp)))
      (throw (Exception. (str "Gem name " s " has an unknown value data type: " dtyp))))
    [typ styp root ktyp ttyp ntyp dtyp]))

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
  (let [name
        (gem-name name)
        plus-index
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

#_(defn keyword-decode-
    [s i e d]
    (let [j
          (s/index-of s e i)]
      (if (nil? j)
        s
        (recur (str (subs s 0 j) d (subs s (+ j 3))) (inc j) e d))))

#_(defn keyword-as-name
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
