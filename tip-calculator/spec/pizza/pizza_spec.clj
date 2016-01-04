(ns pizza.pizza-spec
  (:require [speclj.core :refer :all ]
            [pizza.pizza :refer :all :as p]))

(describe "should calculate slices"
  (it "share of 8 people 8 slices per pizza and 2 pizzas is 16"
      (let [result (p/share {:people 8 :pizzas 2 :slices-per-pizza 8})]
        (should= 2 (:shares result))
        (should= 0 (:remaining result))))

  (it "8 people want 2 slices to eat then need 2 pizzas"
    (let [result (p/pizzas-needed {:people 8 :slices-per-person 2})]
      (should= 2 result)))

  (it "9 people want 2 slices to eat then need 3 pizzas"
    (let [result (p/pizzas-needed {:people 9 :slices-per-person 2})]
      (should= 3 result))))

(run-specs)