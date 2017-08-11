-- Add table for events
CREATE TABLE events (id SERIAL, aggregate VARCHAR, type VARCHAR, message VARCHAR, version INT, data varchar, created TIMESTAMP NOT NULL DEFAULT NOW());

-- Define function for notify-event
CREATE OR REPLACE FUNCTION PUBLIC.NOTIFY() RETURNS trigger AS
$BODY$
BEGIN
PERFORM pg_notify('new_event', row_to_json(NEW)::text);
RETURN new;
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE COST 100;

-- Creates trigger and binds it to "events"-table
CREATE TRIGGER new_event
AFTER INSERT
ON events
FOR EACH ROW
EXECUTE PROCEDURE PUBLIC.NOTIFY();

