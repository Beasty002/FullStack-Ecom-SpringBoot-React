--liquibase formatted sql

--changeset Beasty:product_001
CREATE TABLE products
(
    id           bigserial PRIMARY KEY,
    name         varchar(255),
    description  varchar(255),
    brand        varchar(255),
    price        int,
    category     varchar(255),
    release_date DATE,
    available    boolean,
    quantity     int

);

--rollback drop table products

