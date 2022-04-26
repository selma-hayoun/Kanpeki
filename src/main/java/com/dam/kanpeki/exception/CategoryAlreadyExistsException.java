package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2449592773859587705L;

	public CategoryAlreadyExistsException() {
		super(KanpekiConstants.CATEGORY_EXISTS_EX_STRING);
	}

}
