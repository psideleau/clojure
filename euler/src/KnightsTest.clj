(ns clojure.test.example
  (:use clojure.test)
  (:use clojure.set)
  (:use bowling.knights)
  (:import bowling.knights.Piece)
  (:import bowling.knights.PieceWithMoves)
)
;
;(deftest should-create-board
;  (let [board (create-board) ]
;    (is (= 8 (count board)))
;    (is (= 64 (apply + (map #(count %) board))))))

(deftest get-positions-from-middle
  (is (= [(Piece. 1 3) (Piece. 1 5) (Piece. 2 6)(Piece. 4 6)(Piece. 5 5)(Piece. 5 3)(Piece. 2 2)(Piece. 4 2)] (get-positions-from (Piece. 3, 4) [])))
)

(deftest get-positions-from-top-row
  (is (= [(Piece. 1 2)(Piece. 2 1)] (get-positions-from (Piece. 0, 0) (vec nil))))
  )

(deftest get-positions-from-top-right
  (is (= [(Piece. 2 6)(Piece. 1 5)] (get-positions-from (Piece. 0, 7) (vec nil))))
  )

(deftest get-positions-from-bottom-row
  (is (= [(Piece. 5 1)(Piece. 6 2)] (get-positions-from (Piece. 7, 0) (vec nil)))))

(deftest get-positions-should-exclude-already-moved-poistions
(is (= [(Piece. 2 1)] (get-positions-from (Piece. 0, 0) [(Piece. 1 2)]))))

(deftest get-positions-from-top-row
  (is (= [(Piece. 1 2)(Piece. 2 1)] (get-positions-from (Piece. 0, 0) (vec nil))))
  )


(deftest get-next-position-to-move-to
  (is (= (Piece. 6 0) (get-next-move-from (Piece. 4 1) (vec nil))))
  )

(deftest test-move
  (let [pieces (move (Piece. 0 0))]
    (is (= 64 (count pieces)))
    (is (= 64 (count (set pieces))))))

(run-all-tests)