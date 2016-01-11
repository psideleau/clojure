(ns fibonacci.fib  (:require [speclj.core :refer :all ]
                            ))
(defn fib [n]
  (cond
    (= 0 n) 0
    (= 1 n) 1
    :else (+ (fib (- n 1)) (fib (- n 2)))))

(defn fib-seq [n]
  (map fib (range 1 (inc n))))

(describe "Calculate Fibonacci numbers"
          (it "should get a fibonacci number"
              (should= 0 (fib 0))
              (should= 1 (fib 1) )
              (should= 1 (fib 2))
              (should= 2 (fib 3))
              (should= 5 (fib 5))
              (should= 233 (fib 13))))


(describe "Calculate Fibonacci sequence"
          (it "should get fibonacci numbers"
              (should= '(1 1 2) (fib-seq 3))
              (should= '(1 1 2 3 5 8) (fib-seq 6))
              (should= '(1 1 2 3 5 8 13 21) (fib-seq 8))))


(run-specs)