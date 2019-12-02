(require '[clojure.string :as str])
(defn op [registers position] 
    (assoc 
            registers 
            (nth registers (+ position 3))
            (
                (cond 
                    (= (nth registers position) 1) + 
                    (= (nth registers position) 2) *
                )
                (nth registers (nth registers (+ position 1))) 
                (nth registers (nth registers (+ position 2))) 
            )
    )
)

(defn runCode [registers position] 
    (if
        (= (nth registers position) 99)
        registers
        (recur (op registers position) (+ position 4))
    )
)

(defn assignInput [registers x y] 
    (assoc (assoc registers 1 x) 2 y)
)

(let [input 
    (vec (map read-string
        (str/split
            (slurp "input")
            #","
        )
    ))]
    (pr (
        take 1 (runCode (assignInput input 12 2) 0)
    ))
)