package com.dam.kanpeki.utils;

public class KanpekiConstants {

	private KanpekiConstants() {
	}

	/**
	 * General
	 */
	public static final String EMPTY_STRING = "";

	public static final String DOT_STRING = ".";

	public static final String DOUBLE_DOTS_STRING = "..";

	public static final String UNDERSCORE_STRING = "_";

	public static final String SEMICOLON_STRING = " : ";

	public static final String FILES_CONTENT_TYPE = "application/octet-stream";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss";

	public static final String TABLE_WORDS_NAME = " Words ";

	public static final String TABLE_QUESTIONS_NAME = " Questions ";

	public static final String TABLE_RESULTS_NAME = " Results ";

	/**
	 * Mappings
	 */
	public static final String MAP_CLIENT = "http://localhost:4200";

	public static final String MAP_GET_METHOD = "GET";

	public static final String MAP_POST_METHOD = "POST";

	public static final String MAP_PUT_METHOD = "PUT";

	public static final String MAP_DELETE_METHOD = "DELETE";

	/**
	 * API INFO
	 */
	public static final String API_INFO_TITLE = "GSFP DAM Project - KANPEKI";

	public static final String API_INFO_DESCRIPTION = "Project application for helping the revision of terms, vocabulary and other requirements of the Noken 5 level of Japanese";

	public static final String API_INFO_VERSION = "API 1.0";

	public static final String API_INFO_WILD_CARD = "#";

	public static final String API_INFO_CONTACT_NAME = "Selma Hayoun Caballero";

	public static final String API_INFO_CONTACT_URL = "https://es.linkedin.com/in/selma-problem-solving-lover";

	public static final String API_INFO_CONTACT_EMAIL = "selma.hayoun.caballero@gmail.com";

	public static final String API_INFO_LICENSE_NAME = "License";

	/**
	 * API MODEL EXAMPLES
	 */
	public static final String API_ANSWER_RESPONSE_NOTES = "Question response";

	public static final String API_ANSWER_RESPONSE_EXAMPLE = "犬";

	public static final String API_ANSWER_FURIGANA_NOTES = "Response in furigana";

	public static final String API_ANSWER_FURIGANA_EXAMPLE = "いぬ";

	public static final String API_ANSWER_ISCORRECT_NOTES = "True if is correct";

	public static final String API_BOOLEAN_PROPERTY_EXAMPLE = "true";

	public static final String API_CATEGORY_UNIT_NOTES = "Unit name";

	public static final String API_CATEGORY_UNIT_EXAMPLE = "Lesson one";

	public static final String API_CATEGORY_NAME_NOTES = "Category name";

	public static final String API_CATEGORY_NAME_EXAMPLE = "Family";

	public static final String API_CATEGORY_ISQUESTION_NOTES = "True if a category for questions";

	public static final String API_CATEGORY_ID_NOTES = "Category id";

	public static final String API_ID_EXAMPLE = "1";

	public static final String API_QUESTION_STATEMENT_NOTES = "Question statement";

	public static final String API_QUESTION_STATEMENT_EXAMPLE = "友だちはしんせつですが、いつも　_______ です。";

	public static final String API_QUESTION_HELP_NOTES = "Furigana version if necessary";

	public static final String API_QUESTION_HELP_EXAMPLE = "ともだちはしんせつですが、いつも　_______ です。";

	public static final String API_QUESTION_ANWERS_NOTES = "Answers to the question";

	public static final String API_QUESTION_ID_NOTES = "Question id";

	public static final String API_USER_ID_NOTES = "User id";

	public static final String API_RESULT_SCORE_NOTES = "Score reached in the test";

	public static final String API_RESULT_SCORE_EXAMPLE = "10";

	public static final String API_RESULT_DATE_NOTES = "Result date and time";

	public static final String API_RESULT_DATE_EXAMPLE = "2022-01-10T15:00:00";

	public static final String API_USER_EMAIL_NOTES = "User email";

	public static final String API_USER_EMAIL_EXAMPLE = "kanjilovers@kanpeki.com";

	public static final String API_USER_PASSWORD_NOTES = "User password";

	public static final String API_USER_PASSWORD_EXAMPLE = "C4c4hu3t3!!";

	public static final String API_USER_FULLNAME_NOTES = "User full name";

	public static final String API_USER_FULLNAME_EXAMPLE = "John Doe";

	public static final String API_USER_NICKNAME_NOTES = "User full name";

	public static final String API_USER_NICKNAME_EXAMPLE = "John Doe";

	public static final String API_FILE_NOTES = "Image file field for uploading";

	public static final String API_USER_BIRTHDAY_NOTES = "User birthday";

	public static final String API_USER_BIRTHDAY_EXAMPLE = "1989-12-31";

	public static final String API_USER_CITY_NOTES = "User city or town";

	public static final String API_USER_CITY_EXAMPLE = "Kyoto";

	public static final String API_USER_ROLES_NOTES = "User roles";

	public static final String API_USER_ROLES_EXAMPLE = "[\"USER\"]";

	public static final String API_USER_IMG_NOTES = "User image URL in our server";

	public static final String API_USER_CREATED_NOTES = "User creation date and time";

	public static final String API_USER_CREATED_EXAMPLE = "2022-04-13T18:39:39.505Z";

	public static final String API_USER_PASSWORD_CHANGE_NOTES = "Password last date and time of modification";

	public static final String API_USER_PASSWORD_CHANGE_EXAMPLE = "2022-04-13T18:39:39.505Z";

	public static final String API_WORD_JAPANESE_NOTES = "Word in japanese";

	public static final String API_WORD_JAPANESE_EXAMPLE = "母";

	public static final String API_WORD_ENGLISH_NOTES = "Word in english";

	public static final String API_WORD_ENGLISH_EXAMPLE = "my mom";

	public static final String API_WORD_SPANISH_NOTES = "Word in spanish";

	public static final String API_WORD_SPANISH_EXAMPLE = "mi mamá";

	public static final String API_WORD_FURIGANA_NOTES = "Word in furigana";

	public static final String API_WORD_FURIGANA_EXAMPLE = "はは";

	public static final String API_WORD_ID_NOTES = "Word id";

	/**
	 * Files
	 */
	public static final String FILES_CONTROLLER_INFO_FILE_TYPE = "Could not determine file type.";

	public static final String FILES_SERVE_FILE = "serveFile";

	/**
	 * Controllers API responses messages
	 */
	public static final String CONTROLLER_MSG_200 = "OK. Resources obtained correctly";

	public static final String CONTROLLER_MSG_201 = "OK. Created";

	public static final String CONTROLLER_MSG_204 = "OK. No Content";

	public static final String CONTROLLER_MSG_400 = "Bad request";

	public static final String CONTROLLER_MSG_401 = "Unauthorized";

	public static final String CONTROLLER_MSG_403 = "Forbidden";

	public static final String CONTROLLER_MSG_404 = "Not found";

	public static final String CONTROLLER_MSG_500 = "Unexpected error";

	/**
	 * ExampleMatcher fields
	 */
	public static final String CATEGORY_UNIT_NAME = "unitName";

	public static final String CATEGORY_CAT_NAME = "categoryName";

	public static final String QUESTION_STATEMENT_NAME = "statement";

	public static final String WORD_JAPANESE_NAME = "japanese";

	public static final String WORD_ENGLISH_NAME = "english";

	public static final String WORD_SPANISH_NAME = "spanish";

	public static final String USER_EMAIL_NAME = "email";

	public static final String USER_FULLNAME_NAME = "fullName";

	public static final String USER_NICKNAME_NAME = "nickname";

	/**
	 * Exceptions messages: controlled exceptions
	 */

	public static final String DATA_NOT_FOUND_EX_CATEGORIES = "No categories contain the string";

	public static final String DATA_NOT_FOUND_EX_QUESTIONS_BY_CATEGORY = "No questions registered in that category";

	public static final String DATA_NOT_FOUND_EX_QUESTIONS_BY_STRING = "No questions contain the string";

	public static final String DATA_NOT_FOUND_EX_USER_RESULTS = "Results not found from user: ";

	public static final String DATA_NOT_FOUND_EX_RESULTS_BY_DATES = "No results between %s and %s";

	public static final String DATA_NOT_FOUND_EX_USER_BY_STRING = "No users contain the string";

	public static final String PARAMETER_INCORRECT_EX_MSG = "Values provided are not correct";

	public static final String PARAMETER_INCORRECT_FORMAT_EX_DATES = "Dates format are incorrect. Correct pattern: yyyy-MM-dd";

	public static final String DATA_NOT_FOUND_EX_USERS_CREATED_BY_DATES = "No users created between %s and %s";

	public static final String DATA_NOT_FOUND_EX_USERS_BIRTHDAY_BY_DATES = "No birthdays between %s and %s";

	public static final String DATA_NOT_FOUND_EX_WORDS_BY_CATEGORY = "No words registered in that category";

	public static final String DATA_NOT_FOUND_EX_WORDS_BY_STRING = "No words contain the string";

	public static final String EX_DELIMITER_DEFAULT = " default message ";

	public static final String EXCEPTION_MSG_DATA_NOT_FOUND = "Data not found";

	public static final String EXCEPTION_MSG_INVALID_OPERATION_CATEGORY = "Category cannot be deleted. Binded with others ressources:";

	public static final String EX_STORAGE_EXCEPTION_NOT_INIT = "Could not initialize storage";

	public static final String EX_STORAGE_EXCEPTION_EMPTY_FILE = "Failed to store empty file ";

	public static final String EX_STORAGE_EXCEPTION_SECURITY_CHECK = "Cannot store file with relative path outside current directory ";

	public static final String EX_STORAGE_EXCEPTION_IOEX_STORE = "Failed to store file ";

	public static final String EX_STORAGE_EXCEPTION_IOEX_READ = "Failed to read file ";

	public static final String EX_STORAGE_FILE_NOT_FOUND_READ = "Could not read file: ";

	public static final String EX_STORAGE_EXCEPTION_IOEX_DELETE = "Error deleting a file";

	public static final String EX_USER_EMAIL_EXISTS_EXCEPTION = "An user with that email already exists";

	public static final String CATEGORY_EXISTS_EX_STRING = "Category with same Unit Name and Category Name already exists";

	public static final String USER_EMAIL_EXISTS_EX_STRING = "User with the given email already exists";

	public static final String USER_NICKNAME_EXISTS_EX_STRING = "User with the given nickname already exists";

	public static final String WORD_JAPANESE_EXISTS_EX_STRING = "Japanese word (japanese value) already exists";

	public static final String INVALID_REFERENCES_RESULT_EX_BOTH = "User id and Category id provided do not exist";

	public static final String INVALID_REFERENCES_RESULT_EX_USER_ID = "User id provided does not exist";

	public static final String INVALID_REFERENCES_RESULT_EX_CATEGORY_ID = "Category id provided does not exist";

	/**
	 * Exceptions messages: data validation
	 */
	public static final String ANSWER_RESPONSE_NOT_BLANK_MSG = "Response may not be null or empty";

	public static final String ANSWER_RESPONSE_SIZE_MSG = "Response must be between 1 and 40 characters long";

	public static final String ANSWER_FURIGANA_SIZE_MSG = "Furigana help must be less than 40 characters long";

	public static final String ANSWER_ISCORRECT_NOT_NULL_MSG = "IsCorrect may not be null";

	public static final String CATEGORY_UNIT_NOT_BLANK_MSG = "UnitName may not be null or empty";

	public static final String CATEGORY_UNIT_SIZE_MSG = "UnitName must be between 1 and 40 characters long";

	public static final String CATEGORY_NAME_NOT_BLANK_MSG = "CategoryName may not be null or empty";

	public static final String CATEGORY_NAME_SIZE_MSG = "CategoryName must be between 1 and 40 characters long";

	public static final String CATEGORY_ISQUESTION_NOT_NULL_MSG = "IsQuestion may not be null";

	public static final String QUESTION_STATEMENT_NOT_BLANK_MSG = "Statement may not be null or empty";

	public static final String QUESTION_STATEMENT_SIZE_MSG = "Statement must be between 1 and 40 characters long";

	public static final String QUESTION_HELP_SIZE_MSG = "Furigana must be between 1 and 40 characters long";

	public static final String CATEGORY_ID_NOT_NULL_MSG = "CategoryId may not be null";

	public static final String QUESTION_ANSWERS_SIZE_MSG = "Must be four different options mandatory";

	public static final String USER_ID_NOT_NULL_MSG = "userId may not be null";

	public static final String USER_EMAIL_NOT_BLANK_MSG = "Email may not be null or empty";

	public static final String USER_EMAIL_NOT_CORRECT_MSG = "Wrong email format";

	public static final String USER_PASSWORD_NOT_BLANK_MSG = "Password may not be null or empty";

	public static final String USER_PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

	public static final String USER_PASSWORD_PATTERN_MSG = "Password insecure";

	public static final String USER_NAME_NOT_BLANK_MSG = "FullName may not be null or empty";

	public static final String USER_NAME_SIZE_MSG = "FullName must be between 1 and 40 characters long";

	public static final String USER_NICKNAME_SIZE_MSG = "NickName must be between 1 and 40 characters long";

	public static final String USER_CITY_NOT_BLANK_MSG = "City may not be null or empty";

	public static final String USER_CITY_SIZE_MSG = "City must be less than 40 characters long";

	public static final String USER_ROLES_NOT_NULL = "Roles may not be null";

	public static final String USER_ROLES_SIZE_MSG = "Must be at least one role assigned mandatory";

	public static final String WORD_JAPANESE_NOT_BLANK_MSG = "Japanese may not be null or empty";

	public static final String WORD_JAPANESE_SIZE_MSG = "Japanese must be less than 40 characters long";

	public static final String WORD_ENGLISH_NOT_BLANK_MSG = "English may not be null or empty";

	public static final String WORD_ENGLISH_SIZE_MSG = "English must be less than 40 characters long";

	public static final String WORD_SPANISH_NOT_BLANK_MSG = "Spanish may not be null or empty";

	public static final String WORD_SPANISH_SIZE_MSG = "Spanish must be less than 40 characters long";

	public static final String WORD_FURIGANA_SIZE_MSG = "Furigana must be less than 40 characters long";

	/**
	 * Values
	 */
	public static final int MAX_AGE_VALUE = 3600;

	public static final int MAX_SCORE_VALUE = 10;

	public static final int MIN_SCORE_VALUE = 0;

	public static final int MAX_STRING_LENGTH_VALUE = 40;

	public static final int MIN_ROLES_VALUE = 1;

	/**
	 * Security
	 */

	public static final String SENSEI_ROLE = "ADMIN";

	public static final String GAKUSEI_ROLE = "USER";

	public static final String ALL_MAPPINGS = "/kanpeki/**";

	public static final String CATEGORY_MAPPINGS = "/kanpeki/categories/**";

	public static final String QUESTION_MAPPINGS = "/kanpeki/questions/**";

	public static final String RESULT_MAPPINGS = "/kanpeki/results/**";

	public static final String USER_MAPPINGS = "/kanpeki/users/**";

	public static final String USER_UPDATE_MAPPINGS = "/kanpeki/users/user/**";

	public static final String REGISTRATION_MAPPINGS = "/kanpeki/registers/**";

	public static final String WORD_MAPPINGS = "/kanpeki/words/**";

	public static final String SECURITY_RESOURCE_ID = "oauth2-resource";// Configuración servidor de autorización y
																		// recursos

}
