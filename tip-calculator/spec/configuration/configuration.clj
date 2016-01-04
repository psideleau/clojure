(ns configuration.configuration
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:classname "net.sourceforge.jtds.jdbc.Driver"
   :subprotocol "jtds:sqlserver"
   :subname ""
   :user ""
   :password ""})

(defn clob-to-string [clob]
  "Turn an Oracle Clob into a String"
  (with-open [rdr (java.io.BufferedReader. (.getCharacterStream clob))]
    (apply str (line-seq rdr))))

(defn convert-clobs-to-strings [records]
  (map #(update-in %1 [:value] clob-to-string) records ))

(defn get-configuration []
  (convert-clobs-to-strings
      (jdbc/query db-spec ["SELECT * FROM table  with (nolock)"])))

(get-configuration)



