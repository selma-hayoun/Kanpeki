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
public class ResponseWordDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 677198719677676418L;

	@ApiModelProperty(notes = KanpekiConstants.API_WORD_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull
	private Long id;

	@NotBlank
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String japanese;

	@NotBlank
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String english;

	@NotBlank
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String spanish;

	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE)
	private String furigana;

	private String urlImage;

}
