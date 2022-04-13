package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCategoryDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1782849004587666808L;

	@ApiModelProperty(notes = "Category id", example = "1", required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(notes = "Unit name", example = "Lesson one", required = true)
	@NotBlank()
	@Size(max = 40)
	private String unitName;

	@ApiModelProperty(notes = "Category name", example = "Family", required = true)
	@NotBlank()
	@Size(max = 40)
	private String categoryName;

	@ApiModelProperty(notes = "True if a category for questions", example = "true", required = true)
	@NotNull
	private Boolean isQuestion;

}
