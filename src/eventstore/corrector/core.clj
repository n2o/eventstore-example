(ns eventstore.corrector.core
  (:import [com.impossibl.postgres.jdbc PGDataSource]
           [com.impossibl.postgres.api.jdbc PGNotificationListener]))

(def datasource
  (doto (PGDataSource.)
    (.setHost "localhost")
    (.setPort 5432)
    (.setDatabase "foo")
    (.setUser "foo")
    (.setPassword "foo")))

(def listener
  (reify PGNotificationListener
    (^void notification [this ^int processId ^String channelName ^String payload]
     (println "msg: " payload))))

(def connection
  (doto (.getConnection datasource)
    (.addNotificationListener listener)))


;; the println response from above appears in the *cider-nrepl localhost* Buffer
;; in emacs
(defn arm-listener
  "Creates listener for new events in the eventstore."
  []
  (doto (.createStatement connection)
    (.execute "LISTEN new_event;")
    (.close)))
(arm-listener)
