package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	@ApiModelProperty(notes = "User id", example = "2", required = true)
	@NotNull
	private Long userId;

	@ApiModelProperty(notes = "Result date and time", example = "2022-01-10T15:00:00", required = true)
	private LocalDateTime resultDate;

	@ApiModelProperty(notes = "Score reached in the test", example = "5", required = true)
	@Min(0)
	@Max(10)
	private double score;

	@ApiModelProperty(notes = "Category id", example = "2", required = true)
	@NotNull
	private Long categoryId;

}
