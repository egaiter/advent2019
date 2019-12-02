(defn calcFuel [mass] 
    (let 
        [num (- 
            (Math/floor 
                (/ 
                    mass
                    3
                )
            )
            2
        )]
        (if (> num 0) (+ num (calcFuel num)) 0)
    )
)
(println
(with-open 
    [rdr (clojure.java.io/reader "input")] 
    (reduce 
        (fn [carry string] 
            (+ 
                carry
                (calcFuel (Float/parseFloat string))
            )
        ) 
        0 
        (line-seq rdr)
    )
)
)