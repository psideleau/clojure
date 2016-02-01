(ns ssiMaps.late-fees
  (:require [speclj.core :refer :all])
  (:import (java.time LocalDate)
           (java.text SimpleDateFormat)
           (java.time.temporal ChronoUnit)
           (java.util Calendar)
           (java.time.format DateTimeFormatter)
        ))

(defn late-fee [{:keys [return-date expected-return-date]}]
  (let [date-formatter (SimpleDateFormat. "d M yyyy")
        return-date-obj  (.parse date-formatter return-date)
        expected-return-date-obj  (.parse date-formatter expected-return-date)]
    (cond
      (.before return-date-obj expected-return-date-obj) 0
      (.equals return-date-obj expected-return-date-obj) 0
      (and (= (.getYear return-date-obj) (.getYear expected-return-date-obj))
           (= (.getMonth return-date-obj) (.getMonth expected-return-date-obj)))
           (* 15 (/ (- (.getTime return-date-obj) (.getTime expected-return-date-obj)) 86400000))
      (= (.getYear return-date-obj) (.getYear expected-return-date-obj))
           (* 500 (- (.getMonth return-date-obj) (.getMonth expected-return-date-obj) ))
      :else 10000)))

(defn late-fee-java8 [{:keys [return-date expected-return-date]}]
  (let [date-formatter (DateTimeFormatter/ofPattern "d M yyyy")
        return-date-obj (LocalDate/parse return-date date-formatter)
        expected-return-date-obj  (LocalDate/parse expected-return-date date-formatter)]
    (cond
      (.isBefore return-date-obj expected-return-date-obj) 0
      (.isEqual return-date-obj expected-return-date-obj) 0
      (and (= (.getYear return-date-obj) (.getYear expected-return-date-obj))
           (= (.getMonth return-date-obj) (.getMonth expected-return-date-obj)))
      (* 15 (.until expected-return-date-obj return-date-obj, ChronoUnit/DAYS) )
      (= (.getYear return-date-obj) (.getYear expected-return-date-obj))
      (* 500 (.until expected-return-date-obj return-date-obj, ChronoUnit/MONTHS))
      :else 10000)))

(describe "should describe late fee"
  (it "no late fee"
    (should= 0  (late-fee {:return-date "9 6 2015" :expected-return-date "9 6 2015"}))
    (should= 0  (late-fee {:return-date "9 5 2015" :expected-return-date "9 6 2015"}))
    (should= 0  (late-fee {:return-date "10 11 2015" :expected-return-date "11 12 2015"})))

  (it "late for month"
      (should= 15  (late-fee {:return-date "13 11 2015" :expected-return-date "12 11 2015"}))
      (should= 45  (late-fee {:return-date "9 6 2015"   :expected-return-date "6 6 2015"})))

  (it "returned in same year"
      (should= 500  (late-fee {:return-date "13 12 2015" :expected-return-date "12 11 2015"}))
      (should= 2500  (late-fee {:return-date "13 11 2015" :expected-return-date "12 6 2015"}))
      )

  (it "returned in more than a year"
      (should= 10000  (late-fee {:return-date "13 12 2016" :expected-return-date "12 11 2015"}))))

;9 6 2015
;6 6 2015
;ssi_maps.cljIf the book is returned on or before the expected return date, no fine will be charged, in other words fine is 0.
;If the book is returned in the same month as the expected return date, Fine = 15 Hackos × Number of late days
;If the book is not returned in the same month but in the same year as the expected return date, Fine = 500 Hackos × Number of late months
;If the book is not returned in the same year, the fine is fixed at 10000 Hackos.
(run-specs)