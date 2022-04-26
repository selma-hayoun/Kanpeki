package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNicknameAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3729489548048649542L;

	public UserNicknameAlreadyExistsException() {
		super(KanpekiConstants.USER_NICKNAME_EXISTS_EX_STRING);
	}

}
