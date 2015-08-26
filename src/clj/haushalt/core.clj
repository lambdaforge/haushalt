(ns haushalt.core
  (:gen-class :main true)
  (:require [clojure.java.io :as io]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [resources]]
            [compojure.handler :refer [site]]
            [net.cgrand.enlive-html :refer [content html deftemplate]]
            [org.httpkit.server :refer [run-server]]))

(deftemplate landing-page
  (io/resource "public/index.html")
  []
  [:#view-container] (content (html [:h1 "Greetings"])))

(defroutes handler
  (GET "/*" [] (landing-page))
  (resources "/"))

(defn start-server [port]
  (run-server (site #'handler) {:port port :join false}))

(defn -main [& args]
  (start-server 8092))

(comment

  (def server (start-server 8092))

  (server)
  
  )
