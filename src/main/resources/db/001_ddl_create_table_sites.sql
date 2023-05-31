CREATE TABLE sites (
    id          SERIAL  PRIMARY KEY,
    site        VARCHAR NOT NULL,
    username    VARCHAR NOT NULL UNIQUE,
    password    VARCHAR NOT NULL
);