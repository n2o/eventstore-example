(ns eventstore.submission.core
  (:use [korma.core]
        [korma.db]))

(defdb pgfoo (postgres
              {:host "localhost"
               :port "5432"
               :db "foo"
               :user "foo"
               :password "foo"
               :delimiters ""}))

(defentity events
  (entity-fields :id :aggregate :type :version :data))

(select events)

(insert events
        (values {:aggregate 0 :type "test" :version 1 :data "hello, world!"}))

