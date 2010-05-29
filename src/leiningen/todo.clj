(ns leiningen.todo
  (:use [leiningen.compile :only (eval-in-project)])
  (:use leiningen.clean)
  (:import java.io.File))

(defn todo
  "Prints a summary of todos annotated using clj-todo.todo/todo.
If namespaces are given as commandline args, prints the summary
for those namespaces; otherwise prints the summary for all the
:namespaces in project.clj"
  [project & namespaces]
  (let [namespaces (if (seq namespaces)
                     (map symbol namespaces)
                     (:namespaces project))]
    (empty-directory (File. (:compile-path project)) true)
    (eval-in-project project
		     `(do
			(require '~'clj-todo.todo)
			(apply require '~namespaces)
			(clj-todo.todo/todo-summary)))))
