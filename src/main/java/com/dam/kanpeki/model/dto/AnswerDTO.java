package com.dam.kanpeki.model.dto;

import java.io.Serializable;

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
public class AnswerDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -5242609612927634971L;

	@ApiModelProperty(notes = KanpekiConstants.API_ANSWER_RESPONSE_NOTES, example = KanpekiConstants.API_ANSWER_RESPONSE_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.ANSWER_RESPONSE_NOT_BLANK_MSG)
	@Size(max = 40, message = KanpekiConstants.ANSWER_RESPONSE_SIZE_MSG)
	private String response;

	@ApiModelProperty(notes = KanpekiConstants.API_ANSWER_FURIGANA_NOTES, example = KanpekiConstants.API_ANSWER_FURIGANA_EXAMPLE)
	@Size(max = 40, message = KanpekiConstants.ANSWER_FURIGANA_SIZE_MSG)
	private String furigana;

	@ApiModelProperty(notes = KanpekiConstants.API_ANSWER_ISCORRECT_NOTES, example = KanpekiConstants.API_BOOLEAN_PROPERTY_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.ANSWER_ISCORRECT_NOT_NULL_MSG)
	private Boolean isCorrect;

}
