(ns diagonaldifference.core
  (:require [clojure.string :as str])
  (:import java.lang.Math))


(defn recur-down-diagonal  [matrix, sumvector, index]
  (cond
    (= index (count matrix)) sumvector
    :else (recur matrix (conj sumvector (get-in matrix [index index])) (+ 1 index))))

(defn get-down-diagonal  [matrix]
  (recur-down-diagonal matrix [] 0))

(defn recur-up-diagonal  [matrix, sumvector, x-index, y-index]
  (cond
    (= y-index (count matrix)) sumvector
    :else (recur matrix (conj sumvector (get-in matrix [x-index y-index])) (- x-index 1) (+ 1 y-index))))

(defn get-up-diagonal  [matrix]
  (recur-up-diagonal matrix [] (- (count matrix) 1) 0))

(defn sum-diagonals [matrix]
  (. Math (abs (- (reduce + (get-down-diagonal  matrix)) (reduce + (get-up-diagonal  matrix))))))

(defn split-string [vector-string]
  (mapv #(Integer/parseInt %) (str/split vector-string #" ")))

(defn build-matrix [matrix, size, counter]
  (if (>= counter size) (do (println matrix) matrix)
    (recur (conj matrix (split-string (read-line))) size (inc counter))))


(defn -main []
  (let [mycount (Integer/parseInt (read-line)) ]
     (println (sum-diagonals (build-matrix [] mycount 0)))))