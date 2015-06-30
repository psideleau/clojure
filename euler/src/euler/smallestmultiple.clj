(ns euler.smallestmultiple
  (require [clojure.string :as s]))



(defn is-divisible-for-all-seq? [numberToTest rangeOfNumbers]
  (every? #(=(mod numberToTest %) 0) rangeOfNumbers)
  )()

(defn find-first-number [number1 rangeOfNumbers]
  (if (is-divisible-for-all-seq? number1 rangeOfNumbers)
    number1
    (recur (inc number1) rangeOfNumbers)
    )
)
(defn smallest-number-divisable-for-all-number-up-to? [x]
  x)







