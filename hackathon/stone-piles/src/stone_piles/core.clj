(ns stone-piles.core
  (:require [clojure.string :as str])
  (:require [clojure.set  :as s]))

(defn   game-over? [stones]
  (every? #(or (= % 1) (= % 2)) stones))

(defn partition-full [n, max, sum, sums]
  (do

  (if (= n 0)
    (when (and (= (count (set sum)) (count sum)) (> (count sum) 1))
      (swap! sums conj sum))
    (let [i (atom (Math/min max n))]
      (while (>= @i 1)
        (do
          (partition-full (- n @i) @i (conj sum @i) sums)
          (swap! i dec)))))))

(def s-partition
  (memoize (fn [n]
    (let [sums (atom [])]
      (partition-full n n [] sums)
      @sums))))


(defn find-min [min-val values]
  (if (= min-val (first values))
    (recur (inc min-val) (rest values))
    min-val))

(defn mex [values]
  (let [sorted-values (sort (vec values))]
    (if-not (= 0 (first sorted-values))
      0
      (find-min 1 (rest sorted-values))
      )))

(def grundy
  (memoize (fn [val]
    (cond
      (= 0 val) 0
      (= 1 val) 0
      (= 2 val) 0
      (= 3 val)  (mex (set [(+ (grundy 2) (grundy 1))]))
      (= 4 val)  (mex (set [(+ (grundy 3) (grundy 1))]))
      (= 5 val)  (mex (s/union (set [(+ (grundy 4) (grundy 1))])(set [(+ (grundy 3) (grundy 2))])))
      :else
       (let [partions-of-val (s-partition val)]
         (mex (set (map (fn [partition] (reduce + (map #(grundy %) partition))) partions-of-val)))
         )
    ))))

(defn find-nim-sum [v]
  (do
    ;(println (mapv grundy v))
    (reduce bit-xor (mapv grundy v))))



(defn get-loser [users, current-play-idx]
  (if (= 0 current-play-idx) (get users 1) (get users 0)))

(defn get-next-user-idx [current-play-idx]
  (if (= 0 current-play-idx) 1 0))

(defn play-games [users, piles, current-play-idx]
  (let [nim-sum (find-nim-sum piles)]
    (cond
     (game-over? piles) (get users 1)
      (= 0 nim-sum) (get users 1)
      :else (get users 0)
    )))

(defn play-game [game]
  (play-games (:users game) (:piles game) 0))

(defn split-string [vector-string]
  (mapv #(Integer/parseInt %) (str/split vector-string #" ")))

(defn get-test-cases [test-cases number-of-tests, current-count]
  (if (= current-count number-of-tests)
    test-cases
    (recur (conj test-cases
             {:users ["ALICE", "BOB"],
              :amount (Integer/parseInt (read-line))
              :piles (split-string (read-line))
              }) number-of-tests (inc current-count))))

(defn -main []
 (let [mycount (Integer/parseInt (read-line)) test-cases (get-test-cases [] mycount 0) ]
   (doseq [game test-cases] (println (play-game game)))))



