(ns tip-calculator.core-spec
  (:require [speclj.core :refer :all]
            [tip-calculator.core :refer :all ]))

(defn calc-bill [{bill :bill tax-pct :tax-pct tip-pct :tip-pct}]
  (let [tax (/ (* (BigDecimal. bill) (BigDecimal.  tax-pct)) 100)
        tip (/ (* (BigDecimal.  bill) (BigDecimal.  tip-pct)) 100)]
    (int (Math/round (.doubleValue (+ (BigDecimal. bill) tax tip))))))

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

(describe "should calculate tip"
          (it "it should rown down"
            (should= 15  (calc-bill {:bill "12" :tax-pct "8" :tip-pct "20"})))
          (it "it should rown down"
              (should= 16  (calc-bill {:bill "12" :tax-pct "11" :tip-pct "20"}))))


(defn calc-paint [length width]
  (int (Math/ceil (/ (* length width) 350.0))))

(describe "should calculate paint"
          (it "it should calculate paint"
              (should= 2  (calc-paint 60 6))))

(defn is-weird? [x]
  (cond
    (odd? x) "Weird"
    (contains? [2 4] x) "Not Weird"
    (and (>= x 6) (<= x 20)) "Weird"
    :else "Not Weird"))

(describe "should check for weird"
          (it "odds numbers are weird"
             (should= "Weird"  (is-weird? 3))
             (should= "Weird"  (is-weird? 1))
             (should= "Weird"  (is-weird? 19)))
          (it "evenum numbers bewteen 2 and 5 is not wired"
              (should= "Not Weird"  (is-weird? 2))
              (should= "Not Weird"  (is-weird? 4)))
          (it "evenum numbers bewteen 6 and 20 is weird"
              (should= "Weird"  (is-weird? 6))
              (should= "Weird"  (is-weird? 12))
               (should= "Weird"  (is-weird? 20)))
          (it "evenum numbers > 20 not werid"
              (should= "Not Weird"  (is-weird? 22))
              (should= "Not Weird"  (is-weird? 28))
              (should= "Not Weird"  (is-weird? 100))))

(run-specs)