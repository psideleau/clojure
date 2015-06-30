(ns clojure.test.example
  (:use clojure.test)
  (:require (euler [factorial :as f]))
)
(require '[clojure.core.reducers :as r])

(defn is-divisible? [x, num]
  (= (double (mod num x)) 0.0)
  )

(def is-divisible-memoize?
  (memoize is-divisible?)
  )

(defn is-prime? [num]
  (.isProbablePrime (.toBigInteger (bigint num)) 100)
  ;(and (> num 1) (not-any? #(is-divisible? % num) (range 2 num)))
  )

(def is-prime-memoize?
  (memoize is-prime?)
  )

(defn get-numbers-to-check-if-prime-factor [x]
  (range 2 (+ 1 x))
  )

(defn get-expotential [prime, value]
  (let [expValue (/ (Math/log value) (Math/log prime))]
    (repeat expValue prime)
    )

  )

(defn reduce-primenumbers [list currentNumber x]
  (if (and (is-divisible? currentNumber x) (is-prime-memoize? currentNumber))
    (conj list currentNumber)
    list
    )
  )

(defn prime-factors-of [x]
  ( let [prime-factors  (r/fold 100000 r/cat r/append!  (r/filter #(do ;(println %)
    (and (is-divisible? % x) (is-prime? %)))
                                     (get-numbers-to-check-if-prime-factor (long (Math/floor (/ x 2)))))) ]
    (if (= 1 (count prime-factors)) (get-expotential (first prime-factors) x) prime-factors)
    )
  )

(defn are-prime-factors-equal-to? [product prime-factors]
  (= (apply * prime-factors) product)
)

(defn is-not-possible-for-prime-to-be-a-product-of? [value prime]
  (> prime (long (Math/floor (/ value 2))))
)

(defn get-next-prime [prime]
  (.longValue (.nextProbablePrime (.toBigInteger (bigint prime))))
)

(defn add-to-prime-factors-if-prime-is-divisible-to [prime product prime-factors]
       (if (is-divisible? prime product)
         (conj prime-factors prime)
          prime-factors
       )
)

(defn recursive-prime-factors-of [prime product prime-factors]
  (if (or (is-not-possible-for-prime-to-be-a-product-of? product prime)
          (are-prime-factors-equal-to? product prime-factors))
     prime-factors
     (recur (get-next-prime prime) product (add-to-prime-factors-if-prime-is-divisible-to prime product prime-factors))
   )
)

(defn prime-factors-of2 [x]
  (if (or (is-prime? x) (<= x 1))
    (throw (IllegalArgumentException. "x cannot be a prime and must be > 3"))
    (let [prime-factors (recursive-prime-factors-of 2 x [])]
      (if (= 1 (count prime-factors)) (get-expotential (first prime-factors) x) prime-factors)
      )
    )
  )

(defn max-primefactor-of3 [x]
  (last (prime-factors-of2 x))
)


(defn max-prime-factor-of [x]
  ( some #(let [isprime  (and (is-divisible? % x) (is-prime-memoize? %))]
                                  do ;(println  % " prime: " isprime)
                                  (when isprime  %) )
                       (iterate dec (long (Math/floor (/ x 2)))))
  )


(defn max-prime-factor-of2 [x]
  (loop [candidate (long (Math/floor (/ x 2)))]
    (if (or (<= candidate 2) (and (is-divisible? candidate x) (is-prime? candidate)))
      candidate
      (recur (dec candidate))
      )
    )
 )


(deftest test-not-a-prime
  (is (= false (f/is-prime? 10)))
  (is (= false (f/is-prime? 1)))
  )

(deftest test-is-a-prime-4
  (is (= true (f/is-prime? 2)))
  (is (= true (f/is-prime? 3)))
  (is (= true (f/is-prime? 7)))
  (is (= true (f/is-prime? 11)))
  (is (= true (f/is-prime? 31)))
  (is (= true (f/is-prime? 37)))
  (is (= true (f/is-prime? 71)))
  (is (= true (f/is-prime-memoize? 7919 )))
  )

(deftest should-throw-exception-if-trying-to-get-prime-factors-of-a-prime
  (is (thrown? AssertionError (f/prime-factors-of 2)))
  (is (thrown? AssertionError (f/prime-factors-of 3)))
  (is (thrown? AssertionError (f/prime-factors-of 1)))
  )

(deftest test-get-prime-factors-of-number
  (is (= '(2, 3) (f/prime-factors-of 6)))
  (is (= '(2, 3) (f/prime-factors-of 12)))
  (is (= '(2, 5) (f/prime-factors-of 10)))
  (is (= '(3, 3) (f/prime-factors-of 9)))
  (is (= '(3, 3, 3) (f/prime-factors-of 27)))
  (is (= '(7, 7) (f/prime-factors-of 49)))
  (is (= '(2, 2, 2, 2, 2) (f/prime-factors-of 32)))
  (is (= '(5, 7, 13, 29) (f/prime-factors-of 13195)))
  )

(deftest test-max-prime-factor
  (is (= 3 (f/max-primefactor-of 6)))
  (is (= 3 (f/max-primefactor-of 12)))
  (is (= 5 (f/max-primefactor-of 10)))
  (is (= 3 (f/max-primefactor-of 9)))
  (is (= 3 (f/max-primefactor-of 27)))
  (is (= 7 (f/max-primefactor-of 49)))
  (is (= 2 (f/max-primefactor-of 32)))
  (is (= 29 (f/max-primefactor-of 13195)))
  (is (= 6857 (f/max-primefactor-of 600851475143)))
  )


(run-all-tests)