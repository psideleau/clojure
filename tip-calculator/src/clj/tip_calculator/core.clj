(ns tip-calculator.core)

(defn to-percent [x]
  "returns x as a percentage"
  (/ x 100))

(defn round-currency [amount]
  (with-precision 2 (bigdec amount)))

(defn to-string [amount]
  (format "$%.2f" (float amount)))


(defn is-natural-number? [x]
  (and (number? x) (>= x 0)))

(defn calculate [{bill :bill tip :tip}]
  "returns map of {:tip-amount :total-amount}"
  (do
    (println "AMOUNTS" bill tip)
    (when (not (and (is-natural-number? bill) (is-natural-number? tip)) ) (throw (IllegalArgumentException. "Error")))
    (let [tip-amount (round-currency (* bill (to-percent tip)))]
      (hash-map :tip-amount (to-string tip-amount)
                :total-amount (to-string (+ bill tip-amount))))))

(defn run-app []
  (while true
      (try
        (println "Enter bill amount")
      (let [bill (Integer/parseInt (read-line))]
        (println "Enter tip")
        (let [tip (Integer/parseInt (read-line))]
          (println "Tip" (calculate {:bill bill :tip tip})))
        (System/exit 0))
      (catch NumberFormatException  nfe ))))


(defn -main []
  (run-app))


