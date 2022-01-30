(use 'clojure.test)

(defn divisible? [x y]
  (zero? (rem x y))
  )

(defn sieve [stream]
  (cons (first stream) (lazy-seq (sieve (filter #(not (divisible? % (first stream))) (rest stream)))))
  )

(def primes (sieve (iterate inc 2)))

(deftest SE
  (is (= (take 2 primes) '(2 3)))
  (is (= (take 3 primes) '(2 3 5)))
  (is (= (take 4 primes) '(2 3 5 7)))
  (is (= (take 5 primes) '(2 3 5 7 11)))
  (is (= (nth primes 30) 127))
)

(run-tests)