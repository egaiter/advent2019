(println
(with-open 
    [rdr (clojure.java.io/reader "input")] 
    (reduce 
        (fn [carry string] 
            (+ 
                carry
                (- 
                    (Math/floor 
                        (/ 
                            (Float/parseFloat string)
                            3
                        )
                    )
                    2
                )
            )
        ) 
        0 
        (line-seq rdr)
    )
)
)