(ns factorial.core-spec
  (:require [speclj.core :refer :all]
            [factorial.core :refer :all]))

(describe "a factorial teat"
  (it "factorial of 3 is 6"
    (should= 6M (factorial.core/factorial 3)))
  (it "factorial of 10 is 3,628,800"
    (should= 3628800M (factorial.core/factorial 10)))
  (it "factorial of 25 is 15511210043330985984000000"
    (should= 15511210043330985984000000M (factorial.core/factorial 25)))
  (it "factorial of 100 is 15511210043330985984000000"
    (should= 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000M (factorial.core/factorial 100))))
