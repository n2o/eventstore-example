# eventstore

Just wanted to play around with an event store and two aggregates using this
store to exchange messages.

I am using a Postgresql database with a single table for the event store.
Submission can add new submissions, which add an event into the event store. The
corrector-module creates a listener to the database and gets notified by
postgres when a new event has been added.

Start your postgres-database like this:

```
docker run --rm --name eventdb -e POSTGRES_PASSWORD=foo -e POSTGRES_USER=foo -e POSTGRES_DB=foo -v $(pwd)/db/entrypoint:/docker-entrypoint-initdb.d -p 5432:5432 postgres
```

This starts a local database in a container named "eventdb" and all credentials
are "foo". Automatically includes a seed, which adds the desired table and creates
a plpgsql-function, which is triggered on a new event. Programs can connect to
the channel defined in the notify-function to get notified on changes.

Start the docker container first!

If you are using CIDER, you can connect to the REPL, load all files into it and
see the notifications in a buffer called *cider-repl localhost*.

I am using a different pgjdbc-implementation,
because [pgjdbc-ng](https://github.com/impossibl/pgjdbc-ng) supports
asynchronous connections to the database, which are necessary to get notified by postgres.

## License

Copyright © 2017 Christian Meter

Distributed under the MIT License.
