(ns haushalt.db
  (require [datomic.api :as d]
           [clojure.java.io :as io])
  (:import datomic.Util))

(def db-uri-base "datomic:free://0.0.0.0:4334")

(defn scratch-conn
  "Create a connection to an anonymous, in-memory database."
  []
  (let [uri (str "datomic:mem://" (d/squuid))]
    (d/delete-database uri)
    (d/create-database uri)
    (d/connect uri)))


(defn persistent-conn
  "Create connection to persistent datomic database"
  []
  (let [uri (str db-uri-base "/haushalt")]
    (d/create-database uri)
    (d/connect uri)))


(defn initialize-db [conn path]
  (doseq [txd (-> path io/resource io/reader Util/readAll)]
    (d/transact conn txd))
  :done)


(defn get-schema-elements
  "Get identifiers of schema elements"
  [db]
  (sort
   (d/q '[:find [?ident ...]
          :where [_ :db/ident ?ident]]
        db)))



(comment

  (def conn (scratch-conn))

  (def db (d/db conn))
  
  (initialize-db conn "database/schema.edn")

  (get-schema-elements db)
  
  )
>>>>>>> 26f996e03fbf6f3a8c4b3c80e76a2d72cac6631c
