(ns factorial.core)

(defn run-factorial [n, value]
  (if (= n 1)
       value
      (recur (dec n) (* (bigdec value) (dec n)))))

(defn factorial [n]
  (run-factorial n n))

l
  (let [n (Integer/parseInt (read-line)) ]
    (println (String/valueOf(factorial n)))))
