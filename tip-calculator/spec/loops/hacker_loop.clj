(ns loops.hacker-loop
  (:require [speclj.core :refer :all ]))
(require '[clojure.string :as str])

(defn produce-sum [a b n]
  (->> (range n)
       (map #(* (int ( Math/pow 2 %)) b))
       (reduce + a)
      ))

(defn build-list-of-sums [a b n]
  (->> (range n)
       (map #(produce-sum a b (inc %)))))

(defn run-test-case [[a b n]]
  (build-list-of-sums a b n))

(defn program []
  (let [test-cases (Integer/parseInt (read-line))]
    (dotimes [x test-cases]
      (println (run-test-case (map #(Integer/parseInt %) (str/split (read-line) #" ")))))))

(describe "should test "
  (it "compute sum"
    (should= 14 (produce-sum 5 3 2 )))
  (it "example one"
   (should= '(8 14 26 50 98) (build-list-of-sums 5 3 5 )))
  (it "example two"
    (should= '(2 6 14 30 62 126 254 510 1022 2046) (build-list-of-sums 0 2 10))))

(run-specs)


