(ns #^{:author "Thomas G. Kristensen"
       :doc "Libary for adding todo statements to a clojure program.

Wrap any form in a todo to add a reminder to revisit the code later.

(defn gimme-ten []
  (todo
   \"This is an ugly way to generate the first ten numbers.\"
   (take 10 (iterate inc 1))))

To review the annotations use the todo-summary function.

(todo-summary)

Summary of todos:

This is an ugly way to generate the first ten numbers.
(take 10 (iterate inc 1)))"}
  clj-todo.todo)

(def *todo-log* (atom []))

(defn- save-code-and-snippet 
  "Helper function for saving comment and code-str in the todo log."
  [comment code-str]
  (swap! *todo-log* conj [comment code-str]))

(defmacro todo 
  "Annotates a form with a comment for later review.
  Adds the comment and the form to the batch of todos, which
  can be revied later using todo-summary."
  [comment form] 
  (do 
    (save-code-and-snippet comment (str form)) 
    form))

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
	  (do
	    (println comment)
	    (println code-str)
	    (println)))
	@*todo-log*)))