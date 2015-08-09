(ns stone-piles.core-spec
  (:require [speclj.core :refer :all]
            [stone-piles.core :refer :all]))

(describe "Grundy values"
  (it "grundy of 1"
    (should= 0 (stone-piles.core/grundy 1)))
  (it "grundy of 2"
    (should= 0 (stone-piles.core/grundy 2)))
  (it "grundy of 3"
    (should= 1 (stone-piles.core/grundy 3)))
  (it "grundy of 4"
    (should= 0 (stone-piles.core/grundy 4)))
  (it "grundy of 5"
    (should= 2 (stone-piles.core/grundy 5)))
  (it "grundy of 6"
    (should= 3 (stone-piles.core/grundy 6)))
   (it "grundy of 7"
    (should= 4 (stone-piles.core/grundy 7)))
  (it "grundy of 8"
    (should= 0 (stone-piles.core/grundy 8)))
  )

(describe "mex"
  (it "mex of [0 2]"
    (should= 1 (stone-piles.core/mex (set [0 2]))))
  (it "mex of [0 1 33]"
    (should= 2 (stone-piles.core/mex (set [0 1 33]))))
  (it "mex of [2 7 999]"
    (should= 0 (stone-piles.core/mex (set [2 7 999]))))
  (it "mex of [0 1 2 3 4 5 6 7 22]"
    (should= 8 (stone-piles.core/mex (set [0 1 2 3 4 5 6 7 22]))))
  (it "mex of [4 0 2 7 1]"
    (should= 3 (stone-piles.core/mex (set [4 0 2 7 1]))))
  )

(describe "should get nim-sum"

  (it "paritions of p"
    (should= [[7, 1]
              [6, 2]
              [5, 3]
              [5, 2, 1]
              [4, 3, 1]] (stone-piles.core/s-partition 8)))


(describe "should detect if game is over"
  (it "nothing to split if piles are all 1 or 2"
    (should= true (stone-piles.core/game-over? [1, 2])))

  (it "game not over if piles can still be split"
    (should= false (stone-piles.core/game-over? [1, 3]))))

(describe "should play game"
  (it "nim sum"
    (should= 3 (stone-piles.core/find-nim-sum [3 4 5])))
  (it "bob wins playing 4"
  (let [game {:users ["ALICE", "BOB"], :piles [4]}]
     (should= "BOB" (stone-piles.core/play-game game))))
 (it "alice wins playing  1 3 4"
 (let [game {:users ["ALICE", "BOB"], :piles [1, 3, 4]}]
      (should= "ALICE" (stone-piles.core/play-game game))))
 (it "bob wins playing 8"
  (let [game {:users ["ALICE", "BOB"], :piles [8]}]
   (should= "BOB" (stone-piles.core/play-game game)))))
  (it "bob wins last case"
    (let [game {:users ["ALICE", "BOB"], :piles [30 47 20 37]}]
      (should= "BOB" (stone-piles.core/play-game game)))))