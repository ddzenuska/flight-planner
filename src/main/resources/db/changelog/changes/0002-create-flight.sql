--liquibase formatted sql

--changeset flight:2

CREATE TABLE FLIGHT
(
    id              serial primary key,
    from_airport_id int       not null,
    to_airport_id   int       not null,
    carrier         varchar   not null,
    departure_time  timestamp not null,
    arrival_time    timestamp not null,
    CONSTRAINT from_airport_fk FOREIGN KEY (from_airport_id) references AIRPORT (id),
    CONSTRAINT to_airport_fk FOREIGN KEY (to_airport_id) references AIRPORT (id)
);