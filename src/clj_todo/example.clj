(ns clj-todo.example
  (:use clj-todo.todo))

(clear-todos)

(map #(todo "Bad function!" (inc %)) (range 10))

(defn lousy-function
  [param-1 param-2]
  (do
    (println "doing one thing here")
    (todo
     "This part looks ugly"
     (map param-1 (repeat param-2))
     )
    (println "a third thing here")))

(todo
 "I don't like how this function works at all. It could be O(1)."
 (defn range-sum 
   [n]
   (reduce + (range n))))

(todo
 "A goal is to make it possible to write something like this:"
 (comment
   (todo-summary
    (main-program))))

(todo
 "Another goal is to make a lein lib so that 
  lein todos
prints the todos from a project"
 nil)

(todo
 "Also, would it be interesting to have a form of todos
that do not require a form? For general thoughts."
 nil)

(todo-summary)