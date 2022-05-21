package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
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
public class RequestQuestionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2501991143334633077L;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_STATEMENT_NOTES, example = KanpekiConstants.API_QUESTION_STATEMENT_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.QUESTION_STATEMENT_NOT_BLANK_MSG)
	@Size(max = 250, message = KanpekiConstants.QUESTION_STATEMENT_SIZE_MSG)
	private String statement;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_HELP_NOTES, example = KanpekiConstants.API_QUESTION_HELP_EXAMPLE)
	@Size(max = 250, message = KanpekiConstants.QUESTION_HELP_SIZE_MSG)
	private String help;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.CATEGORY_ID_NOT_NULL_MSG)
	private Long categoryId;

	@ApiModelProperty(notes = KanpekiConstants.API_QUESTION_ANWERS_NOTES, required = true)
	@NotNull
	@Size(min = 4, max = 4, message = KanpekiConstants.QUESTION_ANSWERS_SIZE_MSG)
	private Set<@NotNull @Valid AnswerDTO> answers;

}
