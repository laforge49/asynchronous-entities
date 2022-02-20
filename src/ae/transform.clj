(ns ae.transform
  (:require [clojure.core.async :as a]
            [clojure.string :as s]
            [tupelo.parse.yaml :as yaml]
            [ae.keywords :as kw]
            [ae.kernel :as k]))

(def testChanClass (class (a/chan)))

(def styp-set
  #{"map" "vec" "mapmap" "mapvec"})

(def dtyp-set
  #{"bool" "str" "int" "chan"})

(defn parse-gem-name
  [key]
  (let [s
        (kw/gem-name key)
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

(defn validate-names
  [edn parent-styp parent-ktyp parent-ttyp parent-ntyp parent-dtyp env]
  (cond
    (string? edn)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a scalar, not structure type " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str (pr-str edn) " is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (some? parent-dtyp)
          (if (not= parent-dtyp "str")
            (throw (Exception. (str (pr-str edn) " is not of data type " (pr-str parent-dtyp)))))
          (let [[typ styp root ktyp ttyp ntyp dtyp]
                (parse-gem-name edn)]
            (if (nil? parent-ntyp)
              (throw (Exception. (str "There is no name type for " (pr-str edn)))))
            (if (and (not= parent-ntyp "%") (not= typ parent-ntyp))
              (throw (Exception. (str (pr-str edn) " is not of name type " parent-ntyp))))
            (if (not (k/entity-exists? edn))
              (throw (Exception. (str "No such entity: " (pr-str edn)))))))))

    (vector? edn)
    (if (and (not (s/starts-with? parent-styp "vec")) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a vector, not structure typ " (pr-str parent-styp))))
      (let [styp
            nil]
        (doseq [item edn]
          (validate-names item styp parent-ktyp parent-ttyp parent-ntyp parent-dtyp env))))

    (map? edn)
    (doseq [[k v] edn]
      (if (some? v)
        (let [[typ styp root ktyp ttyp ntyp dtyp]
              (parse-gem-name k)
              styp
              (if (= parent-styp "mapmap")
                "map"
                (if (= parent-styp "mapvec")
                  "vec"
                  (if (= parent-styp "map")
                    styp
                    (throw (Exception. (str (pr-str edn) " is a map, not structure typ " (pr-str parent-styp)))))))
              _ (if (and (not= parent-ktyp "%") (not= typ parent-ktyp))
                  (throw (Exception. (str (pr-str k) " is not the expected key type: " (pr-str parent-ktyp)))))
              ktyp
              (if (and (nil? ktyp) (nil? ttyp))
                "%"
                ktyp)
              [ntyp dtyp]
              (if (or (some? parent-ntyp) (some? parent-dtyp))
                [parent-ntyp parent-dtyp]
                [ntyp dtyp])]
          (validate-names k nil nil nil parent-ktyp parent-ttyp env)
          (validate-names v styp ktyp ttyp ntyp dtyp env))))

    (boolean? edn)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a scalar, not structure typ " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str (pr-str edn) " is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (not= parent-dtyp "bool")
          (throw (Exception. (str (pr-str edn) "is boolean, not "
                                  (if (nil? parent-dtyp)
                                    "a name"
                                    parent-dtyp)))))))

    (int? edn)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str (pr-str edn) " is a scalar, not structure typ " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str (pr-str edn) " is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (not= parent-dtyp "int")
          (throw (Exception. (str (pr-str edn) "is integer, not "
                                  (if (nil? parent-dtyp)
                                    "a name"
                                    parent-dtyp)))))))

    (= (class edn) clojure.core.async.impl.channels.ManyToManyChannel)
    (if (and (some? parent-styp) (not= parent-styp "%"))
      (throw (Exception. (str "clojure.core.async.chan is a scalar, not structure typ " (pr-str parent-styp))))
      (if (and (some? parent-ktyp) (not= parent-ktyp "%"))
        (throw (Exception. (str "clojure.core.async.chan is a scalar, and does not accept a key type " (pr-str parent-ktyp))))
        (if (not= parent-dtyp "chan")
          (throw (Exception. (str "clojure.core.async.chan is not "
                                  (if (nil? parent-dtyp)
                                    "a name"
                                    parent-dtyp)))))))

    true
    (throw (Exception. (str "Data type " (pr-str parent-dtyp) " is not known, value: " (pr-str edn))))))

(defn unbind-context
  [edn parent-dtyp env]
  (cond
    (string? edn)
    (if (some? parent-dtyp)
      edn
      (let [sndx
            (s/index-of edn " ")
            [order edn]
            (if (nil? sndx)
              [nil edn]
              [(subs edn 0 sndx) (subs edn (inc sndx))])
            ndx
            (s/index-of edn "+")
            _ (if (nil? ndx)
                (throw (Exception. (str "Expecting a + in name " edn))))
            edn
            (subs edn (inc ndx))
            edn
            (if (nil? order)
              edn
              (str order " " edn))]
        edn))

    (vector? edn)
    (reduce
      (fn [v item]
        (conj v (unbind-context item parent-dtyp env)))
      []
      edn)

    (map? edn)
    (let [edn
          (reduce
            (fn [m [k v]]
              (if (or (nil? v)
                      (and (map? v) (empty? v))
                      (and (vector? v) (empty? v)))
                m
                (let [[typ styp root ktyp ttyp ntyp dtyp]
                      (parse-gem-name k)
                      dtyp
                      (if (some? parent-dtyp)
                        parent-dtyp
                        dtyp)]
                  (assoc m (unbind-context k nil env)
                           (unbind-context v dtyp env)))))
            (sorted-map)
            edn)]
      edn)

    (boolean? edn)
    edn

    (= (class edn) testChanClass)
    "clojure.core.async.chan"

    true
    (pr-str (class edn))))

(defn bind-context-
  [local-context resources-set edn parent-styp parent-ktyp parent-ntyp parent-dtyp env]
  (cond
    (string? edn)
    (if (some? parent-dtyp)
      edn
      (let [sndx
            (s/index-of edn " ")
            [order edn]
            (if (nil? sndx)
              [nil edn]
              [(subs edn 0 sndx) (subs edn (inc sndx))])
            ndx
            (s/index-of edn "+")
            un-edn
            (if (some? ndx)
              (subs edn (inc ndx))
              edn)
            full-edn
            (reduce
              (fn [full-edn resource]
                (if (some? full-edn)
                  full-edn
                  (let [full-edn
                        (str resource "+" un-edn)]
                    (if (k/entity-exists? full-edn)
                      full-edn
                      nil))))
              nil
              resources-set)
            full-edn
            (if (some? full-edn)
              full-edn
              (if (= un-edn "context-SYS")
                "ROOT+context-SYS"
                (str local-context "+" un-edn)))]
        (if (some? ndx)
          (if (not= edn full-edn)
            (throw (Exception. (str edn " should have been " full-edn)))))
        (if (nil? order)
          full-edn
          (str order " " full-edn))))

    (vector? edn)
    (let [styp
          nil]
      (reduce
        (fn [v item]
          (conj v (bind-context- local-context resources-set item styp parent-ktyp parent-ntyp parent-dtyp env)))
        []
        edn))

    (map? edn)
    (reduce
      (fn [m [k v]]
        (let [[typ styp root ktyp ttyp ntyp dtyp]
              (parse-gem-name k)
              styp
              (if (= parent-styp "mapmap")
                "map"
                (if (= parent-styp "mapvec")
                  "vec"
                  styp))
              [ntyp dtyp]
              (if (or (some? parent-ntyp) (some? parent-dtyp))
                [parent-ntyp parent-dtyp]
                [ntyp dtyp])]
          (assoc m (bind-context- local-context resources-set k nil nil "%" nil env)
                   (bind-context- local-context resources-set v styp ktyp ntyp dtyp env))))
      (sorted-map)
      edn)

    true
    edn))

(defn bind-context
  [full-context-name edn styp ktyp ntyp dtyp env]
  (let [[_ _ context-base-name]
        (kw/name-as-keyword full-context-name)
        short-context-name
        (if (s/starts-with? context-base-name "context-")
          (subs context-base-name 8)
          context-base-name)
        resources-set
        (k/get-resources-set full-context-name)]
    (bind-context- short-context-name resources-set edn styp ktyp ntyp dtyp env)))

(defn parse-bind-script
  [yaml-script this-map env]
  (let [full-local-context
        (get this-map "SYS+facet-NAME&%")
        edn-script
        (yaml/parse-raw yaml-script)
        edn-script
        (bind-context full-local-context edn-script "map" "request" nil nil env)]
    edn-script))

(defn edn-to-yaml
  ([edn]
   (apply str (edn-to-yaml edn 0 [])))
  ([edn off sv]
   (cond
     (map? edn)
     (reduce
       (fn [sv [k v]]
         (let [sv
               (into sv (repeat off " "))
               sv
               (conj sv k ":\n")
               sv
               (edn-to-yaml v (+ off 2) sv)]
           sv))
       sv
       edn)

     true
     (do
       #_ (println :? (prn-str edn))
       (conj (into sv (repeat off " "))
             (yaml/edn->yaml edn))))))

