(ns angry-professor.core
  (:require [clojure.string :as str]))

(defn should-cancel-class [classData]
  (cond (>=
          (count (filter #(not (pos? %)) (:arrival-times classData)))
          (:min-size classData)) "NO"
         :else "YES"))

(defn get-test-cases [test-cases number-of-tests, current-count]
  (if (= current-count number-of-tests)
    test-cases
    (recur (conj test-cases (Integer/parseInt (read-line))) number-of-tests (inc current-count))))

(defn split-string [vector-string]
  (mapv #(Integer/parseInt %) (str/split vector-string #" ")))

(defn build-test-case []
  (let [data
        (split-string (read-line))
        class-info {:enrollment (first data) :min-size (second data) :arrival-times (split-string (read-line))}]
    class-info
  ))

(defn get-test-cases [test-cases number-of-tests, current-count]
  (if (= current-count number-of-tests)
    test-cases
    (recur (conj test-cases (build-test-case)) number-of-tests (inc current-count))))


(defn -main []
  (let [mycount (Integer/parseInt (read-line)) test-cases (get-test-cases [] mycount 0) ]
    (doseq [class-info test-cases] (println (should-cancel-class class-info)))))


(defn -main1 []
  (let [mycount (Integer/parseInt (read-line)) ]
    (dotimes [x mycount]
      (let [data
            (split-string (read-line))
            class-info {:enrollment (first data) :min-size (second data) :arrival-times (split-string (read-line))}]
        (println (should-cancel-class class-info))))))