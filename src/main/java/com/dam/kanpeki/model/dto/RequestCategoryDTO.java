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
public class RequestCategoryDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6554241705298199254L;

	@ApiModelProperty(notes = "Unit name", example = "Lesson one", required = true)
	@NotBlank(message = "UnitName may not be null or empty")
	@Size(max = 40, message = "UnitName must be less than 40 characters long")
	private String unitName;

	@ApiModelProperty(notes = "Category name", example = "Family", required = true)
	@NotBlank(message = "CategoryName may not be null or empty")
	@Size(max = 40, message = "CategoryName must be less than 40 characters long")
	private String categoryName;

	@ApiModelProperty(notes = "True if a category for questions", example = "true", required = true)
	@NotNull(message = "IsQuestion may not be null")
	private Boolean isQuestion;

}
