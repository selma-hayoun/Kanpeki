package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.dam.kanpeki.utils.KanpekiConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResultDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1999392315749197646L;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.USER_ID_NOT_NULL_MSG)
	private Long userId;

	@ApiModelProperty(notes = KanpekiConstants.API_RESULT_SCORE_NOTES, example = KanpekiConstants.API_RESULT_SCORE_EXAMPLE, required = true)
	@Min(KanpekiConstants.MIN_SCORE_VALUE)
	@Max(KanpekiConstants.MAX_AGE_VALUE)
	private double score;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ID_NOTES, example = KanpekiConstants.API_ID_ALT_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.CATEGORY_ID_NOT_NULL_MSG)
	private Long categoryId;

}
