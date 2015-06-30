(defn hello-world [username]
  (println (format "Hello, %s" username)))

(hello-world "world")

(defn is-odd [ab]
  (odd? ab)
  )

(println (is-odd 5))