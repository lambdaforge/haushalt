(defproject haushalt "0.1.0-SNAPSHOT"
  
  :description "Domestic resources management service"
  
  :url "https://github.com/lambda-kollektiv/haushalt"
  
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj" "src/cljs"]

  :dependencies [[org.clojure/clojure "1.7.0-beta2"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [kioo "0.4.0"]
                 [org.omcljs/om "0.9.0"]]

  :min-lein-version "2.5.0"
  
  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.7"]]

  :clean-targets ^{:protect false}["resources/public/js/compiled" "target"]

  :figwheel
  {:css-dirs ["resources/public/css"]

   }
  
  :cljsbuild
  {:builds
   [{:source-paths ["src/cljs"]
     :figwheel true
     :compiler
     {:output-to "resources/public/js/compiled/main.js"
      :output-dir "resources/public/js/compiled/out"
      :main "haushalt.core"
      :asset-path "js/compiled/out"
      :optimizations :none
      :pretty-print false
      :source-map "main.js.map"}}]})
