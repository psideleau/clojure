(ns ssiMaps.gcd  (:require [speclj.core :refer :all ]
                            ))
(defn gcd [x y]
  (if (= x y)
     x
     (let [max-x (max x y)
           min-y (min x y)
           new-x (- max-x min-y)]
       (recur new-x min-y))))
(defn compute-gcd [input]
  (let [input-results (map #(Integer/parseInt %) input)]
    (println (gcd (nth input-results 0) (nth input-results 1)))))

(describe "GCD"
          (it "GCD"
              (should= 1 (gcd 1 1))
              (should= 4 (gcd 4 8) )
              (should= 4 (gcd 12 8))
              (should= 6 (gcd 54 24))))


(run-specs)