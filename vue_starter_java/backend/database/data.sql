-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;
/*
-- INSERT statements go here
INSERT INTO family
VALUES (DEFAULT, 'Quigley'),
(DEFAULT, 'Zilla'),
(DEFAULT, 'Hartmann'),
(DEFAULT, 'Tin'),
(DEFAULT, 'Myers');


INSERT INTO app_user
VALUES (DEFAULT, 'Quigleyr', 'password1', 'Parent', 'salt', 'Ryan', 'Quigley', (SELECT family_id FROM family WHERE family_name = 'Quigley')),
(DEFAULT, 'Quigleyc', 'password1', 'Child', 'salt', 'Charlotte', 'Quigley', (SELECT family_id FROM family WHERE family_name = 'Quigley')),
(DEFAULT, 'Zillaj', 'password1', 'Parent', 'salt', 'Joe', 'Zilla', (SELECT family_id FROM family WHERE family_name = 'Zilla')),
(DEFAULT, 'Zillae', 'password1', 'Child', 'salt', 'Elodie', 'Zilla', (SELECT family_id FROM family WHERE family_name = 'Zilla')),
(DEFAULT, 'Hartmannl', 'password1', 'Parent', 'salt', 'Leigh', 'Hartman', (SELECT family_id FROM family WHERE family_name = 'Hartmann')),
(DEFAULT, 'Tinl', 'password1', 'Parent', 'salt', 'Irina', 'Tin', (SELECT family_id FROM family WHERE family_name = 'Tin')),
(DEFAULT, 'Myersa', 'password1', 'Parent', 'salt', 'Adam', 'Myers', (SELECT family_id FROM family WHERE family_name = 'Myers'));


INSERT INTO friends 
VALUES ((SELECT id FROM app_user WHERE first_name = 'Ryan'), (SELECT id FROM app_user WHERE first_name = 'Joe')),
((SELECT id FROM app_user WHERE first_name = 'Joe'), (SELECT id FROM app_user WHERE first_name = 'Ryan')),
((SELECT id FROM app_user WHERE first_name = 'Joe'), (SELECT id FROM app_user WHERE first_name = 'Leigh')),
((SELECT id FROM app_user WHERE first_name = 'Joe'), (SELECT id FROM app_user WHERE first_name = 'Adam')),
((SELECT id FROM app_user WHERE first_name = 'Irina'), (SELECT id FROM app_user WHERE first_name = 'Leigh')),
((SELECT id FROM app_user WHERE first_name = 'Irina'), (SELECT id FROM app_user WHERE first_name = 'Joe')),
((SELECT id FROM app_user WHERE first_name = 'Adam'), (SELECT id FROM app_user WHERE first_name = 'Irina')),
((SELECT id FROM app_user WHERE first_name = 'Adam'), (SELECT id FROM app_user WHERE first_name = 'Leigh'));

INSERT INTO book
VALUES (DEFAULT, '12345', 'Book1', 'Author1'),
(DEFAULT, '23456', 'Book2', 'Author2'),
(DEFAULT, '34567', 'Book3', 'Author3'),
(DEFAULT, '45678', 'Book4', 'Author4'),
(DEFAULT, '56789', 'Book5', 'Author5');

INSERT INTO user_book
VALUES (DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Ryan'), (SELECT book_id FROM book WHERE isbn = '12345'), '30', '04-06-2020', 'Paperback'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Ryan'), (SELECT book_id FROM book WHERE isbn = '56789'), '1', '04-04-2020', 'Hardback'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Joe'), (SELECT book_id FROM book WHERE isbn = '12345'), '99', '04-01-2020', 'Paperback'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Joe'), (SELECT book_id FROM book WHERE isbn = '34567'), '300', '04-04-2020', 'Digital'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Leigh'), (SELECT book_id FROM book WHERE isbn = '12345'), '2', '03-30-2020', 'Paperback'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Irina'), (SELECT book_id FROM book WHERE isbn = '45678'), '400', '03-27-2020', 'Reader'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Irina'), (SELECT book_id FROM book WHERE isbn = '12345'), '286', '04-05-2020', 'Digital'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Irina'), (SELECT book_id FROM book WHERE isbn = '23456'), '487', '03-15-2020', 'Paperback'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Adam'), (SELECT book_id FROM book WHERE isbn = '56789'), '59', '04-02-2020', 'Paperback'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Ryan'), (SELECT book_id FROM book WHERE isbn = '23456'), '140', '04-03-2020', 'Reader'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Charlotte'), (SELECT book_id FROM book WHERE isbn = '23456'), '140', '04-03-2020', 'Listener'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Joe'), (SELECT book_id FROM book WHERE isbn = '56789'), '59', '04-02-2020', 'Reader'),
(DEFAULT, (SELECT id FROM app_user WHERE first_name = 'Elodie'), (SELECT book_id FROM book WHERE isbn = '56789'), '59', '04-02-2020', 'Listener');

INSERT INTO prize
VALUES (DEFAULT, 'Liquor', 'Gonna get you drunk', '120', 'Parent', '10', '03-01-2020', '06-30-2020'),
(DEFAULT, 'Snacks', 'Yum yum', '30', 'All', '10', '02-01-2020', '05-31-2020'),
(DEFAULT, 'Video Game', 'Play some FF7', '60', 'Child', '10', NULL, NULL),
(DEFAULT, 'Nap', 'Zzzzzzz', '240', 'All', '10', NULL, NULL),
(DEFAULT, 'Walk', 'Its getting nice out', '300', 'All', '10', '04-01-2020', '09-30-2020');

INSERT INTO user_prize
VALUES((SELECT prize_id FROM prize WHERE prize_name = 'Liquor'), (SELECT id FROM app_user WHERE first_name = 'Ryan')),
((SELECT prize_id FROM prize WHERE prize_name = 'Liquor'), (SELECT id FROM app_user WHERE first_name = 'Joe')),
((SELECT prize_id FROM prize WHERE prize_name = 'Liquor'), (SELECT id FROM app_user WHERE first_name = 'Leigh')),
((SELECT prize_id FROM prize WHERE prize_name = 'Liquor'), (SELECT id FROM app_user WHERE first_name = 'Irina')),
((SELECT prize_id FROM prize WHERE prize_name = 'Liquor'), (SELECT id FROM app_user WHERE first_name = 'Adam')),
((SELECT prize_id FROM prize WHERE prize_name = 'Snacks'), (SELECT id FROM app_user WHERE first_name = 'Leigh')),
((SELECT prize_id FROM prize WHERE prize_name = 'Snacks'), (SELECT id FROM app_user WHERE first_name = 'Ryan')),
((SELECT prize_id FROM prize WHERE prize_name = 'Video Game'), (SELECT id FROM app_user WHERE first_name = 'Joe')),
((SELECT prize_id FROM prize WHERE prize_name = 'Walk'), (SELECT id FROM app_user WHERE first_name = 'Irina')),
((SELECT prize_id FROM prize WHERE prize_name = 'Nap'), (SELECT id FROM app_user WHERE first_name = 'Adam'));
*/
COMMIT;