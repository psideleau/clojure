(ns ssiMaps.binary  (:require [speclj.core :refer :all ]
                            ))
(defn binary [x]
    (loop [x x remainders []]
      (if (= 0 x)
        (clojure.string/join remainders)
        (let [quote-x (quot x 2)
              remainder (rem x 2)]
          (recur quote-x (cons remainder remainders))))))


(describe "binary"
          (it "binary"
              (should= "100" (binary 4))
              ))

(run-specs)