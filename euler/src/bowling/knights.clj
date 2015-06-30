(ns bowling.knights
  (:use clojure.set)
  (:import java.util.LinkedHashSet))

(defrecord Piece [row col]

)

(defrecord PieceWithMoves [piece, moves])

(defn create-board []
  (vec [[0 1 2 3 4][0 1 2 3 4][0 1 2 3 4][0 1 2 3 4]]))

(defn move-top-left [piece]
  (Piece. (- (:row piece) 2) (- (:col piece) 1))
)

(defn move-top-right [piece]
  (Piece. (- (:row piece) 2) (+ (:col piece) 1))
  )

(defn move-right-top [piece]
  (Piece. (- (:row piece) 1) (+ (:col piece) 2) )
  )

(defn move-right-bottom [piece]
  (Piece. (+ (:row piece) 1) (+ (:col piece) 2) ))

(defn move-bottom-right [piece]
  (Piece. (+ (:row piece) 2) (+ (:col piece) 1) ))

(defn move-bottom-left [piece]
  (Piece. (+ (:row piece) 2) (- (:col piece) 1) ))

(defn move-left-top [piece]
  (Piece. (- (:row piece) 1) (- (:col piece) 2) ))

(defn move-left-bottom [piece]
  (Piece. (+ (:row piece) 1) (- (:col piece) 2) ))

(defn create-knight-moves-from [piece]
  (vec [(move-top-left piece)
        (move-top-right piece)
        (move-right-top piece)
        (move-right-bottom piece)
        (move-bottom-right piece)
        (move-bottom-left piece)
        (move-left-top piece)
        (move-left-bottom piece)]))

(defn exclude-pieces-that-are-off-board [pieces]
  (filter #(and (not (neg? (:row %))) (not (neg? (:col %))) (<= (:row %) 7) (<= (:col %) 7)) pieces))

(defn exclude-pieces-that-are-already-visited [pieces, visited-pieces]
  (filter (fn [piece] (= nil (some (fn [x] (= x piece)) visited-pieces))) pieces))

(defn get-positions-from [piece visited-pieces]
  (exclude-pieces-that-are-already-visited (exclude-pieces-that-are-off-board (create-knight-moves-from piece)) visited-pieces))

(defn get-number-of-moves-from [piece visited-pieces]
  (PieceWithMoves. piece (count (get-positions-from piece visited-pieces))))

(defn get-number-of-moves-from-every-piece [pieces visited-pieces]
  (sort-by :moves (map #(get-number-of-moves-from % visited-pieces) pieces)))

(defn get-next-move-from [piece, visited-pieces]
  (:piece (first (get-number-of-moves-from-every-piece (get-positions-from piece visited-pieces) visited-pieces))))


(defn move-accum [position, visited-pieces, times]
  (if (> times 64) visited-pieces
    (recur (get-next-move-from position, visited-pieces) (conj visited-pieces position) (inc times)))
)

(defn move [startingPosition]
  (move-accum startingPosition [] 1)
)




