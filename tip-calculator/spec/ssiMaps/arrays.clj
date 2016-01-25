(ns ssiMaps.arrays  (:require [speclj.core :refer :all ]
                            ))
(defn sum-hour-glass [[top mid bottom]]
  (reduce + (concat top [(nth mid 1)] bottom)))

(defn max-hour-glass [sums]
  (reduce max sums))

(defn hour-glass-left-to-right [n]
  (loop [matrix n idx 0 hour-glass-sums []]
    (if (> idx 3)
      hour-glass-sums
      (let [hour-glass (mapv #(vec (take 3 %)) matrix)
            new-hour-glass-sums (conj hour-glass-sums (sum-hour-glass hour-glass)  )
            matrix-shift-right (mapv (comp vec rest) matrix)]
        (recur matrix-shift-right (inc idx) new-hour-glass-sums)))))

(defn hour-glass-array [n]
  (loop [matrix n idx 0 hour-glass-sums []]
    (if (> idx 3)
      (max-hour-glass hour-glass-sums)
      (let [new-hour-glass-sums (concat hour-glass-sums (hour-glass-left-to-right matrix) )
            matrix-shift-down (rest matrix)]
        (recur matrix-shift-down (inc idx) new-hour-glass-sums)))))

(describe "sum of hourclass"
          (it "14"
              (should= 14 (sum-hour-glass [[1 1 1] [2 2 2] [3 3 3]])
              )))

(describe "sum of hourclass"
          (it "move"
              (should= 14 (sum-hour-glass [[1 1 1] [2 2 2] [3 3 3]])
                       ))
          (it "move 3"
              (should= 19
                       (hour-glass-array [[1 1 1 0 0 0]
                                          [0 1 0 0 0 0]
                                          [1 1 1 0 0 0]
                                          [0 0 2 4 4 0]
                                          [0 0 0 2 0 0]
                                          [0 0 1 2 4 0]]))))


(run-specs)