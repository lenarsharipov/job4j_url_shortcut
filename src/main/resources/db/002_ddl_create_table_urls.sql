CREATE TABLE urls (
    id              SERIAL  PRIMARY KEY,
    original_url    VARCHAR NOT NULL,
    modified_url    VARCHAR NOT NULL,
    calls           INT     NOT NULL
);