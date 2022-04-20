package com.dam.kanpeki.utils;

import com.dam.kanpeki.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

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
		String delimiterStr = KanpekiConstants.EX_DELIMITER_DEFAULT;
		String defaultMsg;

		if (ex.lastIndexOf(delimiterStr) != -1) {
			try {
				String temp = ex;
				defaultMsg = temp
						.substring(temp.indexOf(delimiterStr, temp.indexOf(delimiterStr) + 1) + 18, temp.length() - 3)
						.trim();
			} catch (Exception e) {
				defaultMsg = ex;
			}
			return defaultMsg;
		} else {
			return ex;
		}
	}

	public static ApiError getCustomApiError(BindException ex){

		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + KanpekiConstants.SEMICOLON_STRING + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + KanpekiConstants.SEMICOLON_STRING + error.getDefaultMessage());
		}

		return new ApiError(HttpStatus.BAD_REQUEST, "TOTAL ERRORS: " + ex.getErrorCount() + " - TARGET: " + ex.getBindingResult().getTarget(), errors);

	}

}
