CREATE TABLE urls (
    id              SERIAL  PRIMARY KEY,
    original_url    VARCHAR NOT NULL,
    modified_url    VARCHAR NOT NULL UNIQUE,
    calls           INT     NOT NULL,
    site_id         INT     NOT NULL REFERENCES sites(id)
);