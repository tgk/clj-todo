(ns leiningen.todo
  (:require [clojure.java.io :as io]
            [leiningen.core.eval :as lein-eval]
            [leiningen.clean :as lein-clean]
            [clojure.tools.namespace.find :as ns-find]))

(defn todo
  "Prints a summary of todos annotated using clj-todo.todo/todo.
If namespaces are given as commandline args, prints the summary
for those namespaces; otherwise prints the summary for all the
:namespaces in project.clj. If :todo-log is given in project, 
also writes the log to the filename in :todo-log."
  [project & namespaces]
  (let [namespaces (if (seq namespaces)
		     (map symbol namespaces)		     
		     (ns-find/find-namespaces-in-dir (io/file (:source-path project))))]
    (lein-clean/delete-file-recursively (io/file (:compile-path project)) true)
    (lein-eval/eval-in-project project
                               `(do
                                  (require '~'clj-todo)
                                  (apply require '~namespaces)
                                  (clj-todo/todo-summary)))
    (if (contains? project :todo-log) 
      (lein-eval/eval-in-project project 
                                 `(do
                                    (require '~'clj-todo)
                                    (apply require '~namespaces)
                                    (clj-todo/todo-summary-file ~(:todo-log project)))))))
