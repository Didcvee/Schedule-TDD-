--liquibase formatted sql

--changeset didcvee:1
create table aloha(
    mamasito varchar
);

--changeset didcvee:2
create table rasp
(
    ordered  integer,
    group_id varchar,
    week_day varchar,
    item     varchar,
    date_    date
);
--changeset didcvee:3
create table group_(
    name varchar
);