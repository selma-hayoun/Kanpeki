package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1793807580638829517L;

	public DataNotFoundException(String info) {
		super("Data not found" + (info.isEmpty() ? "." : " : " + info));
	}

}
