(ns core-test
  (:require  [clojure.test :as t]
             [gilded-rose.core :as core]))

(def ^:const MONGOOSE "Elixir of the Mongoose")


(t/deftest test-requirements
  (t/testing "quality decreasing with time"
    (let [mongoose (:elixir core/ITEMS-CONFIG)]
      (t/is (= (core/update-quality [mongoose])
               (list {:name "Elixir of the Mongoose", :sell-in 4, :quality 6})))))

  (t/testing "quality never below 0"
    (let [mongoose (:elixir core/ITEMS-CONFIG)
          composed (apply comp (repeat 10 core/update-quality))]
      (t/is (= (:quality (first (composed [mongoose])) 0)))))

  (t/testing "Quality never above 50"
    ;; this actually does work
    (let [mongoose (:brie core/ITEMS-CONFIG)
          composed (apply comp (repeat 100 core/update-quality))]
      (t/is (= (:quality (first (composed [mongoose]))) 50))))

  (t/testing "Sulfuras is legendary"
    (let [sulfuras (:sulfuras core/ITEMS-CONFIG)]
      (t/is (= sulfuras (core/update-single-item sulfuras)))))

  #_(t/testing "backstage"))
