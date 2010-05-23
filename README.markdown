# Clojure TODO annotation 

The `clj-todo` lib is designed for easily annotating Clojure programs with todo items.
The lib generates a todo summary which includes code fragments to make it easy to extract all todos without having to read trough the entire source code.
As Clojure code (idiomatically) is written as small atomic components these reports should be perfect for code reviews.

## Example usage

Here is an example of some code, using the todo macro.

<pre>
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
</pre>

To get a summary of the todos, call `todo-summary`.

<pre>
Summary of todos:

This part looks ugly
(map param-1 (repeat param-2))

I don't like how this function works at all. It could be O(1).
(defn range-sum [n] (reduce + (range n)))
</pre>


## TODOs (on the lib)

* Create a lein plugin, such that `lein todo` runs trough a project and prints the todo summary.
* Right now, forms are reformatted before printing. This might not be desirable. Should the lib use pprint or is it possible to save the formatting?