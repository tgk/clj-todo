(ns clj-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo]))

(deftest todo
  (testing "adds an entry to the *todo-log*"
    (clj-todo/todo "something" (+ 1 2))
    (is (= @clj-todo/*todo-log*
           [["something"
             "(+ 1 2)\n"]]))))
