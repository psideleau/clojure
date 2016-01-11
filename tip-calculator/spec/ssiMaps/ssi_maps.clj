(ns ssiMaps.ssi-maps  (:require [speclj.core :refer :all ]
                            ))
(def phone-map (atom {}))
(defn add-to-map []
  (let [name (read-line)
        phone (read-line)]
  (swap! phone-map assoc name phone)))


(defn get-phone [name]
  (if (contains? @phone-map name)
    (str  name "=" (get @phone-map name))
    "NOT FOUND"))

(describe "should add map "
          (it "share add a person"
              (->> (add-to-map)
                   (with-in-str "paul\n203521366")
                    )
              (should= "paul=203521366"  (get-phone "paul"))
              (should= "NOT FOUND"  (get-phone "SFSDF"))))


(run-specs)