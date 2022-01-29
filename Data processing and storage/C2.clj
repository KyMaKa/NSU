(use 'clojure.test)

(deftest primes-test
  (is (= 2 (first primes)))
  (is (= 3 (nth primes 1)))
  (is (= 179 (nth primes 40)))
)

(def naturals
(lazy-seq
(cons 1 (map inc naturals))))

(defn subtract [lst1 lst2] ; 
(lazy-seq 
    (if (< (first lst1) (first lst2)) 
    (cons (first lst1) (subtract (rest lst1) lst2))
    (
      if (> (first lst1) (first lst2)) 
      (subtract lst1 (rest lst2))
      (subtract (rest lst1) (rest lst2))
    )
    )
  )
)

(defn sieve [lst] (lazy-seq
(cons (first lst) (sieve (subtract (rest lst) (map (fn [x] (* x (first lst))) naturals))))
))

(def primes 
(lazy-seq (
  sieve (map inc naturals)
)
)
)

(run-tests)