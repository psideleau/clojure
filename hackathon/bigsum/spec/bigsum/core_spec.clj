(ns bigsum.core-spec
  (:require [speclj.core :refer :all]
            [bigsum.core :refer :all]))

(describe "a test"
  (it "FIXME, I fail."
    (should= 5000000015 (bigsum.core/sum [1000000001 1000000002 1000000003 1000000004 1000000005]))))
