CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start 1 increment 1;

CREATE TABLE IF NOT EXISTS task (
    id            serial PRIMARY KEY,
    title         varchar(255) NOT NULL,
    task_date     date NOT NULL,
    start_time    timestamptz NOT NULL,
    end_time      timestamptz NOT NULL
)