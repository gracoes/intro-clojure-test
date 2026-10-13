(ns fitness.upper-body-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require [fitness.upper-body :refer :all]
            [clojure.test :refer :all]))

(deftest push-up-test
  (let [results (push-ups 50)]
    (is (= (:actual-reps results) 50))
    (is (<= (:seconds results) (* 4 60)))))

(deftest pull-up-test
  (let [{:keys [actual-reps seconds]} (pull-ups 10)]
    (is (= actual-reps 10))
    (is (< seconds 60))))

(deftest bench-press-too-heavy-test
  (is (thrown? fitness.safety.UnsafeResistanceException (bench-presses 300 10))))

(deftest bench-press-test
  (let [{actual-reps :actual-reps seconds :seconds} (bench-presses 100 20)]
    (is (= actual-reps 20))
    (is (<= seconds 60))))

(run-tests)