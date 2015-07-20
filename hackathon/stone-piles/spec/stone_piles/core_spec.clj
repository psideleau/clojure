(ns stone-piles.core-spec
  (:require [speclj.core :refer :all]
            [stone-piles.core :refer :all]))

(describe "should split a pile of 4 stones into 3 and 1"
  (it "should split a pile of 4 stones into 3 and 1"
    (should= [1, 3] (stone-piles.core/split-stones [4])))

  (it "should split a pile of 6 stones into 4 and 2"
    (should= [1, 2, 2, 4] (stone-piles.core/split-stones [1, 2, 6])))

  (it "should split a pile of 5 stones into 3 and 2"
    (should= [1, 2, 2, 3] (stone-piles.core/split-stones [1, 2, 5])))

  (it "should split a pile of 5 stones into 3 and 2"
    (should= [1, 2, 3, 4] (stone-piles.core/split-stones [1, 2, 1, 2, 4])))

  (it "should split a pile of 3 stones into 2 and 1"
    (should= [1, 2, 2, 1] (stone-piles.core/split-stones [1, 2, 3])))

(it "nothing to split if piles are all 1"
  (should= [1, 1] (stone-piles.core/split-stones [1, 1])))

(it "nothing to split if piles are all 1"
    (should= [1, 2] (stone-piles.core/split-stones [1, 2])))
  )

(describe "should detect if game is over"
  (it "nothing to split if piles are all 1 or 2"
    (should= true (stone-piles.core/game-over? [1, 2])))

  (it "game not over if piles can still be split"
    (should= false (stone-piles.core/game-over? [1, 3]))))

(describe "should play game"
  (it "bob wins"
    (let [game {:users ["ALICE", "BOB"], :piles [4]}]
      (should= "BOB" (stone-piles.core/play-game game))))
  (it "alice wins"
    (let [game {:users ["ALICE", "BOB"], :piles [1, 3, 4]}]
      (should= "ALICE" (stone-piles.core/play-game game)))))