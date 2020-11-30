ALTER TABLE task ADD user_id serial NOT NULL;
ALTER TABLE task ADD public boolean DEFAULT '0' NOT NULL;

CREATE TABLE IF NOT EXISTS appuser(
    id            serial PRIMARY KEY,
    login         varchar(32)  NOT NULL,
    password      varchar(255) NOT NULL,
    role          varchar(16)  NOT NULL DEFAULT 'AUTHOR'
)