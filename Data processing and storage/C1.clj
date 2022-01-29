(defn fun [alphabet n] ( 
    let [
      contains (fn [item l] (reduce (fn [acc cur] (if (= item cur) true acc)) false l)),
      
      builder (fn [oldList] (
        reduce (fn [result item] (concat item result)) () (
          map (
            fn [oldWord] (
              reduce (fn [acc letter] (if (contains letter oldWord) acc (cons (cons letter oldWord) acc))) 
              () 
              alphabet
            )
          ) 
          oldList
        )
      )
      )
    ]
    
   (
     map
      (fn [x] (apply str x))
      (
        reduce 
        (fn [acc i] (builder acc))
        '(())
        (range n)
      )
    )
  )
)

(println (fun '("a" "b" "c") 2))

