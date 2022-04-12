package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -5242609612927634971L;

	private String response;

	private String furigana;

	private boolean isCorrect;

}
