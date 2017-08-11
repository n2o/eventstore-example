(ns eventstore.submission.core
  (:require [eventstore.events :as events])
  (:use [korma.core]
        [korma.db]))

(def subs (atom []))

(def config {:aggregate "submission"
             :version 1})

(defn add!
  "Add new submission. Locally stores the new submissions and adds a new event
  in the eventstore."
  [author course file]
  (let [sub {:author author :course course :file file}]
    (swap! subs conj sub)
    (events/add! {:aggregate (:aggregate config)
                  :type "add"
                  :message "Added new submission"
                  :version (:version config)
                  :data (str sub)})))
;; (add! "Christian" "SS17/ProPra" "/path/to/file")
;; (add! "Nicht Christian" "SS17/ProPra" "/path/to/another/file")
;; (events/all)
