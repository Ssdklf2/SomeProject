insert into users (id, active, date_of_created, email, name, password, phone_number, image_id)
values (1, true, '2022-10-24 18:20:27.336766', 'admin@mail.ru', 'admin',
        '$2a$08$8/uwl2qP45wq6VJ2SCHv.OFJ11GnYIHvfaZ6ymREU2kYoNqsRhBsG', 1, null);
insert into users (id, active, date_of_created, email, name, password, phone_number, image_id)
values (1, true, '2022-10-24 18:20:27.336766', 'user@mail.ru', 'user',
        '$2a$08$8/uwl2qP45wq6VJ2SCHv.OFJ11GnYIHvfaZ6ymREU2kYoNqsRhBsG', 1, null);

insert into messages(id, city, date_of_created, description, message_type, preview_image_id, price, title, user_id)
values (1, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 1000, 'NOKIA1', 1),
       (2, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'SELL', null, 1000, 'TV', 1),
       (3, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 165326, 'Товар1', 1),
       (4, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 213579, 'Товар2', 1),
       (5, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 946, 'Товар3', 1),
       (6, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 37486, 'Холодильник', 1),
       (7, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 2135, 'Шкаф', 1),
       (8, 'Novosibirsk', '2022-10-25 18:20:27.336766', 'desc', 'SELL', null, 9000, 'Велосипед', 1),
       (9, 'Peterburg', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 12345, 'Товар4', 1),
       (10, 'Peterburg', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 50, 'Светильник', 1),
       (11, 'Peterburg', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 999, 'Товар34', 1),
       (12, 'Peterburg', '2022-10-25 18:20:27.336766', 'desc', 'BUY', null, 1, 'Товар55', 1),
       (13, 'Kazan', '2022-10-25 18:20:27.336766', 'desc', 'SELL', null, 10000, 'PS5', 1),
       (14, 'Moscow', '2022-10-25 18:20:27.336766', 'desc', 'SELL', null, 1153, 'Принтер', 1);

