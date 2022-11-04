-- auto-generated definition
create table users
(
    id              serial
        primary key,
    login           varchar,
    first_name      varchar not null,
    last_name       varchar not null,
    profile_descr   varchar,
    status   varchar,
    profile_picture varchar default 'default_picture.png'::character varying,
    country         varchar,
    city            varchar,
    money           numeric,
    password        varchar not null
);

alter table users
    owner to postgres;

create table payments_log
(
    id      serial
        primary key,
    user_id integer                             not null
        constraint user_f_key
            references users
            on update cascade on delete cascade,
    amount  numeric                             not null,
    date_p  timestamp default CURRENT_TIMESTAMP not null
);

alter table payments_log
    owner to postgres;

create procedure make_payment(IN user_id integer, IN amount double precision)
    language plpgsql
as
$$
BEGIN
UPDATE users SET money = money + amount WHERE id = user_id;
INSERT INTO payments_log (user_id, amount) VALUES (user_id, amount);
END;
$$;

alter procedure make_payment(integer, double precision) owner to postgres;

create table if not exists roles
(
    id   serial
    constraint roles_pkey
    primary key,
    name varchar not null
    constraint roles_name_key
    unique
);

create table if not exists user_roles
(
    user_id integer not null
    constraint user_roles_user_id_fkey
    references users,
    role_id integer not null
    constraint user_roles_role_id_fkey
    references roles,
    constraint user_roles_user_id_role_id_key
    unique (user_id, role_id)
    );



INSERT INTO users (login, first_name, last_name, status, profile_descr, profile_picture, country, city, money, password)
values ('login1', 'Дмитрий', 'Бражник','Если волк молчит, его лучше не перебивать', 'Всем привет! Я главный андроид разработчик', 'http://localhost:8080/api/img/default-profile-picture.jpg', 'Россия', 'Нижний Новгород', 0, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money, password)
values ('login2', 'Новожилов', 'Александр', 'Сас', 'http://localhost:8080/api/img/default-profile-picture.jpg', 'Россия', 'Нижний Новгород', 0, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money, password)
values ('login3', 'Путин', 'Владимир', 'Описание', 'http://localhost:8080/api/img/default-profile-picture.jpg', 'Россия', 'Москва', 50000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money, password)
values ('login4', 'Петр', 'Петров', 'Не молодец', 'http://localhost:8080/api/img/default-profile-picture.jpg', 'Россия', 'Тверь', 0, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money, password)
values ('login5', 'Дональд', 'Трамп', 'make america great again', 'http://localhost:8080/api/img/default-profile-picture.jpg', 'USA', 'Washington', 100, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money, password)
values ('login5', 'Джон', 'Рембо', 'Первая кровь на них', 'http://localhost:8080/api/img/default-profile-picture.jpg', 'USA', 'Washington', 100, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');


INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_USER');

INSERT INTO user_roles(user_id, role_id) VALUES (1,1);
INSERT INTO user_roles(user_id, role_id) VALUES (1,2);

INSERT INTO user_roles(user_id, role_id) VALUES (2,2);
INSERT INTO user_roles(user_id, role_id) VALUES (3,2);
INSERT INTO user_roles(user_id, role_id) VALUES (4,2);
INSERT INTO user_roles(user_id, role_id) VALUES (5,2);
INSERT INTO user_roles(user_id, role_id) VALUES (6,2);