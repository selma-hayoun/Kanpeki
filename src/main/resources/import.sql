/*Table Category*/
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(1, 'Lesson 1', 'Family', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(2, 'Lesson 1', 'Vocabulary', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(3, 'Lesson 2', 'Animals', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(4, 'Lesson 2', 'Vocabulary', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(5, 'Lesson 3', 'Food', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(6, 'Lesson 3', 'Vocabulary', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(7, 'Lesson 3', 'Grammar', 1);

/*Table words*/
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(1, '母', 'はは', 'mom', 'mamá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(2, '母1', 'はは', 'mom', 'mamá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(3, '母2', 'はは', 'mom', 'mamá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(4, '母3', 'はは', 'mom', 'mamá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(5, '母4', 'はは', 'mom', 'mamá', '', 1);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(6, '犬', 'いぬ', 'dog', 'perro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(7, '犬1', 'いぬ', 'dog', 'perro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(8, '犬2', 'いぬ', 'dog', 'perro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(9, '犬3', 'いぬ', 'dog', 'perro', '', 3);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(10, 'にんじん', 'にんじん', 'carrot', 'zanahoria', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(11, 'にんじん1', 'にんじん', 'carrot', 'zanahoria', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(12, 'にんじん2', 'にんじん', 'carrot', 'zanahoria', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(13, 'にんじん3', 'にんじん', 'carrot', 'zanahoria', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(14, 'にんじん4', 'にんじん', 'carrot', 'zanahoria', '', 5);

/*Table Question*/
INSERT INTO questions(id, statement, help, category_id) VALUES(1, '_______ 、えいがをみにいきませんか？', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(2, '友だちはしんせつですが、いつも　_______ です。', 'ともだちはしんせつですが、いつも　_______ です。', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(3, '_______ 、えいがをみにいきませんか？', '', 2);

INSERT INTO questions(id, statement, help, category_id) VALUES(4, '_______ 、えいがをみにいきませんか？', '', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(5, '友だちはしんせつですが、いつも　_______ です。', 'ともだちはしんせつですが、いつも　_______ です。', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(6, '_______ 、えいがをみにいきませんか？', '', 4);

INSERT INTO questions(id, statement, help, category_id) VALUES(7, '_______ 、えいがをみにいきませんか？', '', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(8, '_______ 、えいがをみにいきませんか？', '', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(9, '_______ 、えいがをみにいきませんか？', '', 7);

/*Table answers*/
/*Question 1*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(1, 'ゆうべ', '', 0, 1);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(2, 'きのう', '', 0, 1);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(3, 'あした', '', 1, 1);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(4, 'おととい', '', 0, 1);
/*Question 2*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(5, 'うれしい', '', 0, 2);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(6, 'いそがしい', '', 1, 2);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(7, 'むずかしい', '', 0, 2);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(8, 'やさしい', '', 0, 2);
/*Question 3*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(9, 'ゆうべ', '', 0, 3);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(10, 'きのう', '', 0, 3);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(11, 'あした', '', 1, 3);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(12, 'おととい', '', 0, 3);
/*Question 4*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(13, 'ゆうべ', '', 0, 4);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(14, 'きのう', '', 0, 4);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(15, 'あした', '', 1, 4);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(16, 'おととい', '', 0, 4);
/*Question 5*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(17, 'うれしい', '', 0,  5);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(18, 'いそがしい', '', 1, 5);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(19, 'むずかしい', '', 0, 5);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(20, 'やさしい', '', 0, 5);
/*Question 6*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(21, 'ゆうべ', '', 0, 6);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(22, 'きのう', '', 0, 6);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(23, 'あした', '', 1, 6);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(24, 'おととい', '', 0, 6);
/*Question 7*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(25, 'ゆうべ', '', 0, 7);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(26, 'きのう', '', 0, 7);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(27, 'あした', '', 1, 7);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(28, 'おととい', '', 0, 7);
/*Question 8*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(29, 'ゆうべ', '', 0, 8);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(30, 'きのう', '', 0, 8);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(31, 'あした', '', 1, 8);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(32, 'おととい', '', 0, 8);
/*Question 9*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(33, 'ゆうべ', '', 0, 9);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(34, 'きのう', '', 0, 9);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(35, 'あした', '', 1, 9);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(36, 'おととい', '', 0, 9);

/*Table Users*/
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(1, '', '2022-04-01', 'Kyoto', '2022-04-01', 'selma.hayoun.caballero@gmail.com', 'admin', '2022-04-01', 'admin', 'admin');

INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(2, '', '1989-12-31', 'Córdoba', '2000-01-01', 'aaaaa@gmail.com', 'Marisol Ruiz Cabrales', '2022-04-01', 'KanjiAditc', 'rucam');
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(3, '', '1990-10-28', 'Madrid', '2020-04-01', 'bbbbb@gmail.com', 'Lucas Martinez Cuenca', '2022-04-01', 'Sakura', 'macul');
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(4, '', '2001-05-01', 'Salamanca', '2020-04-01', 'ccccc@gmail.com', 'Paloma De la Mata Lunero', '2022-04-01', 'Hokkaido', 'delup');

/*Table User_roles*/
INSERT INTO user_roles(user_id, roles) VALUES(1, 'ADMIN');
INSERT INTO user_roles(user_id, roles) VALUES(1, 'USER');
INSERT INTO user_roles(user_id, roles) VALUES(2, 'USER');
INSERT INTO user_roles(user_id, roles) VALUES(3, 'USER');
INSERT INTO user_roles(user_id, roles) VALUES(4, 'USER');

/*Table results*/
/*User 2*/
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-10T15:00:00', 2, 2, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-10T17:00:00', 2, 4, 7);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-12T00:00:00', 2, 6, 8);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-10', 2, 2, 8);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-11', 2, 4, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-12', 2, 6, 6);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-10', 2, 2, 10);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-11', 2, 4, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-12', 2, 6, 5);
/*User 3*/
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-10', 3, 2, 3);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-11', 3, 4, 2);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-10', 3, 6, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-12', 3, 2, 6);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-13', 3, 4, 6);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-16', 3, 6, 7);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-15', 3, 4, 8);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-16', 3, 6, 8);
/*User 4*/
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-15', 4, 2, 10);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-16', 4, 4, 9);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-18', 4, 6, 8);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-19', 4, 2, 10);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-10', 4, 4, 1);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-12', 4, 6, 2);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-15', 4, 2, 4);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-15', 4, 6, 10);


