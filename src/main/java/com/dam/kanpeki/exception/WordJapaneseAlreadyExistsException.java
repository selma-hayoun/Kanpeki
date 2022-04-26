package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WordJapaneseAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8809012389601870163L;

	public WordJapaneseAlreadyExistsException() {
		super(KanpekiConstants.WORD_JAPANESE_EXISTS_EX_STRING);
	}

}
