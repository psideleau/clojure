(ns euler.palindrome
  (require [clojure.string :as s]))

(defn is-palindrome? [x]
  (= (s/reverse (str x)) (str x)))

(defn get-max-value-for-number-of-digits [digits]
  (- (int (Math/pow 10 digits)) 1))

(defn get-numbers-to-check-for-palindrome [digits]
  (take (- (+ 1 (get-max-value-for-number-of-digits digits)) (int (Math/pow 10 (- digits 1))))
    (iterate dec (get-max-value-for-number-of-digits digits) )))

(defn get-all-palindromes [numbers-to-check]
  (remove #(nil? %)
           (map (fn[x] (some (fn[y]
                        (when (is-palindrome? (* x y))  (* x y))) numbers-to-check))  numbers-to-check)))

(defn largest-palindrome-for-product-of-numbers-of-length [digits]
  (apply max (get-all-palindromes (get-numbers-to-check-for-palindrome digits))))

(defn start [x digits]
  (if (every? (fn[d] (= 0 (rem x d))) digits)
    x
    (recur (inc x) digits)))






