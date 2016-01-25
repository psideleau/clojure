(ns ssiMaps.arrays  (:require [speclj.core :refer :all ]
                            ))

(defn diffs-map [values]
  (let [sorted-values (sort values)]
    (loop [values sorted-values
           differences []]
      (if (= 1 (count values))
        differences
        (let [x (first values)
              rest-values (rest values)
              y (first rest-values)
              diff (Math/abs (- x y))]
          (recur rest-values (cons {:pair (list x y) :diff diff} differences)))))))

(defn min-diff [values]
  (let [diffs-map  (diffs-map values)
        min-value  (apply min (map #(:diff %) diffs-map))
        min-values (filter #(= min-value (:diff %)) diffs-map)]
    (->> (map #(:pair %) min-values)
         (reduce into)
         (sort)
         (clojure.string/join " "))))

(describe "absolute difference"
  (it "14"
    (should= "2 3 3 4 4 5" (min-diff [5 4 3 2])
      ))

  (it "142"
    (should= "-520 -470 -20 30" (min-diff
                                  [-20 -3916237 -357920 -3620601 7374819 -7330761 30 6246457 -6461594 266854 -520 -470])
      )))



(run-specs)