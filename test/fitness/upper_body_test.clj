(ns fitness.upper-body-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [fitness.upper-body :refer :all]
            [clojure.test :refer :all]))

(deftest push-up-test
  (let [results (push-ups 50)]
    (is (= (:actual-reps results) 50))
    (is (<= (:seconds results) (* 4 60)))))

(deftest pull-up-test
  (let [results (pull-ups 10)]
    (is (= (:actual-reps results) 10))
    (is (< (:seconds results) 60))))

(run-tests)