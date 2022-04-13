package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Set;

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

	private String statement;

	private String help;

	private Long categoryId;

	private Set<AnswerDTO> answers;

}
