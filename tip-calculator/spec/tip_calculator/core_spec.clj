(ns tip-calculator.core-spec
  (:require [speclj.core :refer :all]
            [tip-calculator.core :refer :all ]))

(describe "should calculate value"
  (it "is a 200 dollar bill with 15% tip"
      (let [cost (tip-calculator.core/calculate {:bill 200 :tip 15})]
        (should= "$30.00" (:tip-amount cost))
        (should=  "$230.00" (:total-amount cost))))

  (it "should round up"
      (let [cost (tip-calculator.core/calculate {:bill 11.25 :tip 15})]
        (should= "$1.69" (:tip-amount cost))
        (should= "$12.94" (:total-amount cost))))

  (it "should throw error if bill is < 0"
      (should-throw IllegalArgumentException  (tip-calculator.core/calculate {:bill -1 :tip 15})))

  (it "should throw error if bill is not numeric"
      (should-throw IllegalArgumentException (tip-calculator.core/calculate {:bill "-1" :tip 15})))

  (it "should throw error if tip is < 0"
      (should-throw IllegalArgumentException (tip-calculator.core/calculate {:bill 100 :tip -1})))

  (it "should throw error if tip is not numeric"
      (should-throw IllegalArgumentException  (tip-calculator.core/calculate {:bill 1 :tip "15"}))))

(run-specs)