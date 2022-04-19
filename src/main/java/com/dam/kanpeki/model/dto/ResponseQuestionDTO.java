package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dam.kanpeki.utils.KanpekiConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseQuestionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8900633378166476455L;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_STATEMENT_NOTES, example = KanpekiConstants.API_QUESTION_STATEMENT_EXAMPLE, required = true)
	@NotBlank()
	@Size(max = 40)
	private String statement;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_HELP_NOTES, example = KanpekiConstants.API_QUESTION_HELP_EXAMPLE)
	@Size(max = 40)
	private String help;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long categoryId;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_ANWERS_NOTES, required = true)
	@NotNull
	@Size(min = 4, max = 4)
	private Set<AnswerDTO> answers;

}
