-- auto-generated definition
create table users
(
    id              serial
        primary key,
    login           varchar,
    first_name      varchar not null,
    last_name       varchar not null,
    profile_descr   varchar not null,
    status   varchar,
    profile_picture varchar default 'default_picture.png'::character varying,
    country         varchar,
    city            varchar,
    money           numeric
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

INSERT INTO users (login, first_name, last_name, status, profile_descr, profile_picture, country, city, money)
values ('login1', 'Дмитрий', 'Бражник','Если волк молчит, его лучше не перебивать', 'Всем привет! Я главный андроид разработчик', 'picture.png', 'Россия', 'Нижний Новгород', 0);

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money)
values ('login2', 'Новожилов', 'Александр', 'Сас', 'picture2.png', 'Россия', 'Нижний Новгород', 0);

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money)
values ('login3', 'Путин', 'Владимир', 'Описание', 'picture3.png', 'Россия', 'Москва', 50000);

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money)
values ('login4', 'Петр', 'Петров', 'Не молодец', 'picture4.png', 'Россия', 'Тверь', 0);

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money)
values ('login5', 'Дональд', 'Трамп', 'make america great agan', 'picture5.png', 'USA', 'Washington', 100);

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money)
values ('login5', 'Джон', 'Рембо', 'Первая кровь на них', 'picture6.png', 'USA', 'Washington', 100);
