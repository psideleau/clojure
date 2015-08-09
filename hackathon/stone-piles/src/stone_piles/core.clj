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
  ;  (println "getting max for " sorted-values)
    (if-not (= 0 (first sorted-values))
      0
      (find-min 1 (rest sorted-values))
      )))

(def grundy
  (memoize (fn [val]
  (do
    ;(println "getting grundy for " val)
    (cond
      (= 0 val) 0
      (= 1 val) 0
      (= 2 val) 0
      :else

      (let [partions-of-val (s-partition val)]
     ;   (println "partition of " val " is: "  partions-of-val)
         (mex (set (map (fn [partition] (reduce bit-xor (map grundy partition))) partions-of-val)))
         )
    )))))

(def grundy-map {0 0
                 1 0
                 2 0
                 3 1
                 4 0
                 5 2
                 6 3
                 7 4
                 8 0
                 9 5
                 10 6
                 11 7
                 12 8
                 13 9
                 14 10
                 15 11
                 16 12
                 17 13
                 18 14
                 19 15
                 20 16
                 21 17
                 22 18
                 23 19
                 24 20
                 25 21
                 26 22
                 27 23
                 28 24
                 29 25
                 30 26
                 31 27
                 32 28
                 33 29
                 34 30
                 35 31
                 36 32
                 37 33
                 38 34
                 39 35
                 40 36
                 41 37
                 42 38
                 43 39
                 44 40
                 45 41
                 46 42
                 47 43
                 48 44
                 49 45
                 50 46
                 })

(defn find-nim-sum [v]
  (let [grundy-values (sort (mapv byte (mapv #(get grundy-map %) v)))]
   ; (println "nim sizes" (count grundy-values))
   ; (println grundy-values)
    ;(reduce bit-xor (mapv grundy v))))
    (reduce bit-xor grundy-values)))




(defn get-loser [users, current-play-idx]
  (if (= 0 current-play-idx) (get users 1) (get users 0)))

(defn get-next-user-idx [current-play-idx]
  (if (= 0 current-play-idx) 1 0))

(defn play-games [users, piles, current-play-idx]
  (let [nim-sum (find-nim-sum piles)]
   ; (println "pile size" (count piles))
  ;  (println "nim-sum" nim-sum)
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



