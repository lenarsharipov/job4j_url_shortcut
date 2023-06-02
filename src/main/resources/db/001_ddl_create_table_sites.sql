CREATE TABLE sites (
    id          SERIAL  PRIMARY KEY,
    site        VARCHAR NOT NULL UNIQUE,
    login       VARCHAR NOT NULL UNIQUE,
    password    VARCHAR NOT NULL
);