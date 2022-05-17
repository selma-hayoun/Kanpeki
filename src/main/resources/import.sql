/*TABLE oauth_client_details*/
drop table if exists oauth_client_details;
create table oauth_client_details (client_id VARCHAR(255) PRIMARY KEY, resource_ids VARCHAR(255), client_secret VARCHAR(255), scope VARCHAR(255), authorized_grant_types VARCHAR(255), web_server_redirect_uri VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additional_information VARCHAR(4096), autoapprove VARCHAR(255));

/*TABLE oauth_client_details*/
drop table if exists oauth_client_token;
create table oauth_client_token (token_id VARCHAR(255), token LONGVARBINARY, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255));

/*TABLE oauth_access_token*/
drop table if exists oauth_access_token;
create table oauth_access_token (token_id VARCHAR(255), token LONGVARBINARY, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255), authentication LONGVARBINARY, refresh_token VARCHAR(255));
 
 /*TABLE oauth_refresh_token*/
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (token_id VARCHAR(255), token LONGVARBINARY, authentication LONGVARBINARY);

/*TABLE oauth_code*/
drop table if exists oauth_code;
create table oauth_code (code VARCHAR(255), authentication LONGVARBINARY);
 
 /*TABLE oauth_approvals*/
drop table if exists oauth_approvals;
create table oauth_approvals (userId VARCHAR(255), clientId VARCHAR(255), scope VARCHAR(255), status VARCHAR(10), expiresAt TIMESTAMP, lastModifiedAt TIMESTAMP);

/*TABLE ClientDetails*/
drop table if exists ClientDetails;
create table ClientDetails (appId VARCHAR(255) PRIMARY KEY, resourceIds VARCHAR(255), appSecret VARCHAR(255), scope VARCHAR(255), grantTypes VARCHAR(255), redirectUrl VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additionalInformation VARCHAR(4096), autoApproveScopes VARCHAR(255));


/*IMPORT DUMMY DATA*/
/*Table Category*/
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(1, 'Lesson 1', 'Family', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(2, 'Lesson 1', 'Vocabulary & Kanji', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(3, 'Lesson 2', 'Animals', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(4, 'Lesson 2', 'Grammar', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(5, 'Lesson 3', 'Day of the Week', 0);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(6, 'Lesson 3', 'Vocabulary & Kanji', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(7, 'Lesson 3', 'Grammar', 1);
INSERT INTO categories(id, unit_name, category_name, is_question) VALUES(8, 'Lesson 1', 'Numbers', 0);

/*Table words*/
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(1, '家ぞく', 'かぞく', 'family', 'familia', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(2, 'りょうしん', 'りょうしん', 'parents', 'padres', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(3, '父', 'ちち', 'my dad', 'mi papá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(4, '母', 'はは', 'my mom', 'mi mamá', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(5, '姉', 'あね', 'my older sister', 'mi hermana mayor', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(6, '妹', 'いもうと', 'my younger sister', 'mi hermana menor', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(7, '弟', 'おとうと', 'my younger brother', 'mi hermano menor', '', 1);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(8, '兄', 'あに', 'my older brother', 'mi hermano mayor', '', 1);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(9, '犬', 'いぬ', 'dog', 'perro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(10, '鳥', 'とり', 'bird', 'pájaro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(11, 'ヘビ', 'ヘビ', 'snake', 'serpiente', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(12, '馬', 'うま', 'horse', 'caballo', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(13, '狐', 'きつね', 'fox', 'zorro', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(14, '動物', 'どうぶつ', 'animal', 'animal', '', 3);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(15, '牛', 'うし', 'cow', 'vaca', '', 3);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(16, '日よう日', 'にちようび', 'sunday', 'domingo', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(17, '月曜日', 'げつようび', 'monday', 'lunes', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(18, '火曜日', 'かようび', 'tuesday', 'martes', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(19, '水曜日', 'すいよび', 'wednesday', 'miércoles', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(20, '木曜日', 'もくようび', 'thursday', 'jueves', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(21, '金曜日', 'きんようび', 'friday', 'viernes', '', 5);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(22, '土曜日', 'どようび', 'saturday', 'sábado', '', 5);

INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(23, '一', 'いち', 'one', 'uno', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(24, '二', 'に', 'two', 'dos', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(25, '三', 'さん', 'three', 'tres', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(26, '四', 'し / よん', 'four', 'cuatro', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(27, '五', 'ご', 'five', 'cinco', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(28, '六', 'ろく', 'six', 'seis', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(29, '七', 'なな / しち', 'seven', 'siete', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(30, '八', 'はち', 'eight', 'ocho', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(31, '九', 'きゅう / く', 'nine', 'nueve', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(32, '十', 'じゅう', 'ten', 'diez', '', 8);
INSERT INTO words(id, japanese, furigana, english, spanish, url_image, category_id) VALUES(33, 'れい', 'ゼロ', 'zero', 'cero', '', 8);

/*Table Question*/
INSERT INTO questions(id, statement, help, category_id) VALUES(1, '大人が 一人と 子どもが 二人います。', '大人 − おとな', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(2, 'あの 人は ジムさんの お父さんです。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(3, 'あのおとこのこは だれですか。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(4, '今日 は ははの たん生日です。', 'きょうは ははの たんじょうびです。', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(21, 'あのやまはふじさんです。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(22, 'これはスイスの時計です。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(23, '今日はいいてんきです。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(24, 'ポケットはズボンの後ろにあります。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(25, 'これはわたしのぱそこんです。', '', 2);
INSERT INTO questions(id, statement, help, category_id) VALUES(26, 'あれはなんですか。', '', 2);

INSERT INTO questions(id, statement, help, category_id) VALUES(5, 'A 「毎日いえで （     ） べんきょうしますか。」 B 「わたちは いえでは べんきょうしません。」', '', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(6, 'A 「このかんじの いみを 教えてください。」 B 「（      ） かんじですか？。」', '', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(7, '昨日、ノート _____  _____  __＊__  _____を 2こ 買いました。', 'きのう、ノート _____  _____  __＊__  _____を にこ かいました。', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(34, 'まいにちしんぶん　_______ 読みます。', 'まいにちしんぶん　_______ よみます。', 4);
INSERT INTO questions(id, statement, help, category_id) VALUES(35, '田中さんは　_______ 来ていませんよ。', 'たなかさんは　_______ きていませんよ。', 4);

INSERT INTO questions(id, statement, help, category_id) VALUES(8, '私の 前の アパートは （      ）せまかったです。', 'わたしの まえの アパートは （      ）せまかったです。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(9, 'トム 「りんさんは いつ しゅくだいを しますか。」 りん 「あさ、 します。 おきて、 あさごはんを （      ）から します。」', '', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(10, '毎日、 おふろに 入ります。（      ）、ねます。', 'まいにち、 おふろに はいります。（      ）、ねます。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(27, '学校は家から （       ）ですから、たいへんです。', 'がっこうはうちから （       ）ですから、たいへんです。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(28, 'これは （        ）ですが、いい薬です。', 'これは （        ）ですが、いいくすりです。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(29, '（        ）おいしいくだものですね。', '', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(30, 'わたしはからいたべものがすきじゃありません。', '__の ぶんと だいたい おなじ いみの ぶんは どれですか。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(31, 'このみちはあまりひろくないです。', '__の ぶんと だいたい おなじ いみの ぶんは どれですか。', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(32, 'にもつはここに（        ）ください。', '', 7);
INSERT INTO questions(id, statement, help, category_id) VALUES(33, '子どもが（         ）いますから、しずかにしてください。', '子ども − こども', 7);

INSERT INTO questions(id, statement, help, category_id) VALUES(11, '_______ 、映画をみにいきませんか？', '_______ 、えいがをみにいきませんか？', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(12, 'わたしはいつも　_______ をききながらべんきょうします。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(13, 'わたしのすきなのみものは　_______ です。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(14, 'わたしはじてんしゃを　_______ もっています。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(15, 'この　_______ じしょはだれのですか？', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(16, 'ちちはことし80さいですが、_______ です。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(17, 'あめがふっています。みんなかさを　_______ います。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(18, 'がくせいたちはきょうしつでやまだせんせいににほんごを　_______ います。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(19, 'やまださんはあかいぼうしを　_______ います。', '', 6);
INSERT INTO questions(id, statement, help, category_id) VALUES(20, 'やまださんはたいていおふろにはいって、_______ ねます。', '', 6);


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
/*Question 11*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(41, 'ゆうべ', '', 0, 11);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(42, 'きのう', '', 0, 11);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(43, 'あした', '', 1, 11);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(44, 'おととい', '', 0, 11);
/*Question 12*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(45, 'ぺん', '', 0, 12);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(46, 'ラジオ', '', 1, 12);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(47, 'テーブル', '', 0, 12);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(48, 'ストーブ', '', 0, 12);
/*Question 13*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(49, 'おかし', '', 0, 13);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(50, 'こうちゃ', '', 1, 13);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(51, 'みかん', '', 0, 13);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(52, 'ねこ', '', 0, 13);
/*Question 14*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(53, 'にだい', '', 1, 14);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(54, 'にさつ', '', 0, 14);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(55, 'にほん', '', 0, 14);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(56, 'にまい', '', 0, 14);
/*Question 15*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(57, 'ほそい', '', 0, 15);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(58, 'まるい', '', 0, 15);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(59, 'みじかい', '', 0, 15);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(60, 'ちいさい', '', 1, 15);
/*Question 16*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(61, 'げんき', '', 1, 16);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(62, 'けっこう', '', 0, 16);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(63, 'ざんねん', '', 0, 16);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(64, 'かんたん', '', 0, 16);
/*Question 17*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(65, 'あけて', '', 0, 17);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(66, 'あげて', '', 0, 17);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(67, 'さして', '', 1, 17);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(68, 'つけて', '', 0, 17);
/*Question 18*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(69, 'ならって', '', 1, 18);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(70, 'つくって', '', 0, 18);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(71, 'おぼえて', '', 0, 18);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(72, 'べんきょうして', '', 0, 18);
/*Question 19*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(73, 'ならって', '', 0, 19);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(74, 'つくって', '', 0, 19);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(75, 'おぼえて', '', 0, 19);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(76, 'べんきょうして', '', 4, 19);
/*Question 20*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(77, 'ならって', '', 0, 20);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(78, 'つくって', '', 1, 20);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(79, 'おぼえて', '', 0, 20);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(80, 'べんきょうして', '', 0, 20);
/*Question 21*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(81, 'やま − 中', '', 0, 21);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(82, 'やま − 人', '', 0, 21);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(83, 'やま − 山', '', 1, 21);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(84, 'やま − 国', '', 0, 21);
/*Question 22*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(85, '時計 − じけい', '', 0, 22);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(86, '時計 − とけい', '', 1, 22);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(87, '時計 − じかん', '', 0, 22);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(88, '時計 − とかい', '', 0, 22);
/*Question 23*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(89, '今日 − きょう', '', 1, 23);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(90, '今日 − きゅう', '', 0, 23);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(91, '今日 − こにち', '', 0, 23);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(92, '今日 − いまにち', '', 0, 23);
/*Question 24*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(93, '後ろ − あしろ', '', 0, 24);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(94, '後ろ − うしろ', '', 1, 24);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(95, '後ろ − おしろ', '', 0, 24);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(96, '後ろ − ごうろ', '', 0, 24);
/*Question 25*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(97, 'パンコン', '', 0, 25);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(98, 'パンコソ', '', 0, 25);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(99, 'パソコソ', '', 0, 25);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(100, 'パソコン', '', 1, 25);
/*Question 27*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(101, 'とおい', '', 1, 27);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(102, 'おもい', '', 0, 27);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(103, 'ちかい', '', 0, 27);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(104, 'むずかしい', '', 0, 27);
/*Question 28*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(105, 'すき', '', 0, 28);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(106, 'ひま', '', 0, 28);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(107, 'にがい', '', 1, 28);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(108, 'さむい', '', 0, 28);
/*Question 29*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(109, 'あつくて', '', 0, 29);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(110, 'あまくて', '', 1, 29);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(111, 'おもくて', '', 0, 29);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(112, 'あたたかくて', '', 0, 29);
/*Question 30*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(113, 'わたしはにがいたべものがきらいです。', '', 0, 30);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(114, 'わたしはすきなたべものがありません。', '', 0, 30);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(115, 'わたしはからいたべものがきらいです。', '', 1, 30);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(116, 'わたしはきらいなたべものがありません。', '', 0, 30);
/*Question 31*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(117, 'このみちはすこしせまいです。', '', 1, 31);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(118, 'このみちはとてもせまいです。', '', 0, 31);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(119, 'このみちはすこしわるいです。', '', 0, 31);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(120, 'このみちはとてもわるいです。', '', 0, 31);
/*Question 32*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(121, 'おきて', '', 0, 32);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(122, 'おいて', '', 1, 32);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(123, 'ねて', '', 0, 32);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(124, 'すって', '', 0, 32);
/*Question 33*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(125, 'あそんど', '', 0, 33);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(126, 'おきて', '', 0, 33);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(127, 'ねて', '', 1, 33);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(128, 'あびて', '', 0, 33);
/*Question 26*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(129, '向', '', 0, 26);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(130, '何', '', 1, 26);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(131, '伺', '', 0, 26);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(132, '同', '', 0, 26);
/*Question 34*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(133, 'へ', '', 0, 34);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(134, 'を', '', 1, 34);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(135, 'に', '', 0, 34);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(136, 'が', '', 0, 34);
/*Question 35*/
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(137, 'もう', '', 0, 35);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(138, 'だけ', '', 0, 35);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(139, 'まだ', '', 1, 35);
INSERT INTO answers(id, response, furigana, is_correct, question_id) VALUES(140, 'まで', '', 0, 35);

/*Table Users*/
/*ADMIN - C4c4hu3t3!! */
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(1, '', '2022-04-01', 'Kyoto', '2022-04-01', 'selma.hayoun.caballero@gmail.com', 'admin', '2022-04-01', 'admin', '$2a$10$QxHQlcNgqf4.94D/7Vratuw1q/GeKBQAVKn3rmEGt3Pw652MJzkC6');
/*TEST1 - C4c4hu3t3$$ */
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(2, '', '1989-12-31', 'Córdoba', '2000-01-01', 'aaaaa@gmail.com', 'Marisol Ruiz Cabrales', '2022-04-01', 'KanjiAditc', '$2a$10$T5/lAd9vNZlYNIXcRhp.Zu6DbOVAg06o7lymdfCvAnx2aTzAqXC1S');
/*TEST2 - C4c4hu3t3&& */
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(3, '', '1990-10-28', 'Madrid', '2020-04-01', 'bbbbb@gmail.com', 'Lucas Martinez Cuenca', '2022-04-01', 'Sakura', '$2a$10$Ddwvpaay7TzGtsjsEx5SauLWndOLQSxnUpqDSmrG.cyjYMaqFBeVK');
/*TEST3 - C4c4hu3t3** */
INSERT INTO users(id, url_image, birthday, city, created_at, email, full_name, last_password_change_at, nickname, password) VALUES(4, '', '2001-05-01', 'Salamanca', '2020-04-01', 'ccccc@gmail.com', 'Paloma De la Mata Lunero', '2022-04-01', 'Hokkaido', '$2a$10$gvtnJsGaBcBa8MjwcmIvAO.602xPRbv6qADZ2XfYUvljREL6M25im');

/*Table User_roles*/
INSERT INTO user_roles(user_id, roles) VALUES(1, 'ADMIN');
/*INSERT INTO user_roles(user_id, roles) VALUES(1, 'USER');*/
/*Se ha eliminado el multi-rol dado que la parte cliente no ha sido capaz de gestionarlo*/
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
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-15', 2, 7, 8);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-10', 2, 2, 10);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-11', 2, 4, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-12', 2, 6, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-15', 2, 7, 6);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-05', 2, 2, 9);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-06', 2, 4, 7);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-10', 2, 6, 6);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-11', 2, 7, 8);
/*User 3*/
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-10', 3, 2, 3);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-11', 3, 4, 2);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-15', 3, 7, 4);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-10', 3, 6, 5);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-12', 3, 2, 6);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-13', 3, 4, 6);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-16', 3, 6, 7);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-20', 3, 7, 5);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-15', 3, 4, 8);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-16', 3, 6, 8);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-20', 3, 7, 9);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-05', 3, 4, 10);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-08', 3, 6, 9);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-09', 3, 7, 7);
/*User 4*/
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-15', 4, 2, 10);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-16', 4, 4, 9);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-18', 4, 6, 8);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-19', 4, 2, 10);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-01-20', 4, 7, 8);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-10', 4, 4, 1);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-12', 4, 6, 2);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-15', 4, 2, 4);
INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-02-24', 4, 7, 3);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-03-15', 4, 6, 10);

INSERT INTO results(result_date, user_id, category_id, score) VALUES('2022-04-15', 4, 7, 7);

