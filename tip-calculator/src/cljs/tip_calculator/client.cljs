(ns tip-calculator.client
  (:require [ajax.core :refer [GET POST]]
            [figwheel.client :as fw]
            [dommy.core :as dommy :refer-macros [sel sel1]]))
(fw/watch-and-reload
  :websocket-url   "ws://localhost:3449/figwheel-ws"
  :jsload-callback
  (fn []
    (println "reloaded")))

(defn handler [response]
  (.log js/console "server responded...")
  (-> (sel1 :.fromServer)
      (dommy/set-text!
        (str "Tip:" (get response "tip-amount") " Total:" (get response "total-amount")))))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))

(defn say-hello [e]
  (GET "/tip"
        {:params {:bill (dommy/value (sel1 :#bill)) :tip (dommy/value (sel1 :#tip))}
         :handler handler
         :error-handler error-handler
         :format :json
         :response-format :json}))

(dommy/listen! (sel1 :#btn) :click say-hello)

(.log js/console "Hello SARAH SIDELEAU!")
