(defn incWord [word, alphabet]
       (filter #(some? %) (map (fn [letter] (if (not= letter (str (first word))) (str letter word))) alphabet))
       )

(defn incWords [words alphabet]
       (map #(incWord % alphabet) words)
       )

(defn getStrings [alphabet, N]
       (sort (reduce (fn [words coll] (apply concat (incWords words alphabet))) alphabet (range 1 N)))
       )

(getStrings '("a" "b" "c") 2)
