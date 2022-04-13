package com.dam.kanpeki.exception;

public class StorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220293587053210771L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
