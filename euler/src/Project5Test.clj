(ns clojure.test.example
  (:use clojure.test)
  (:require (euler [smallestmultiple :as p]))
)

(deftest is-small-number
  (is (= 2520  (p/smallest-number-divisable-for-all-number-up-to? 10)))
  )



(run-all-tests)