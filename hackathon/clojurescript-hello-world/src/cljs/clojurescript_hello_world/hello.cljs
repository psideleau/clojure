(ns clojurescript-hello-world.hello
  )

(defn getCurrentDate []
   (js/Date.))

(.write js/document "<p>Hello World!</p>")
