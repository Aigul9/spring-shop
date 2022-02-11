CREATE TABLE IF NOT EXISTS categories
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(255) NOT NULL
);

--drop table customers;
CREATE TABLE IF NOT EXISTS customers
(
    id    SERIAL PRIMARY KEY ,
    first_name  VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendors
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(255) NOT NULL
);