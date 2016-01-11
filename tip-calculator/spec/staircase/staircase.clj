(ns staircase.staircase (:require [speclj.core :refer :all ]
                                  [clojure.string :as str]))


(defn draw-stair [step max-step]
   (->>  (repeat step "#")
         (str/join)
         (format (str "%" max-step "s"))))

(defn create-staircase [n]
  (dotimes [x n]
    (-> (inc x)
        (draw-stair n)
        (println))))


(describe "should print stair case"
          (it "staircase of 3"
              (should= "  #\n ##\n###\n" (create-staircase 6))))


(run-specs)