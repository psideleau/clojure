(ns pizza.pizza)

(defn share [{people :people pizzas :pizzas slices-per-pizza :slices-per-pizza}]
  (let [total-slices (* pizzas slices-per-pizza)
        l-share (quot total-slices people)
        remaining (- total-slices (* people l-share))]
        {:shares l-share  :remaining remaining}))

(defn pizzas-needed [{people :people  slices-per-person :slices-per-person}]
  (let [total-slices-needed (* people slices-per-person)
        slices-in-a-pizza 8]
  (int (Math/ceil (/  total-slices-needed slices-in-a-pizza)))))