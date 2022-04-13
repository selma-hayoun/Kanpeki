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
public class ResponseWordDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 677198719677676418L;

	@ApiModelProperty(notes = "Word id", example = "1", required = true)
	@NotNull
	private Long id;

	@NotBlank
	@Size(max = 40)
	private String japanese;

	@NotBlank
	@Size(max = 40)
	private String english;

	@NotBlank
	@Size(max = 40)
	private String spanish;

	@Size(max = 40)
	private String furigana;

	private String urlImage;

}
