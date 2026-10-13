(ns fitness.lower-body-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [fitness.lower-body :refer :all]
            [clojure.test :refer :all]
            [fitness.warm-up :as warm-up]
            [fitness.cool-down :as cool-down]
            [fitness.pep :as pep]))

(defn warm-up []
  (warm-up/jumping-jacks 50)
  (warm-up/knee-raises 30))

(defn cool-down []
  (cool-down/middle-split 30)
  (cool-down/forward-bend 30))

(use-fixtures :once
  (fn [tests]
    (warm-up)
    (tests)
    (cool-down)))

(use-fixtures :each
  (fn [test]
    (pep/you-can-do-it)
    (test)
    (pep/applaud)))

(deftest squat-test
  (let [results (squats 50)]
    (is (= (:actual-reps results) 50))
    (is (< (:seconds results) 60))))

(deftest frog-leap-test
  (let [results (frog-leaps 5)]
    (is (= (:actual-reps results) 5))
    (is (< (:seconds results) 30))))

(deftest weighted-squats-too-heavy-test
  (is (thrown? fitness.safety.UnsafeResistanceException (weighted-squats 500 10))))

(deftest weighted-squats-test
  (let [results (weighted-squats 100 20)]
    (is (= (:actual-reps results) 20))
    (is (<= (:seconds results) (* 2 60)))))

(run-tests)