package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterIncorrectFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 708018116495333173L;

	public ParameterIncorrectFormatException(String info) {
		super("Data not found" + (info.isEmpty() ? "." : " : " + info));
	}

}
