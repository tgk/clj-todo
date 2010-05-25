(ns 
    #^{:author "Thomas G. Kristensen"
       :doc "Libary for adding todo statements to a clojure program.

Wrap any form in a todo to add a reminder to revisit the code later.

(todo
 \"I don't like how this function works at all. It could be O(1).\"
 (defn range-sum 
   [n]
   (reduce + (range n))))

To review the annotations use the todo-summary function.

(todo-summary)

Summary of todos:

I don't like how this function works at all. It could be O(1).
(defn range-sum [n] (reduce + (range n)))"}
  clj-todo.todo 
  (:use clojure.contrib.pprint))

(def *todo-log* (atom []))

(defn- save-code-and-snippet 
  "Helper function for saving comment and code-str in the todo log."
  [comment code-str]
  (swap! *todo-log* conj [comment code-str]))

(defmacro todo 
  "Annotates a form with a comment for later review.
  Adds the comment and the form to the batch of todos, which
  can be revied later using todo-summary."
  [comment body] 
  (do
    (save-code-and-snippet 
     comment 
     (with-out-str
       (with-pprint-dispatch *code-dispatch* (pprint body))))
    body))

(defn clear-todos
  "Clears the todo log."
  []
  (swap! *todo-log* (fn [& more] [])))

(defn todo-summary
  "Prints a summary of the todos in the project."
  []
  (println "Summary of todos:")
  (println)
  (doall
   (map (fn [[comment code-str]] 
	  (do (println comment) (println code-str)))
	@*todo-log*)))