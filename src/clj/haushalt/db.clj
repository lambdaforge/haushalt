(ns haushalt.db
  (:require [datomic.api :as d]))


(def db-uri-base "datomic:free://0.0.0.0:4334")


(defn db-conn []
  ((let  [uri (str db-uri-base "/haushalt")])
   (d/create-database uri)
   (d/connect uri)))

(defn init-schema [conn file]
  (transact-all conn (io/resource file)))

(defn get-entity [conn id]
  (entity (d/db conn) id))

(defn get-revenues [conn] ;; test
  (get-entity (d/db conn)
              (d/q '[:find ?e
                     :where
                     [?e :revenue/user ?uid]
                     [?uid :user/email ?email]]
                   (d/db conn))))

;;(def conn (db-conn))
;;(init-schema conn "schema.edn") ;; ueberarbeiten
