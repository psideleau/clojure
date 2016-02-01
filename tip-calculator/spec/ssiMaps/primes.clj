(ns ssiMaps.primes  (:require [speclj.core :refer :all ]
                            ))

(defn is-prime? [x]
  (cond
    (= 1 x) false
    (= 2 x)) true
    (even? x)  false
    :else
    (let [upto (int (Math/sqrt x))]
      (->> (range 2 (inc upto))
           (every? #(>= (rem x %) 1))))))


(describe "primes"
  (it "should be prime"

    (should= true (is-prime? 2))
    (should= true (is-prime? 5))
    (should= true (is-prime? 7))
    (should= true (is-prime? 11))
    (should= true (is-prime? 13))
    (should= true (is-prime? 503)))

    (it "should not be prime"
        (should= true (is-prime? 1))
      (should= false (is-prime? 4))
      (should= false (is-prime? 1000))
      (should= false (is-prime? 8))))

(run-specs)