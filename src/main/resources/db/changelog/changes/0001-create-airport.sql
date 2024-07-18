--liquibase formatted sql

--changeset airport:1

CREATE TABLE AIRPORT
(
    id serial primary key,
    country varchar not null,
    city varchar not null,
    airport varchar not null
);