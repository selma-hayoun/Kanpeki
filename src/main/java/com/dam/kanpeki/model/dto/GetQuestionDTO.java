package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8900633378166476455L;

	private Long id;

	private String statement;

	private String help;

	private Long categoryId;

	private Set<AnswerDTO> answers;

}
