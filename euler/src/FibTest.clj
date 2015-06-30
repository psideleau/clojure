(ns clojure.test.example
  (:use clojure.test))

; ExampleTest.clj

(defn fib [n]
  (if (= n 0)
    0
    (if (<= n 2)
    1
      (+ (fib (- n 1)) (fib (- n 2))))
    )
  )

(deftest fib-of-zero
  (is (= 0 (fib 0))))

(deftest fib-of-1
  (is (= 1 (fib 1))))

(deftest fib-of-2
  (is (= 1 (fib 2))))

(deftest fib-of-2
  (is (= 2 (fib 3))))

(deftest fib-of-11
  (is (= 89 (fib 11))))

(run-all-tests)