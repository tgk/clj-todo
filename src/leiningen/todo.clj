(ns leiningen.todo
  (:use [leiningen.compile :only (eval-in-project)])
  (:use [leiningen.clean :only (empty-directory)])
  (:use [clojure.contrib.find-namespaces :only [find-namespaces-in-dir]])
  (:import java.io.File))

(defn todo
  "Prints a summary of todos annotated using clj-todo.todo/todo.
If namespaces are given as commandline args, prints the summary
for those namespaces; otherwise prints the summary for all the
:namespaces in project.clj. If :todo-log is given in project, 
also writes the log to the filename in :todo-log."
  [project & namespaces]
  (let [namespaces (if (seq namespaces)
		     (map symbol namespaces)		     
		     (find-namespaces-in-dir (File. (:source-path project))))]
    (empty-directory (File. (:compile-path project)) true)
    (eval-in-project project
		     `(do
			(require '~'clj-todo.todo)
			(apply require '~namespaces)
			(clj-todo.todo/todo-summary)))
    (if (contains? project :todo-log)
      (eval-in-project project
		     `(do
			(require '~'clj-todo.todo)
			(apply require '~namespaces)
			(clj-todo.todo/todo-summary-file ~(:todo-log project)))))))