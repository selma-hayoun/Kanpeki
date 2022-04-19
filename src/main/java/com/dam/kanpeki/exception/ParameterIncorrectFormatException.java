package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterIncorrectFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 708018116495333173L;

	public ParameterIncorrectFormatException(String info) {
		super(KanpekiConstants.EXCEPTION_MSG_DATA_NOT_FOUND
				+ (info.isEmpty() ? KanpekiConstants.DOT_STRING : KanpekiConstants.SEMICOLON_STRING + info));
	}

}
