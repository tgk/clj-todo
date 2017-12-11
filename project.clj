(defproject clj-todo "0.4.0-SNAPSHOT"
  :description "A small lib for adding todo annotations to Clojure projects."
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.namespace "0.2.11"]
		 [leiningen "2.8.1"]]
  :dev-dependencies [[autodoc "0.7.1"]]
  :autodoc {:copyright "2010 Thomas G. Kristensen"}
  :todo-log "todo-summary.log")
