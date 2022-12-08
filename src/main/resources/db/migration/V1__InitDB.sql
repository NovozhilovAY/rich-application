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
values ('login1', 'Дмитрий', 'Бражник','Если волк молчит, его лучше не перебивать', 'Всем привет! Я главный андроид разработчик. Добавляйтесь в друзья https://vk.com/braggggg', 'http://localhost:8080/api/img/1-image.jpg', 'Россия', 'Нижний Новгород', 10000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, status, profile_descr, profile_picture, country, city, money, password)
values ('login2', 'Александр', 'Новожилов', 'Я написал сервер для этого приложения', 'Хочешь научиться писать код на Java? Пиши мне на почту: sasha.n191@yandex.ru','http://localhost:8080/api/img/2-image.jpeg', 'Россия', 'Нижний Новгород', 10000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name, status, profile_descr, profile_picture, country, city, money, password)
values ('login3', 'Андрей', 'Гудков', 'Лентяй','Попробуйте заставить меня хоть что-то сделать', 'http://localhost:8080/api/img/3-image.jpeg', 'Россия', 'Нижний Новгород', 15, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login4', 'Христина', 'Мотыль', 'Самая вредная девченка в мире','Научите меня включать демку в дискорде','http://localhost:8080/api/img/4-image.jpeg', 'Россия', 'Нижний Новгород', 0, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status ,profile_descr, profile_picture, country, city, money, password)
values ('login5', 'Александр', 'Еланский', 'Я написал этот сайт!', 'Получаю зарплату в coins' ,'http://localhost:8080/api/img/5-image.jpeg', 'Россия', 'Нижний Новгород', 7520, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login6', 'Петр', 'Петров', 'Текст для статуса', 'Текст для описания профиля','http://localhost:8080/api/img/6-image.jpeg', 'Россия', 'Москва', 100, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login7', 'Богач', 'Богатеев', 'Слабо меня обогнать?', 'Как прекрасно быть самым богатым человеком в мире','http://localhost:8080/api/img/7-image.jpeg', 'Россия', 'Москва', 10000000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login8', 'Ляпис', 'Трубецкой', 'Лучший поэт в мире', 'Мою новую книгу можете найти на сайте www.stupid-book.com','http://localhost:8080/api/img/8-image.jpeg', 'Россия', 'Москва', 3335, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login9', 'Андрей', 'Мельниченко', 'Это просто лучшее приложение!', 'Путешествейте с туристической фирмой Спутник','http://localhost:8080/api/img/9-image.jpeg', 'Беларусь', 'Минск', 45000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login10', 'Тарас', 'Тарасов', 'Всю зарплату сюда закинул', 'Покупайте картошку в ООО Лучший картофель','http://localhost:8080/api/img/10-image.jpeg', 'Беларусь', 'Минск', 25000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login11', 'Денис', 'Агутин', 'Оказываю юридическую помощь', 'Нужен адвокат? Пиши в телегу @advakat228','http://localhost:8080/api/img/11-image.jpeg', 'Россия', 'Пермь', 6000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login12', 'Сергей', 'Воробьев', 'Заправляйтесь на заправках SeregaOil', 'На других заправках бензин разбавляют','http://localhost:8080/api/img/12-image.jpeg', 'Россия', 'Пермь', 7400, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login13', 'Артем', 'Бакшеев', 'Заправляйтесь на заправках ArtemOil', 'На других заправках бензин разбавляют','http://localhost:8080/api/img/13-image.jpeg', 'Россия', 'Пермь', 7500, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login14', 'Бакшей', 'Артемов', 'Заправляйтесь на заправках BashOil', 'На других заправках бензин разбавляют','http://localhost:8080/api/img/14-image.jpeg', 'Россия', 'Пермь', 7501, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');

INSERT INTO users (login, first_name, last_name,status, profile_descr, profile_picture, country, city, money, password)
values ('login15', 'Влад', 'Колпаков', 'Легкий заработок в интернете у меня в профиле', 'Онлайн казино LOH777 быстрые ставки, большие выигрыши','http://localhost:8080/api/img/15-image.jpeg', 'Россия', 'Магадан', 77000, '$2a$10$.BotI0.UfIymUOajsgB0rez7XlvPgzgdP38TtHSf.vxsxHrd4dmPi');


INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_USER');

INSERT INTO user_roles(user_id, role_id) VALUES (1,1);
INSERT INTO user_roles(user_id, role_id) VALUES (1,2);

INSERT INTO user_roles(user_id, role_id) VALUES (2,2);
INSERT INTO user_roles(user_id, role_id) VALUES (3,2);
INSERT INTO user_roles(user_id, role_id) VALUES (4,2);
INSERT INTO user_roles(user_id, role_id) VALUES (5,2);
INSERT INTO user_roles(user_id, role_id) VALUES (6,2);
INSERT INTO user_roles(user_id, role_id) VALUES (7,2);
INSERT INTO user_roles(user_id, role_id) VALUES (8,2);
INSERT INTO user_roles(user_id, role_id) VALUES (9,2);
INSERT INTO user_roles(user_id, role_id) VALUES (10,2);
INSERT INTO user_roles(user_id, role_id) VALUES (11,2);
INSERT INTO user_roles(user_id, role_id) VALUES (12,2);
INSERT INTO user_roles(user_id, role_id) VALUES (13,2);
INSERT INTO user_roles(user_id, role_id) VALUES (14,2);
INSERT INTO user_roles(user_id, role_id) VALUES (15,2);