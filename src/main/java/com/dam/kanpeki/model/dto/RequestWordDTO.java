package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dam.kanpeki.utils.KanpekiConstants;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestWordDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 2218386476425136746L;

	@ApiModelProperty(notes = KanpekiConstants.API_WORD_JAPANESE_NOTES, example = KanpekiConstants.API_WORD_JAPANESE_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.WORD_JAPANESE_NOT_BLANK_MSG)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.WORD_JAPANESE_SIZE_MSG)
	private String japanese;

	@ApiModelProperty(notes = KanpekiConstants.API_WORD_ENGLISH_NOTES, example = KanpekiConstants.API_WORD_ENGLISH_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.WORD_ENGLISH_NOT_BLANK_MSG)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.WORD_ENGLISH_SIZE_MSG)
	private String english;

	@ApiModelProperty(notes = KanpekiConstants.API_WORD_SPANISH_NOTES, example = KanpekiConstants.API_WORD_SPANISH_EXAMPLE, required = true)
	@NotBlank(message = KanpekiConstants.WORD_SPANISH_NOT_BLANK_MSG)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.WORD_SPANISH_SIZE_MSG)
	private String spanish;

	@ApiModelProperty(notes = KanpekiConstants.API_WORD_FURIGANA_NOTES, example = KanpekiConstants.API_WORD_FURIGANA_EXAMPLE)
	@Size(max = KanpekiConstants.MAX_STRING_LENGTH_VALUE, message = KanpekiConstants.WORD_FURIGANA_SIZE_MSG)
	private String furigana;

	@ApiModelProperty(notes = KanpekiConstants.API_CATEGORY_ID_NOTES, example = KanpekiConstants.API_ID_EXAMPLE, required = true)
	@NotNull(message = KanpekiConstants.CATEGORY_ID_NOT_NULL_MSG)
	private Long categoryId;

	@ApiModelProperty(notes = KanpekiConstants.API_FILE_NOTES)
	private MultipartFile file;

}
