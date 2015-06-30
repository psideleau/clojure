(ns clojure.test.example
  (:use clojure.test)
  (:use bowling.game)
  (:import (bowling.game.Game))
)

(defn run [times f]
  (loop [i 0]
    (if (< i times)
      (do
        (f)
        (recur (inc i)))
      nil)))

(deftest user-scores-gutter-game
  (let [game (create-game) i 0]
    (run 20 #(roll game 0))
    (is (= 0 (totalScore game)))))

(deftest user-scores-1-each-frame
  (let [game (create-game)]
    (run 10 #(do
          (roll game 0)
           (roll game 1)))
    (is (= 10 (totalScore game)))))

(deftest user-scores-spare-3-pins-then-all-gutters
  (let [game (create-game)]
    (do
      (roll game 9)
      (roll game 1)
      (roll game 3)
      (run 17 #(roll game 0)))
    (is (= 16 (totalScore game))))
)

(deftest user-gets-a-strike-8-pin-frame-and-gutters
  (let [game (create-game)]
    (do
      (roll game 10)
      (roll game 4)
      (roll game 4)
      (run 8 #(roll game 0)))
    (is (= 26 (totalScore game)))))

(deftest user-gets-perfect-score
  (let [game (create-game)]
    (do
      (run 12 #(do
                 (roll game 10)
                 (println (currentFrames game))
                 )))
    (is (= 300 (totalScore game)))))
(run-all-tests)