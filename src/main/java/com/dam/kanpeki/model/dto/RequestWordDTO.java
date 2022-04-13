package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@ApiModelProperty(notes = "Word in japanese", example = "母", required = true)
	@NotBlank(message = "Japanese may not be null or empty")
	@Size(max = 40, message = "Japanese must be less than 40 characters long")
	private String japanese;

	@ApiModelProperty(notes = "Word in english", example = "my mom", required = true)
	@NotBlank(message = "English may not be null or empty")
	@Size(max = 40, message = "English must be less than 40 characters long")
	private String english;

	@ApiModelProperty(notes = "Word in spanish", example = "mi mamá", required = true)
	@NotBlank(message = "Spanish may not be null or empty")
	@Size(max = 40, message = "Spanish must be less than 40 characters long")
	private String spanish;

	@ApiModelProperty(notes = "Word in furigana", example = "はは", required = false)
	@Size(max = 40, message = "Furigana help must be less than 40 characters long")
	private String furigana;

	@ApiModelProperty(notes = "Category id", example = "1", required = true)
	@NotNull(message = "CategoryId may not be null")
	private Long categoryId;

	@ApiModelProperty(notes = "Image file field for uploadling", required = false)
	private MultipartFile file;

}
