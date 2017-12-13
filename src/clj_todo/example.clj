(ns clj-todo.example
  (:require [clj-todo :refer [todo]]))

(defn lousy-function
  [param-1 param-2]
  (do
    (println "doing one thing here")
    (todo "This part looks ugly"
          (map param-1 (repeat param-2)))
    (println "a third thing here")))

(todo
 "I don't like how this function works at all. It could be O(1)."
 (defn range-sum 
   [n]
   (reduce + (range n))))
