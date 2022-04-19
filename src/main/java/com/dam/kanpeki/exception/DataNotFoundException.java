package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1793807580638829517L;

	public DataNotFoundException(String info) {
		super(KanpekiConstants.EXCEPTION_MSG_DATA_NOT_FOUND
				+ (info.isEmpty() ? KanpekiConstants.DOT_STRING : KanpekiConstants.SEMICOLON_STRING + info));
	}

}
