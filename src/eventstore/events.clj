(ns eventstore.events
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

(defn add-event [{:keys [aggregate type version data]}]
  (insert events
          (values :aggregate aggregate
                  :type type
                  :version version
                  :data data)))
