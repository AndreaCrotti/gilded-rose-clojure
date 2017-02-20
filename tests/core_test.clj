(ns core-test
  (:require  [clojure.test :as t]
             [gilded-rose.core :as core]))

(def ^:const MONGOOSE "Elixir of the Mongoose")


(t/deftest test-requirements
  (t/testing "quality decreasing with time"
    (let [mongoose (core/item MONGOOSE 5 7)]
      (t/is (= (core/update-quality [mongoose])
               (list {:name "Elixir of the Mongoose", :sell-in 4, :quality 6})))))

  (t/testing "quality never below 0"
    (let [mongoose (core/item MONGOOSE 2 0)
          composed (apply comp (repeat 10 core/update-quality))]
      #_(t/is (= (composed [mongoose]) []))))

  (t/testing "Quality never above 50"
    ;; this actually does work
    (let [mongoose (core/item "Aged Brie" 5 7)
          composed (apply comp (repeat 100 core/update-quality))]
      (t/is (= (:quality (first (composed [mongoose]))) 50)))))
