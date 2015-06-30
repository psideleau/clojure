(ns clojure.test.example
  (:use clojure.test))

(defn is-multiple-of-3 [num]
  (= (mod num 3) 0)
  )

(defn is-multiple-of-5 [num]
  (= (mod num 5) 0)
  )

(defn build-list-up-to-rec [limit, list]
  (if (>= (count list) (- limit 1))
    list
    (build-list-up-to-rec limit (cons (+ 1 (count list)) list))))

(defn build-list-up-to [limit]
  (reverse (build-list-up-to-rec  limit '()))
  )

(defn list-of-primes [limit]
  (filter (fn [x]
            (or (is-multiple-of-3 x) (is-multiple-of-5 x))) (build-list-up-to limit)))

(defn sum-of-prime-numbers [limit]
  (reduce + (list-of-primes limit)))

(deftest test-list-of-primes
  (is (= '(3, 5, 6, 9) (list-of-primes 10))))

(deftest test-sum-of-prime-numbers-3-and-5
  (is (= 0 (sum-of-prime-numbers 3)))
  (is (= 3 (sum-of-prime-numbers 4)))
  (is (= 8 (sum-of-prime-numbers 6))))


(deftest test-is-not-multiple-of-3
  (is (= false (is-multiple-of-3 1))))

(deftest test-is--multiple-of-3
  (is (= true (is-multiple-of-3 3)))
  (is (= true (is-multiple-of-3 6)))
  (is (= true (is-multiple-of-3 9))))

(deftest test-is-multiple-of-5
  (is (= true (is-multiple-of-5 5)))
  (is (= true (is-multiple-of-5 10)))
  (is (= true (is-multiple-of-5 15))))

(deftest test-should-build-list-0
  (is (= '() (build-list-up-to 0))))

(deftest test-should-build-list-1
  (is (= '() (build-list-up-to 1))))

(deftest test-should-build-list-2
  (is (= '(1) (build-list-up-to 2))))

(deftest test-should-build-list-3
  (is (= '(1, 2) (build-list-up-to 3))))

(run-all-tests)