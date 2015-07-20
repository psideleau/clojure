(ns angry-professor.core-spec
  (:require [speclj.core :refer :all]
            [angry-professor.core :refer :all]))

(describe "should cancel class"
  (it "cancel class when 3 students arrive late"
    (let [test {:enrollment 4, :min-size 2, :arrival-times [2 3 4 -1]}]
      (should= "YES" (angry-professor.core/should-cancel-class test))))

  (it "class can continue when 2 students arrive on time"
    (let [test {:enrollment 4, :min-size 2, :arrival-times [2 0 4 -1]}]
      (should= "NO" (angry-professor.core/should-cancel-class test)))))
