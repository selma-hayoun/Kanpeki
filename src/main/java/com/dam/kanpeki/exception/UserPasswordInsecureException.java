package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserPasswordInsecureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170213420629797508L;

	public UserPasswordInsecureException() {
		super(KanpekiConstants.USER_PASSWORD_INVALID_EX_STRING);
	}

}
