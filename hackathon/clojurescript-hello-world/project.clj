(defproject clojurescript-hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"] [org.clojure/clojurescript "1.7.107"]]
  :plugins [[lein-cljsbuild "1.1.0"]]
  :source-paths ["src/clj"]
  :target-path "target/%s"
  :cljsbuild {:builds
              [{:source-paths ["src/cljs"],
                :compiler
                {:optimizations :whitespace,
                 :output-to "resources/public/hello.js",
                 :pretty-print true}}]}

  :profiles {:uberjar {:aot :all}})
