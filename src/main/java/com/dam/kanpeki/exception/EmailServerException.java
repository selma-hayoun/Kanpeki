package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8902990407713446628L;

	public EmailServerException() {
		super(KanpekiConstants.EMAIL_SERVICE_EX);
	}

}
