(ns euler.factorial)

(def ^:const _100-percent-certainty-that-number-is-a-prime 100)
(defn is-divisible? [divisor, num]
  (= (double (mod num divisor)) 0.0))

(defn is-prime? [num]
  (.isProbablePrime (.toBigInteger (bigint num)) _100-percent-certainty-that-number-is-a-prime))

(def is-prime-memoize?
  (memoize is-prime?))

(defn get-numbers-to-check-if-prime-factor-of [x]
  (range 2 (+ 1 x)))

(defn get-expotential [prime, value]
  (let [expValue (/ (Math/log value) (Math/log prime))]
    (repeat expValue prime)))

(defn are-prime-factors-equal-to? [product prime-factors]
  (= (apply * prime-factors) product))

(defn is-prime-to-large-to-be-product-of [value prime]
  (> prime (long (Math/floor (/ value 2)))))

(defn get-next-prime [prime]
  (.longValue (.nextProbablePrime (.toBigInteger (bigint prime))))
)

(defn add-to-prime-factors-if-prime-is-divisible-to [params]
  (if (is-divisible? (:prime params) (:product params))
    (conj (:prime-factors params) (:prime params))
    (:prime-factors params)))

(defn recursive-prime-factors-of [params]
  (if (or (is-prime-to-large-to-be-product-of (:product params) (:prime params))
          (are-prime-factors-equal-to? (:product params) (:prime-factors params)))
    (:prime-factors params)
    (recur {:prime (get-next-prime (:prime params))
            :product (:product params)
            :prime-factors (add-to-prime-factors-if-prime-is-divisible-to params)})))

(defn prime-factors-of [x]
  {:pre [(not= is-prime? x) (> x 3)]}
  (let [prime-factors (recursive-prime-factors-of {:prime 2 :product x :prime-factors []})]
    (if (= 1 (count prime-factors)) (get-expotential (first prime-factors) x) prime-factors)))

(defn max-primefactor-of [x]
  (last (prime-factors-of x)))
