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

To get a summary of the todos, either call `todo-summary` within the project (only works if it isn't pre-compiled) or simply use `lein todo`.

<pre>
Summary of todos:

This part looks ugly
(map param-1 (repeat param-2))

I don't like how this function works at all. It could be O(1).
(defn range-sum [n] (reduce + (range n)))
</pre>

At the moment, running `lein todo` will remove all compiled files as the program has to expand macros to build the todo log.

If `:todo-log` is given in `project.clj`, todos will also be written to that file.
See `project.clj` for an example of this.