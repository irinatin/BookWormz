-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

INSERT INTO family
VALUES (DEFAULT, 'Test Family 1'),
(DEFAULT, 'Test Family 2');


INSERT INTO users
VALUES (DEFAULT, 'TestAdult1', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'user'),
(DEFAULT, 'TestChild1', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'child'),
(DEFAULT, 'TestAdult2', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'user');


INSERT INTO user_info 
VALUES (DEFAULT, (SELECT id FROM users WHERE username = 'TestAdult1'), 'Test1', 'Adult1', (SELECT family_id FROM family WHERE family_name = 'Test Family 1')),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestChild1'), 'Test1', 'Child1', (SELECT family_id FROM family WHERE family_name = 'Test Family 1')),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestAdult2'), 'Test1', 'Adult2', (SELECT family_id FROM family WHERE family_name = 'Test Family 2'));

INSERT INTO book
VALUES (DEFAULT, 'Test Book 1', 'Author 1', (SELECT family_id FROM family WHERE family_name = 'Test Family 1')),
(DEFAULT, 'Test Book 2', 'Author 2', (SELECT family_id FROM family WHERE family_name = 'Test Family 1')),
(DEFAULT, 'Test Book 3', 'Author 3', (SELECT family_id FROM family WHERE family_name = 'Test Family 1')),
(DEFAULT, 'Test Book 4', 'Author 4', (SELECT family_id FROM family WHERE family_name = 'Test Family 2')),
(DEFAULT, 'Test Book 5', 'Author 5', (SELECT family_id FROM family WHERE family_name = 'Test Family 2')),
(DEFAULT, 'Test Book 6', 'Author 6', (SELECT family_id FROM family WHERE family_name = 'Test Family 2'));


INSERT INTO user_book
VALUES (DEFAULT, (SELECT id FROM users WHERE username = 'TestAdult1'), (SELECT book_id FROM book WHERE title = 'Test Book 1'), '100', '2020-04-10', 'Digital/Kindle', false),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestAdult1'), (SELECT book_id FROM book WHERE title = 'Test Book 2'), '60', '2020-04-09', 'Paperback', false),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestChild1'), (SELECT book_id FROM book WHERE title = 'Test Book 3'), '200', '2020-04-11', 'Read-Aloud (Reader)', true),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestChild1'), (SELECT book_id FROM book WHERE title = 'Test Book 4'), '30', '2020-04-07', 'Read-Aloud (Listener)', false),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestAdult2'), (SELECT book_id FROM book WHERE title = 'Test Book 5'), '120', '2020-04-08', 'Digital/Kindle', true),
(DEFAULT, (SELECT id FROM users WHERE username = 'TestAdult2'), (SELECT book_id FROM book WHERE title = 'Test Book 6'), '150', '2020-04-10', 'Paperback', false);

INSERT INTO prize
VALUES (DEFAULT, 'Adult Prize 1', 'Test Prize 1', '120', 'user', '10', '2020-01-01', '2020-12-31'),
(DEFAULT, 'Child Prize 1', 'Test Prize 2', '60', 'child', '10', '2020-06-30', '12-31-2020'),
(DEFAULT, 'Combo Prize 1', 'Test Prize 3', '30', 'all', '10', '2020-01-01', '2020-05-01'),
(DEFAULT, 'Adult Prize 2', 'Test Prize 4', '150', 'user', '10', '2020-04-01', '2020-04-30'),
(DEFAULT, 'Child Prize 2', 'Test Prize 5', '200', 'child', '10', '2020-03-01', '2020-08-30');

INSERT INTO user_prize
VALUES((SELECT prize_id FROM prize WHERE prize_name = 'Adult Prize 1'), (SELECT id FROM users WHERE username = 'TestAdult1')), 
((SELECT prize_id FROM prize WHERE prize_name = 'Adult Prize 2'), (SELECT id FROM users WHERE username = 'TestAdult1')), 
((SELECT prize_id FROM prize WHERE prize_name = 'Combo Prize 1'), (SELECT id FROM users WHERE username = 'TestAdult1')),
((SELECT prize_id FROM prize WHERE prize_name = 'Child Prize 1'), (SELECT id FROM users WHERE username = 'TestChild1')), 
((SELECT prize_id FROM prize WHERE prize_name = 'Child Prize 2'), (SELECT id FROM users WHERE username = 'TestChild1')), 
((SELECT prize_id FROM prize WHERE prize_name = 'Combo Prize 1'), (SELECT id FROM users WHERE username = 'TestChild1')),
((SELECT prize_id FROM prize WHERE prize_name = 'Adult Prize 1'), (SELECT id FROM users WHERE username = 'TestAdult2')), 
((SELECT prize_id FROM prize WHERE prize_name = 'Adult Prize 2'), (SELECT id FROM users WHERE username = 'TestAdult2')), 
((SELECT prize_id FROM prize WHERE prize_name = 'Combo Prize 1'), (SELECT id FROM users WHERE username = 'TestAdult2'));


COMMIT;