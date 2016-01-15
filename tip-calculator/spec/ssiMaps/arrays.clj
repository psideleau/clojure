(ns ssiMaps.arrays  (:require [speclj.core :refer :all ]
                            ))
(defn sum-hour-glass [[top mid bottom]]
  (reduce + (concat top [(nth mid 1)] bottom)))

(defn max-hour-glass [sums]
  (reduce max sums))

(defn hour-glass-array [n]
  (loop [matrix n idx 0 hour-glass-sums []]
    (if (> idx 3)
      (max-hour-glass hour-glass-sums)
      (let [hour-glass (mapv #(vec (take 3 %)) matrix)
            new-hour-glass-sums (conj hour-glass-sums (sum-hour-glass hour-glass)  )
            matrix-shift-right (mapv (comp vec rest) matrix)]
        (recur matrix-shift-right (inc idx) new-hour-glass-sums)))))

(describe "sum of hourclass"
          (it "14"
              (should= 14 (sum-hour-glass [[1 1 1] [2 2 2] [3 3 3]])
              )))

(describe "sum of hourclass"
          (it "move"
              (should= 14 (sum-hour-glass [[1 1 1] [2 2 2] [3 3 3]])
                       ))
          (it "move 3 b3 28"
              (should= 28
                       (hour-glass-array [[0 1 2 3 4 5] [0 1 2 3 4 5] [0 1 2 3 4 5]]))))


(run-specs)