(ns utopian-tree.core-spec
  (:require [speclj.core :refer :all]
            [utopian-tree.core :refer :all]))

(describe "growth of trees"
  (it "should cycle"
    (should= 1 (utopian-tree.core/grow 0)))
  (it "should cycle1"
    (should= 3 (utopian-tree.core/grow 2)))
  (it "should cycle2"
    (should= 6 (utopian-tree.core/grow 3)))
  (it "should cycle222"
    (should= 7 (utopian-tree.core/grow 4)))
  (it "should cycle4"
    (should= 14 (utopian-tree.core/grow 5))))
