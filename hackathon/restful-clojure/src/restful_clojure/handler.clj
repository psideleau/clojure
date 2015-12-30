(ns restful-clojure.handler
  (:use compojure.core
        ring.middleware.json)
  (:require [compojure.handler :as handler]
            [ring.util.response :refer [response]]
            [compojure.route :as route]))

(defn- str-to [num]
  ([:result (apply str (interpose ", " (range 1 (inc num))))]))

(defn str-from [num]
  (let [test {:result (apply str (interpose ", " (range 1 (inc num))))}]
    test))

(defn wrap-log-request [handler]
  (fn [req]
    (println req)
    (handler req)))

(defroutes app-routes
  (GET "/count-up/:to" [to] (response (str-to (Integer. to))))
  (GET "/count-down/:from" [from] (response (str-from (Integer. from))))
  (route/not-found (response {:message "Page not found"})))

(def app
  (-> app-routes
    wrap-log-request
    wrap-json-response))

