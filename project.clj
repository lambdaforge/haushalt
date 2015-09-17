(defproject haushalt "0.1.0-SNAPSHOT"

  :description "A domestic management application"

  :url "http://lambda-kollektiv/haushalt"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.107"]

                 [http-kit "2.1.19"]
                 [ring "1.4.0"]
                 [enlive "1.1.6"]
                 [compojure "1.4.0"]
                 [com.taoensso/timbre "4.1.1"]

                 [com.datomic/datomic-free "0.9.5206"]
                 [prismatic/dommy "1.1.0"]
                 [om "0.7.3"]
                 [kioo "0.4.0"]
                 [figwheel "0.3.7"]
                 [clj-time "0.11.0"]
                 [aprint "0.1.3"]]

  :source-paths ["src/cljs" "src/clj"]

  :min-lein-version "2.0.0"

  :main haushalt.core

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.3.7"]]

  :figwheel {:http-server-root "public"
             :port 3449
             :css-dirs ["resources/public/css"]}

  :clean-targets ^{:protect false}["target" "resources/public/js/compiled"]
  
  :cljsbuild
  {:builds
   {:dev
    {:source-paths ["src/cljs/lesezeichen/client"]
     :compiler {:output-to "resources/public/js/compiled/main.js"
                :output-dir "resources/public/js/compiled/out"
                :optimizations :none
                :source-map true}}
    :prod
    {:source-paths ["src/cljs/lesezeichen/client"]
     :compiler {:output-to "resources/public/js/main.js"
                :optimizations :advanced}}}})
