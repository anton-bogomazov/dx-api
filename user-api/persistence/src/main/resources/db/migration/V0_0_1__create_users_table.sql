CREATE TABLE users(
    id          UUID    PRIMARY KEY NOT NULL,
    version     int     NOT NULL default 0,
    first_name  TEXT    NOT NULL,
    last_name   TEXT    NOT NULL,
    unique(first_name, last_name)
);