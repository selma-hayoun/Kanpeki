package com.dam.kanpeki.model.dto;

import java.io.Serializable;

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
public class RequestResultDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1999392315749197646L;

	@ApiModelProperty(notes = "User id", example = "1", required = true)
	@NotNull(message = "userId may not be null")
	private Long userId;

	@ApiModelProperty(notes = "Score reached in the test", example = "10", required = true)
	@Min(0)
	@Max(10)
	private double score;

	@ApiModelProperty(notes = "Category id", example = "1", required = true)
	@NotNull(message = "categoryId may not be null")
	private Long categoryId;

}
