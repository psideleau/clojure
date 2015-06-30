(ns clojure.test.example
  (:use clojure.test)
  (:require (euler [palindrome :as p]))
)

(deftest is-palindrome
  (is (= true (p/is-palindrome? "tot")))
  (is (= true (p/is-palindrome? "9009")))
  )

(deftest find-largest-palindrome-made-from-the-product-of-ndigit-numbers
  (is (= 9009 (p/largest-palindrome-for-product-of-numbers-of-length 2)))
  (is (= 580085 (p/largest-palindrome-for-product-of-numbers-of-length 3)))
)
(run-all-tests)