(ns ssiMaps.arrays  (:require [speclj.core :refer :all ]
                            ))

(def word-function
  (partial re-seq #"[a-zA-Z]+")
  )

(defn sum-words [words]
  (count (word-function words)))

(defn print-words [words]
  (doseq [word (word-function words)]
    (println word)
  ))

(describe "sum of words"
          (it "10"
              (should= 10 (sum-words "He is a very very good boy, isn't he?")))
              (it "10"
                  (should= 0 (sum-words "?")
                           )))

(print-words "He is a very very good boy, isn't he?")
(print-words "?")
(print-words "")
(run-specs)