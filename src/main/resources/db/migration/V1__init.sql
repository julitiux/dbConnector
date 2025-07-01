CREATE TABLE people
(
    id           UUID           NOT NULL,
    username     VARCHAR UNIQUE NOT NULL,
    name         VARCHAR        NOT NULL,
    lastname     VARCHAR        NOT NULL,
    password     VARCHAR        NOT NULL,
    date_created VARCHAR        NOT NULL,
    last_updated VARCHAR        NOT NULL,
    PRIMARY KEY (id)
);
