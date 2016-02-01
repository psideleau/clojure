(ns ssiMaps.funny  (:require [speclj.core :refer :all ]
                            ))

(defn reduce-word [word-seq]
  (let [int-values (mapv int (vec word-seq))]
    (map-indexed (fn[idx curr]
      (if (= idx 0)
        0
        (Math/abs (- curr (nth int-values (dec idx)))))) int-values)))

(defn calc-funny [word]
  (let [seq-word (seq word)
        reverse-word (clojure.string/reverse word)]
    (if (= (reduce-word seq-word) (reduce-word reverse-word))
           "Funny"
           "Not Funny")))


(describe "should be funny"
  (it "14"
    (should= "Funny" (calc-funny "acxz"))
    (should= "Not Funny" (calc-funny "bcxz"))))


(run-specs)