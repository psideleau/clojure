(ns leapyear.core)

(defn is-leap-year? [arg]
  (cond
     (= 0 (mod arg 400)) true
     (= 0 (mod arg 100)) false
     (= 0 (mod arg 4)) true
     :else false
    ))
