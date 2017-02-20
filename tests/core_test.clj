(ns core-test
  (:require  [clojure.test :as t]
             [gilded-rose.core :as core]))

(def ^:const MONGOOSE "Elixir of the Mongoose")


(t/deftest test-requirements
  (t/testing "quality decreasing with time"
    (let [mongoose (core/item "Elixir of the Mongoose" 5 7)]
      (t/is (= (core/update-quality [mongoose])
               (list {:name "Elixir of the Mongoose", :sell-in 4, :quality 6})))))

  (t/testing "quality never below 0"))
