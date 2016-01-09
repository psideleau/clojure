(ns hello-world.hello-world)


(defn hello-world []
  (do
    (print "What is your name?")
    (flush)
    (let [name (read-line) msg (str "Hello, " name ", nice to meet you!")]
      (println "")
      (println msg))))


(defn time-example []

  (
    let [
         time (read-line)
         format (java.text.SimpleDateFormat. "hh:mm:ssa")
         format-military (java.text.SimpleDateFormat. "HH:mm:ss")
         date  (.parse format time)
         ]

    (println (.format format-military date))

    ))


(defn count-characters []
  (do
    (print "What is the input string?")
    (flush)
    (let [val (read-line) count (count val)]
      (println (format "%s has %d characters" val count)))))


(count-characters)