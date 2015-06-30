(ns leapyear.core-spec
  (:require [speclj.core :refer :all]
            [leapyear.core :refer :all]))

(describe "leap years"
  (it "leap years are divisible by 400"
    (should=  true (leapyear.core/is-leap-year? 400)))

  (it "leap years are divisible by 4"
    (should=  true (leapyear.core/is-leap-year? 88))
    (should=  true (leapyear.core/is-leap-year? 2016))
    (should=  true (leapyear.core/is-leap-year? 2020)))

  (it "leap years must be divisible by 4"
    (should=  false (leapyear.core/is-leap-year? 2013))
    (should=  false (leapyear.core/is-leap-year? 2015)))

  (it "leap years are not divisible by 100"
    (should=  false (leapyear.core/is-leap-year? 100))
    (should=  false (leapyear.core/is-leap-year? 2100))))
