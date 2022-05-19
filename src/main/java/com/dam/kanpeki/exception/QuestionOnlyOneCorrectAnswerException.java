package com.dam.kanpeki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dam.kanpeki.utils.KanpekiConstants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionOnlyOneCorrectAnswerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8814637543452753964L;

	public QuestionOnlyOneCorrectAnswerException() {
		super(KanpekiConstants.QUESTION_ONE_CORRECT_ANSWER_EX_STRING);
	}

}
