(ns bigsum.core
  (:require [clojure.string :as str]))

(defn sum [vector-to-sum]
  (reduce + vector-to-sum))

(defn -main []
  (let [mycount (read-line) mylist (read-line)]
    (println (bigsum.core/sum (map #(Integer/parseInt %)(str/split mylist #" "))))))