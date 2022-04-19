package com.dam.kanpeki.exception;

import com.dam.kanpeki.utils.KanpekiConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidOperationOnCategoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2907683479199005719L;

	public InvalidOperationOnCategoryException(String info) {
		super(KanpekiConstants.EXCEPTION_MSG_INVALID_OPERATION_CATEGORY + info);
	}

}
