(ns fraud-prevention.core-spec
  (:require [speclj.core :refer :all]
            [fraud-prevention.core :refer :all]))

(describe "should detect fraud when same deal id and address but different credit card used "
  (before (def orders  [{:order-id 1,
                         :deal-id 1,
                         :email "bugs@bunny.com",
                         :street "123 Sesame St.",
                         :city "New York",
                         :state "NY",
                         :zip "10011",
                         :credit-card "12345689010"
                         },
                        {:order-id 2,
                         :deal-id 1,
                         :email "elmer@fudd.com",
                         :street "123 Sesame St.",
                         :city "New York",
                         :state "NY",
                         :zip "10011",
                         :credit-card "10987654321"
                         },
                        {:order-id 3,
                         :deal-id 2,
                         :email "bugs@bunny.com",
                         :street "123 Sesame St.",
                         :city "New York",
                         :state "NY",
                         :zip "10011",
                         :credit-card "12345689010"
                         }]))
  (it "first and second orders are fraudlent b/c address is same with same deal-id but diffferent credit card numbers"
    (should= [1 2] (fraud-prevention.core/find-fradulent-orders orders))))

(describe "should detect fraud when same deal id and email address but different credit card used "
  (before (def orders  [{:order-id 1,
                         :deal-id 1,
                         :email "bugs1@bunny.com",
                         :credit-card "12345689010"
                         },
                        {:order-id 2,
                         :deal-id 1,
                         :email "bugs1@bunny.com",
                         :credit-card "10987654321"
                         },
                        {:order-id 3,
                         :deal-id 1,
                         :email "bugs1@Bunny.com",
                         :credit-card "11111"
                         },
                        {:order-id 4,
                         :deal-id 1,
                         :email "bugs.1@bunny.com",
                         :credit-card "7777"
                         },
                        {:order-id 5,
                         :deal-id 1,
                         :email "bugs1+10@bunny.com",
                         :credit-card "777789"
                         }]))
  (it "first and second orders are fraudlent b/c address is same with same deal-id but diffferent credit card numbers"
    (should= [1 2 3 4 5] (fraud-prevention.core/find-fradulent-orders orders))))

(describe "should makes all values tolowercase "
  (before (def orders  [{:order-id 1,
                         :deal-id 1,
                         :email "Bugs@Bunny.com",
                         :street "123 Sesame St.",
                         :city "New York",
                         :state "NY",
                         :zip "10011",
                         :credit-card "ABC45689010"
                         },
                        {:order-id 2,
                         :deal-id 1,
                         :email "ELMER@fudd.com",
                         :street "123 Sesame St.",
                         :city "New York",
                         :state "NY",
                         :zip "10011",
                         :credit-card "DS-10987654321"
                         }])
          (def expected-orders [{:order-id 1,
                                 :deal-id 1,
                                 :email "bugs@bunny.com",
                                 :street "123 sesame st.",
                                 :city "new york",
                                 :state "ny",
                                 :zip "10011",
                                 :credit-card "abc45689010"
                                 },
                                {:order-id 2,
                                 :deal-id 1,
                                 :email "elmer@fudd.com",
                                 :street "123 sesame st.",
                                 :city "new york",
                                 :state "ny",
                                 :zip "10011",
                                 :credit-card "ds-10987654321"
                                 }]))
  (it "first and second orders are fraudlent b/c address is same with same deal-id but diffferent credit card numbers"
    (should= expected-orders (fraud-prevention.core/to-lowercase orders))))

(describe "should remove . from emails "
(before (def orders  [{:email "Bugs.2@Bunny.com"},
                      {:email "ELM1.ER@fudd.com"}])
  (def expected-orders [{:email "Bugs2@Bunny.com"},
                        {:email "ELM1ER@fudd.com"}]))
(it "periods are removed"
  (should= expected-orders (fraud-prevention.core/format-emails orders))))

(describe "should exclude + emails "
(before (def orders  [{:email "bugs+10@bunny.com"},
                      {:email "b+ugs10@bunny.com"},
                      {:email "+bugs10@bunny.com"}])
  (def expected-orders [{:email "bugs@bunny.com"},
                        {:email "b@bunny.com"},
                        {:email "@bunny.com"}]))
(it "+ are excluded"
  (should= expected-orders (fraud-prevention.core/format-emails orders))))

(describe "should treat rd. and st. the same"
  (before (def orders  [{:street "123 sesame st."},
                        {:street "123 sesame rd."}])
    (def expected-orders [{:street "123 sesame street"},
                          {:street "123 sesame road"}]))
  (it "abbreviations are replaced with full"
    (should= expected-orders (fraud-prevention.core/format-streets orders))))




