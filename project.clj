(defproject clj-todo "0.1.0-SNAPSHOT"
  :description "A small lib for adding todo annotations to Clojure projects."
  :dependencies [[org.clojure/clojure "1.1.0"]
		 [org.clojure/clojure-contrib "1.1.0"]]
  :dev-dependencies [[leiningen/lein-swank "1.1.0"]
		     [autodoc "0.7.1"]]
  :autodoc {:copyright "2010 Thomas G. Kristensen"}
  :namespaces [clj-todo.example]
  :todo-log "todo-summary.log")