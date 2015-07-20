(ns stone-piles.core
  (:require [clojure.string :as str]))

(defn grow-stones [stones, new-stones]
  (if (empty? stones) new-stones
    (let [val (first stones)]
      (cond
        (or (= val 1) (= val 2)) (recur (rest stones) (conj new-stones val))
        (= val 4) (recur (rest stones) (vec (concat new-stones [1, 3])))
        :else (recur (rest stones) (vec (concat new-stones [2, (- val 2)])))))))


(defn split-stones [stones]
  (grow-stones stones []))

(defn game-over? [stones]
  (every? #(or (= % 1) (= % 2)) stones))

(defn get-loser [users, current-play-idx]
  (if (= 0 current-play-idx) (get users 1) (get users 0)))

(defn get-next-user-idx [current-play-idx]
  (if (= 0 current-play-idx) 1 0))

(defn play-games [users, piles, current-play-idx]
  (do
    (println piles)
    (println current-play-idx)
  (if (game-over? piles) (get-loser users current-play-idx)
    (recur users (split-stones piles) (get-next-user-idx current-play-idx)))))

(defn play-game [game]
  (play-games (:users game) (:piles game) 0))

