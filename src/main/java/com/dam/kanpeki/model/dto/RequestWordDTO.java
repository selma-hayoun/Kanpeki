package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

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

	@NotBlank(message = "Japanese may not be null or empty")
	@Size(max = 40, message = "Japanese must be less than 40 characters long")
	private String japanese;

	@NotBlank(message = "English may not be null or empty")
	@Size(max = 40, message = "English must be less than 40 characters long")
	private String english;

	@NotBlank(message = "Spanish may not be null or empty")
	@Size(max = 40, message = "Spanish must be less than 40 characters long")
	private String spanish;

	@Size(max = 40, message = "Furigana help must be less than 40 characters long")
	private String furigana;

	@NotNull(message = "CategoryId may not be null")
	private Long categoryId;

	private MultipartFile file;

}
