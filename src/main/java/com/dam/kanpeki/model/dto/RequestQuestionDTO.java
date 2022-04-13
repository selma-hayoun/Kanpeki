package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestQuestionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2501991143334633077L;

	@NotBlank(message = "Statement may not be null or empty")
	@Size(max = 40, message = "Statement must be between 1 and 40 characters long")
	private String statement;

	@Size(max = 40, message = "Furigana help must be less than 40 characters long")
	private String help;

	@NotNull(message = "CategoryId may not be null")
	private Long categoryId;

	@NotNull
	@Size(min = 4, max = 4, message = "Must be four options mandatorily")
	private Set<@NotNull @Valid AnswerDTO> answers;

}
