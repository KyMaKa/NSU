(ns job.Task3
  (:use clojure.test))

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
        coll ;; если и так пустая, то не паримся и возвращаем её же
        (concat ;; соединяем кусочки, которые обработает наш фильтр
          (apply concat
                  (->> (partition-all block-size (take chunk-size coll)) ;; разбиваем на список списков по block-size элементов
                       (map #(future (doall (filter pred %))))
                       (doall)
                       (map deref))) ;; ждём конца исполнения
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

(deftest pfilterTest
  (is (= (pfilter longCalc (range 2 100)) (filter longCalc (range 2 100))))
  (is (= (nth (pfilter longCalc naturals) 150) (nth (filter longCalc naturals) 150))) ;; test for infinite list
)

;(run-tests 'job.Task3)