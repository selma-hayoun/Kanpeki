package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotBlank(message = "Response may not be null or empty")
	@Size(max = 40, message = "Response must be between 1 and 40 characters long")
	private String response;

	@Size(max = 40, message = "Furigana help must be less than 40 characters long")
	private String furigana;

	@NotNull(message = "IsCorrect may not be null")
	private Boolean isCorrect;

}
