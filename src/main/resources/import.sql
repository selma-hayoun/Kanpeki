/*Table Category*/
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(1, 'Lesson 1', 'Family', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(2, 'Lesson 1', 'Vocabulary & Kanji', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(3, 'Lesson 2', 'Animals', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(4, 'Lesson 2', 'Grammar', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(5, 'Lesson 3', 'Day of the Week', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(6, 'Lesson 3', 'Vocabulary & Kanji', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(7, 'Lesson 3', 'Grammar', 1);

/*Table words*/
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(1, '家ぞく', 'かぞく', 'family', 'familia', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(2, 'りょうしん', 'りょうしん', 'parents', 'padres', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(3, '父', 'ちち', 'my dad', 'mi papá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(4, '母', 'はは', 'my mom', 'mi mamá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(5, '姉', 'あね', 'my older sister', 'mi hermana mayor', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(5, '妹', 'いもうと', 'my younger sister', 'mi hermana menor', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(5, '弟', 'おとうと', 'my younger brother', 'mi hermano menor', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(5, '兄', 'あに', 'my older brother', 'mi hermano mayor', '', 1);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(6, '犬', 'いぬ', 'dog', 'perro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(7, '鳥', 'とり', 'bird', 'pájaro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(8, 'ヘビ', 'ヘビ', 'snake', 'serpiente', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(9, '馬', 'うま', 'horse', 'caballo', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(9, '狐', 'きつね', 'fox', 'zorro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(6, '動物', 'どうぶつ', 'animal', 'animal', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(6, '牛', 'うし', 'cow', 'vaca', '', 3);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(10, '日よう日', 'にちようび', 'sunday', 'domingo', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(11, '月曜日', 'げつようび', 'monday', 'lunes', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(12, '火曜日', 'かようび', 'tuesday', 'martes', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(13, '水曜日', 'すいよび', 'wednesday', 'miércoles', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(14, '木曜日', 'もくようび', 'thursday', 'jueves', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(15, '金曜日', 'きんようび', 'friday', 'viernes', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(16, '土曜日', 'どようび', 'saturday', 'sábado', '', 5);

/*Table Question*/
INSERT INTO questions(id, statement, help, category_id) VALUES(1, '大人が 一人と 子どもが 二人います。', '大人 − おとな', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(2, 'あの 人は ジムさんの お父さんです。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(3, 'あのおとこのこは だれですか。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(4, '今日 は ははの たん生日です。', 'きょうは ははの たんじょうびです。', 2);

INSERT INTO questions(id, statement, help, category_id) VALUES(5, 'A 「毎日いえで （     ） べんきょうしますか。」 B 「わたちは いえでは べんきょうしません。」', '', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(6, 'A 「このかんじの いみを 教えてください。」 B 「（      ） かんじですか？。」', '', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(7, '昨日、ノート _____  _____  __＊__  _____を 2こ 買いました。', 'きのう、ノート _____  _____  __＊__  _____を にこ かいました。', 4);

INSERT INTO questions(id, statement, help, category_id) VALUES(8, '私の 前の アパートは （      ）せまかったです。', 'わたしの まえの アパートは （      ）せまかったです。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(9, 'トム 「りんさんは いつ しゅくだいを しますか。」 りん 「あさ、 します。 おきて、 あさごはんを （      ）から します。」', '', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(10, '毎日、 おふろに 入ります。（      ）、ねます。', 'まいにち、 おふろに はいります。（      ）、ねます。', 7);

/*Table answers*/
/*Question 1*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(1, 'ひとり', '', 1, 1);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(2, 'ひたり', '', 0, 1);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(3, 'ふとり', '', 0, 1);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(4, 'ふたり', '', 0, 1);
/*Question 2*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(5, 'おとさん', '', 0, 2);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(6, 'おどさん', '', 0, 2);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(7, 'おとおさん', '', 0, 2);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(8, 'おとうさん', '', 1, 2);
/*Question 3*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(9, '女の子', '', 0, 3);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(10, '男の子', '', 1, 3);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(11, '女の人', '', 0, 3);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(12, '男の人', '', 0, 3);
/*Question 4*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(13, '母', '', 1, 4);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(14, '田', '', 0, 4);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(15, '毎', '', 0, 4);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(16, '海', '', 0, 4);
/*Question 5*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(17, 'どのくらい', '', 1,  5);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(18, 'どんなくらい', '', 0, 5);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(19, 'どこで', '', 0, 5);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(20, 'どちらで', '', 0, 5);
/*Question 6*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(21, 'どれ', '', 0, 6);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(22, 'なに', '', 0, 6);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(23, 'どの', '', 1, 6);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(24, 'どっち', '', 0, 6);
/*Question 7*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(25, 'を', '', 0, 7);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(26, '３さつ', '', 0, 7);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(27, 'けしゴム', '', 0, 7);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(28, 'と', '', 1, 7);
/*Question 8*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(29, '古いで', 'ふるいで', 0, 8);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(30, '古くて', 'ふるくて', 1, 8);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(31, '古かったと', 'ふるかったと', 0, 8);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(32, '古いでしたと', 'ふるいでしたと', 0, 8);
/*Question 9*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(33, '食べる', 'たべる', 0, 9);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(34, '食べた', 'たべた', 0, 9);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(35, '食べて', 'たべて', 1, 9);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(36, '食べない', 'たべない', 0, 9);
/*Question 10*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(37, 'と', '', 0, 10);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(38, 'また', '', 0, 10);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(39, 'そしてから', '', 0, 10);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(40, 'それから', '', 1, 10);


/*Table Users*/
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(1, '', '2022-04-01', 'Kyoto', '2022-04-01', 'selma.hayoun.caballero@gmail.com', 'admin', '2022-04-01', 'admin', 'C4c4hu3t3$$');

INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(2, '', '1989-12-31', 'Córdoba', '2000-01-01', 'aaaaa@gmail.com', 'Marisol Ruiz Cabrales', '2022-04-01', 'KanjiAditc', 'C4c4hu3t3$$');
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(3, '', '1990-10-28', 'Madrid', '2020-04-01', 'bbbbb@gmail.com', 'Lucas Martinez Cuenca', '2022-04-01', 'Sakura', 'C4c4hu3t3$$');
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(4, '', '2001-05-01', 'Salamanca', '2020-04-01', 'ccccc@gmail.com', 'Paloma De la Mata Lunero', '2022-04-01', 'Hokkaido', 'C4c4hu3t3$$');

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


