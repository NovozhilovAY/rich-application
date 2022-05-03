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

INSERT INTO users (login, first_name, last_name, profile_descr, profile_picture, country, city, money)
values ('login1', 'Дмитрий', 'Бражник', 'Всем привет! Я главный андроид разработчик', 'picture.png', 'Россия', 'Нижний Новгород', 0);

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
