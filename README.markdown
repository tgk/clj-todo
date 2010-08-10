# Clojure to do annotation 

The `clj-todo` lib is designed for easily annotating Clojure programs with todo items.
The lib generates a todo summary which includes code fragments to make it easy to extract all todos without having to read trough the entire source code.
As Clojure code (idiomatically) is written as small atomic components these reports should be perfect for code reviews.

## Installation

`clj-todo` version 0.4.0 is uploaded to [clojars](http://clojars.org). 
To use it, simply add it to your lein dependencies as

    [clj-todo "0.4.0-SNAPSHOT"]

The current version (0.4.0) is targeted at Clojure 1.2.0 and Clojure contrib 1.2.0.

## Example usage

Here is a full example of using the `todo` macro: (also included in [source](http://github.com/tgk/clj-todo/blob/master/src/clj_todo/example.clj))

<pre>
(ns clj-todo.example
  (:use clj-todo))

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

To get a summary of the todos use `lein todo`.
If `lein todo` is given a list of namespaces it will print the todos of these. 
If none is given it will use all namespaces in the project.

<pre>
Summary of todos:

This part looks ugly
(map param-1 (repeat param-2))

I don't like how this function works at all. It could be O(1).
(defn range-sum [n] (reduce + (range n)))
</pre>

At the moment, running `lein todo` will remove all compiled files as the program has to expand macros to build the todo log.

If `:todo-log` is given in `project.clj`, todos will also be written to that file.
See [`project.clj`](http://github.com/tgk/clj-todo/blob/master/project.clj) for an example of this.