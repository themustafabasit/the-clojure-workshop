(ns the-clojure-workshop.chapter13-db-interaction.db-connection
  (:require [clojure.java.jdbc :as jdbc]))

(def db {:dbtype "derby"
         :dbname "derby-local"
         :create true})

(jdbc/get-connection db)
;; => #object[org.apache.derby.impl.jdbc.EmbedConnection 0x673493b3 "org.apache.derby.impl.jdbc.
;;    EmbedConnection@1731498931 (XID = 166), (SESSIONID = 1), (DATABASE = derby-local), (DRDAID = null) "]
