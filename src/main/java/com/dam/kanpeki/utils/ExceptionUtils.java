package com.dam.kanpeki.utils;

import org.springframework.stereotype.Component;

@Component
public class ExceptionUtils {

	private ExceptionUtils() {
	}

	/**
	 * Método auxiliar para el tratamiento del texto de las excepciones
	 *
	 * @param ex
	 * @return
	 */
	public static String getDefaultMsg(String ex) {

		String delimeterStr = KanpekiConstants.EX_DELIMITER_DEFAULT;
		String defaultMsg;

		if (ex.lastIndexOf(delimeterStr) != -1) {
			try {
				String temp = ex;
				defaultMsg = temp
						.substring(temp.indexOf(delimeterStr, temp.indexOf(delimeterStr) + 1) + 18, temp.length() - 3)
						.trim();
			} catch (Exception e) {
				defaultMsg = ex;
			}
			return defaultMsg;
		} else {
			return ex;
		}

	}
}
