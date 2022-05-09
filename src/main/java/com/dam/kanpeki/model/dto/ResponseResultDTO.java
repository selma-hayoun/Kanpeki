package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.dam.kanpeki.utils.KanpekiConstants;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResultDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 163330717493359419L;

	@ApiModelProperty(notes = KanpekiConstants.API_USER_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long userId;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@ApiModelProperty(notes = KanpekiConstants.API_RESULT_DATE_NOTES, example = KanpekiConstants.API_RESULT_DATE_EXAMPLE, required = true)
	private LocalDateTime resultDate;

	@ApiModelProperty(notes = KanpekiConstants.API_RESULT_SCORE_NOTES, example = KanpekiConstants.API_RESULT_SCORE_EXAMPLE, required = true)
	@Min(KanpekiConstants.MIN_SCORE_VALUE)
	@Max(KanpekiConstants.MAX_AGE_VALUE)
	private double score;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long categoryId;

}
