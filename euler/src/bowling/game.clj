(ns bowling.game)

(defprotocol BOWL
  (roll [this pins])
  (totalScore [this])
  (currentFrames [this]))

(defprotocol FRAME
  (is-complete? [this])
  (is-spare? [this])
  (is-strike? [this])
  (needs-pins? [this])
  (score [this]))



(defrecord Frame [scores]
  FRAME
  (is-strike? [this] (= 10 (first scores)))
  (is-complete? [this] (or (is-strike? this) (and (>= (count scores ) 2))))
  (is-spare? [this] (and (not (is-strike? this)) (>= (apply + scores) 10)))
  (needs-pins? [this] (and (or (is-spare? this) (is-strike? this)) (< (count scores ) 3)))
  (score [this]
    (apply + scores)))

(declare get-last-frame)

(defn add-pins-to-frame[pins frame atom-frames]
  (do
    (reset! atom-frames (pop @atom-frames))
    (swap! atom-frames conj (assoc-in frame [:scores] (conj (:scores frame) pins )))))

(defn add-new-frame-to-game [pins atom-frames]
  (swap! atom-frames conj (Frame. (vector pins))))

(defrecord Game [frames]
  BOWL
  (roll [this pins]
    (if (empty? @frames)
      (add-new-frame-to-game pins frames)
      (let [last-frame (get-last-frame this)]
          (if (and (is-complete? last-frame) (< (count @frames) 10))
              (add-new-frame-to-game pins frames)
              (add-pins-to-frame pins last-frame frames))
          (reset! frames  (conj (mapv (fn[frame]
                            (if (needs-pins? frame)
                                (assoc-in frame [:scores] (conj  (:scores frame) pins) )
                                frame)) (butlast @frames)) (last @frames))))))

  (currentFrames [this]
    @frames)
  (totalScore [this]
      (apply + (map #(score %) @frames))))

(defn get-last-frame [game]
  (nth (currentFrames game) (dec (count (currentFrames game)))))

(defn create-game []
  (Game. (atom [])))



