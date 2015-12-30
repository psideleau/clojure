(defproject tip-calculator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 ;;ClojureScript
                 [org.clojure/clojurescript "1.7.122"]
                 [cljs-ajax "0.5.1"]
                 [prismatic/dommy "1.1.0"]]
  :ring {:handler tip-calculator.service/app
         :auto-reload? true
         :auto-refresh? true}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]
                                  [speclj "3.3.1"]]}}
  :plugins [[lein-ring "0.9.7"]
            [speclj "3.3.1"]
            [lein-figwheel "0.4.1"]]
  :cljsbuild {
              :builds [ { :id "tip-calculator"
                         :source-paths ["src/cljs"]
                         :figwheel true
                         :compiler {:main "tip-calculator.client"
                                    :asset-path "js/out"
                                    :output-to "resources/public/js/app.js"
                                    :output-dir "resources/public/js/out"
                                    :optimizations :none
                                    :source-map true} } ]
              }
  :test-paths ["spec"])
