(ns fraud-prevention.core
  (:require [clojure.string :as str]))

(defn to-lower-if-string [value]
  (if (string? value) (clojure.string/lower-case value) value))

(defn convert-order-to-lowercase [order]
  (into {} (map (fn [[key val]] [key (to-lower-if-string val)]) order)))

(defn to-lowercase [orders]
  (mapv convert-order-to-lowercase orders))

(defn excludePlusFrom [s]
  (if (.contains s "+") (do (println "contains") (first (str/split s #"\+")))  s))

(defn format-email [order]
  (let [emailparts (str/split (:email order) #"@") ]
    (update-in order [:email] (fn [x] (str (str/replace (excludePlusFrom (first emailparts)) "." "") "@" (last emailparts))))))

(defn format-emails [orders]
  (mapv format-email orders))

(defn fix-street [street]
  (str/replace % #"st\.", "street"))

(defn fix-road [street]
  (str/replace % #"rd\.", "road"))

(defn format-street [order]
  (update-in order [:street] #((str/replace % #"st\.", "street")))
  )

(defn format-streets [orders]
  (mapv format-street orders))

(defn find-fradulent-orders [orders]
  [1])
