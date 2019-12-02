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
        (do 
            ; (println (nth registers 0)) 
            registers
        )
        (recur (op registers position) (+ position 4))
    )
)

(defn assignInput [registers x y] 
    (do
        ; (println x y)
        (assoc (assoc registers 1 x) 2 y)
    )
)

(let [answer 19690720]
(let [memory 
    (vec (map read-string
        (str/split
            (slurp "input")
            #","
        )
    ))]
    (let [result 
        (loop 
            [noun 0]
            (let [resultVerb
                (loop 
                    [verb 0]
                    (let [output 
                        (runCode (assignInput memory noun verb) 0)] 
                        (cond 
                            (= (nth output 0) answer) verb
                            (<= verb 99) (recur (inc verb))
                            :else nil
                        )
                    )
                )]
                (if 
                    (and (= nil resultVerb) (< noun 99)) 
                    (recur (inc noun)) 
                    (vector noun resultVerb)
                )
            )
        )]
        (do 
            (println result)
            (println (+ (* (nth result 0) 100) (nth result 1)))
        )
    )
    
)
)