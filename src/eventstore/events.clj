(ns eventstore.events
  (:use [korma.core]
        [korma.db]))

(defdb pg (postgres
           {:host "localhost"
            :port "5432"
            :db "foo"
            :user "foo"
            :password "foo"
            :delimiters ""}))

(defentity events
  (entity-fields :id :aggregate :type :message :version :data :created))

(defn add!
  "Adds new event to event-store.

  Example:
  (add! {:aggregate \"foo\" :type \"test\" :version 1 :data \"hello, world!\"})"
  [vals]
  (insert events (values vals)))
;; (add! {:aggregate "foo" :type "test" :version 1 :data "hello, world!"})

(defn all
  "Returns all stored events in database."
  []
  (select events))
