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

--changeset Beasty:product_002
ALTER TABLE products
    ADD COLUMN image BYTEA , ADD COLUMN image_type varchar(255);

--rollback alter table products drop column image BYTEA , drop column image_type varchar(255);


--changeset Beasty:product_003
ALTER TABLE products
    ADD COLUMN image_name VARCHAR(255);

ALTER TABLE products
    RENAME COLUMN image TO image_data;

--rollback
--ALTER TABLE products
--DROP COLUMN image_name;

--ALTER TABLE products
  --  RENAME COLUMN image_data TO image;


