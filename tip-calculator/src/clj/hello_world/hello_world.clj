(ns hello-world.hello-world)


(defn hello-world []
  (do
    (print "What is your name?")
    (flush)
    (let [name (read-line) msg (str "Hello, " name ", nice to meet you!")]
      (println "")
      (println msg))))


(defn count-characters []
  (do
    (print "What is the input string?")
    (flush)
    (let [val (read-line) count (count val)]
      (println (format "%s has %d characters" val count)))))


(count-characters)