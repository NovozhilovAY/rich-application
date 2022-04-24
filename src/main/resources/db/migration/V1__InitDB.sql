-- auto-generated definition
create table users
(
    id              serial
        primary key,
    login           varchar,
    first_name      varchar not null,
    last_name       varchar not null,
    profile_descr   varchar not null,
    profile_picture varchar default 'default_picture.png'::character varying,
    country         varchar,
    city            varchar,
    money           numeric
);

alter table users
    owner to postgres;

