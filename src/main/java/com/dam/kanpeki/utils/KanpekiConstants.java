package com.dam.kanpeki.utils;

public class KanpekiConstants {

	/**
	 * General
	 */
	public static final String EMPTY_STRING = "";

	public static final String FILES_CONTENT_TYPE = "application/octet-stream";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Files
	 */
	public static final String FILES_CONTROLLER_INFO_FILE_TYPE = "Could not determine file type.";

	/**
	 * Controllers api responses messages
	 */
	public static final String CONTROLLER_MSG_200 = "OK. Resources obtained correctly";

	public static final String CONTROLLER_MSG_400 = "Bad request";

	public static final String CONTROLLER_MSG_404 = "Not found";

	public static final String CONTROLLER_MSG_500 = "Unexpected error";

	/**
	 * Exceptions messages
	 */

	public static final String DATA_NOT_FOUND_EX_CATEGORIES = "No categories contain the string";

	public static final String DATA_NOT_FOUND_EX_QUESTIONS_BY_CATEGORY = "No questions registered in that category";

	public static final String DATA_NOT_FOUND_EX_QUESTIONS_BY_STRING = "No questions contain the string";

	public static final String DATA_NOT_FOUND_EX_USER_RESULTS = "Results not found from user: ";

	public static final String DATA_NOT_FOUND_EX_RESULTS_BY_DATES = "No results between %s and %s";

	public static final String DATA_NOT_FOUND_EX_USER_BY_STRING = "No users contain the string";

	public static final String PARAMETER_INCORRECT_FORMAT_EX_DATES = "Dates format are incorrect. Correct pattern: yyyy-MM-dd";

	public static final String DATA_NOT_FOUND_EX_USERS_CREATED_BY_DATES = "No users created between %s and %s";

	public static final String DATA_NOT_FOUND_EX_USERS_BIRTHDAY_BY_DATES = "No birthdays between %s and %s";

	public static final String DATA_NOT_FOUND_EX_WORDS_BY_CATEGORY = "No words registered in that category";

	public static final String DATA_NOT_FOUND_EX_WORDS_BY_STRING = "No words contain the string";

	private KanpekiConstants() {
	}

}
