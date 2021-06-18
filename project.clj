(defproject asynchronous-entities "0.1.0-SNAPSHOT"
  :description "A universal persistent data structure"
  :url "https://github.com/laforge49/asynchronous-entities"
  :license {:name "APACHE LICENSE, VERSION 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.3"]]
  :main ^:skip-aot ae.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
