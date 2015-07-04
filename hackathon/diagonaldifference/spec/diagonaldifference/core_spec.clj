(ns diagonaldifference.core-spec
  (:require [speclj.core :refer :all]
            [diagonaldifference.core :refer :all]))

(describe "diagonals"
  (it "should get down diagonal"
    (should=  [11 3] (diagonaldifference.core/get-down-diagonal [[11 1]
                                                        [2 3]])))
  (it "sum of diagnoals for 2 by 2 matrix"
   (should=  11 (diagonaldifference.core/sum-diagonals [[11 1]
                                                         [2 3]])))
  (it "sum diagonals for 3 by x matrix"
    (should= 15 (diagonaldifference.core/sum-diagonals [[11 2 4]
                 [4 5 6]
                 [10 8 -12]]))))

(describe "split string into vector of integeres"
  (it "should split list"
    (should= [11 3] (diagonaldifference.core/split-string "11 3"))))

