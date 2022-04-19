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
public class RequestCategoryDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6554241705298199254L;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_UNIT_NOTES, example = KanpekiConstants.API_CATEGORY_UNIT_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.CATEGORY_UNIT_NOT_BLANK_MSG)
	@Size(max = 40, message = KanpekiConstants.CATEGORY_UNIT_SIZE_MSG)
	private String unitName;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_NAME_NOTES, example = KanpekiConstants.API_CATEGORY_NAME_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.CATEGORY_NAME_NOT_BLANK_MSG)
	@Size(max = 40, message = KanpekiConstants.CATEGORY_NAME_SIZE_MSG)
	private String categoryName;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ISQUESTION_NOTES, example = KanpekiConstants.API_BOOLEAN_PROPERTY_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.CATEGORY_ISQUESTION_NOT_NULL_MSG)
	private Boolean isQuestion;

}
