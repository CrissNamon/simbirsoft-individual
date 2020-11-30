CREATE TABLE IF NOT EXISTS role(
    id          serial PRIMARY KEY,
    name        varchar(255) NOT NULL
);

INSERT INTO role(id, name) VALUES (0, 'AUTHOR');
INSERT INTO role(id, name) VALUES (1, 'ADMIN');

ALTER TABLE appuser DROP COLUMN role;
ALTER TABLE appuser ADD COLUMN role serial NOT NULL;
ALTER TABLE appuser ALTER COLUMN role SET DEFAULT 0;

ALTER TABLE appuser ADD CONSTRAINT fk_role FOREIGN KEY (role) REFERENCES role(id) ON UPDATE CASCADE ;
