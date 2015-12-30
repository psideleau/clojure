(ns tip-calculator.service
  (:use compojure.core
        clojure.walk
        tip-calculator.core
        ring.middleware.json
        ring.middleware.params)
  (:import java.math.BigDecimal)
  (:require [compojure.handler :as handler]
            [ring.util.response :refer [response]]
            [compojure.route :as route]
            [ring.util.response :as response]))

(defn- str-to [num]
  ([:result (apply str (interpose ", " (range 1 (inc num))))]))

(defn str-from [num]
  (let [test {:result (apply str (interpose ", " (range 1 (inc num))))}]
    test))

(defn wrap-log-request [handler]
  (fn [req]
    (handler req)))

(defn get-tip [params]
  (response (tip-calculator.core/calculate {:tip (BigDecimal. (get params "tip")) :bill (BigDecimal. (get params "bill"))})))

(defroutes app-routes
           (GET "/" [] (response/resource-response "index.html" {:root "public"}))
           (route/resources "/")
           (GET "/tip" request (get-tip (:query-params request)))
           (GET "/count-down/:from" [from] (response (str-from (Integer. from))))
           (route/not-found (response {:message "Page not found"})))

(def app
  (-> app-routes
      wrap-log-request
      wrap-params
      wrap-json-response))