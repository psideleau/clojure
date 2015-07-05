(ns utopian-tree.core)

(defn get-cycle-growth [cycle-count value]
  (if (= 0 (mod cycle-count 2)) (* value 2) (inc value)))

(defn grow-cycles [number-of-cycles count value]
  (if (or (= number-of-cycles 0)  (= number-of-cycles count)) value
    (recur number-of-cycles (inc count) (get-cycle-growth count value))))

(defn grow [number-of-cycles]
  (grow-cycles number-of-cycles 0 1))

(defn get-test-cases [test-cases number-of-tests, current-count]
  (if (= current-count number-of-tests)
    test-cases
    (recur (conj test-cases (Integer/parseInt (read-line))) number-of-tests (inc current-count))))

(defn -main []
  (let [mycount (Integer/parseInt (read-line)) test-cases (get-test-cases [] mycount 0) ]
    (doseq [cycle test-cases] (println (grow cycle)))))