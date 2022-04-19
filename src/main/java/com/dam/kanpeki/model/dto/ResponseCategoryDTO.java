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
public class ResponseCategoryDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1782849004587666808L;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_UNIT_NOTES, example = KanpekiConstants.API_CATEGORY_UNIT_EXAMPLE, required = true)
	@NotBlank()
	@Size(max = 40)
	private String unitName;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_NAME_NOTES, example = KanpekiConstants.API_CATEGORY_NAME_EXAMPLE, required = true)
	@NotBlank()
	@Size(max = 40)
	private String categoryName;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ISQUESTION_NOTES, example = KanpekiConstants.API_BOOLEAN_PROPERTY_EXAMPLE, required = true)
	@NotNull
	private Boolean isQuestion;

}
