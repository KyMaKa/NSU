(ns task3.core
  (:use clojure.test))

(def naturals
  (lazy-seq
    (cons 1 (map inc naturals))))

(defn filter-parallel [pred coll]
  (let [chunk-size 10000,
        block-size 1000]
  (lazy-seq
    (if (empty? coll) ; coll is split to keep the function lazy
      coll
      (concat
        (reduce concat '()
          (->> (partition-all block-size (take chunk-size coll))
               (map #(future (doall (filter pred %))))
               (doall)
               (map deref)))
        (filter-parallel pred (drop chunk-size coll)))))))


(defn prime [n]
  (let [helper (fn [x i]
                 (if (> (* i i) x)
                   true
                   (if (zero? (mod x i))
                     false
                     (recur x (+ i 2)))))]
    (or
      (= n 2)
      (and
        (not (even? n))
        (> n 1)
        (helper n 3)))))

(defn -main [& args]
  (println "Parallel filter:")
  (time (doall (filter-parallel prime (range 2 1000000))))
  (println "Filter")
  (time (doall (filter prime (range 2 1000000))))
  (println "Infinite list")
  (println (nth (filter-parallel prime naturals) 10000))
  (shutdown-agents)
  )

(deftest filter-test
  (testing "Testing"
    (is (= (filter-parallel prime (range 2 1000000)) (filter prime (range 2 1000000))))
    (is (= (nth (filter-parallel prime naturals) 10000) (nth (filter prime naturals) 10000)))
    ))

(run-tests)