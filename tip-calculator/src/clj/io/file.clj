(ns io.file)

(defn write-to-file []
  (with-open [w (clojure.java.io/writer "paul.txt")]
    (doseq [name names]
      (.write w  (str name "\n")))))

(defn read-file []
  (with-open [rdr (clojure.java.io/reader "/tmp/foo.txt")]
    (reduce conj [] (line-seq rdr))))
