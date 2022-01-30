(use 'clojure.test)

(def naturals
  (iterate inc 1)
  )

(defn longCalc [n]
  (Thread/sleep 5)
  (zero? (rem n 5))
  )

(defn pfilter [pred coll]
  (let [chunk-size 10000, block-size 500]
    (lazy-seq
      (if (empty? coll)
        coll
        (concat
          (apply concat
                  (->> (partition-all block-size (take chunk-size coll))
                       (map #(future (doall (filter pred %))))
                       (doall)
                       (map deref)))
          (pfilter pred (drop chunk-size coll)))
        )
      )
    )
  )

(defn checkEfficiency []
  "Сравнение эффективности параллельного варианта filter'а и обычного"
  (println "\npfilter:")
  (time (doall (pfilter longCalc (range 2 10000))))
  (println "\nfilter:")
  (time (doall (filter longCalc (range 2 10000))))
  nil
  )
(checkEfficiency)

(deftest pfilterTest
  (is (= (pfilter longCalc (range 2 100)) (filter longCalc (range 2 100))))
  (is (= (nth (pfilter longCalc naturals) 150) (nth (filter longCalc naturals) 150)))
)

(run-tests)