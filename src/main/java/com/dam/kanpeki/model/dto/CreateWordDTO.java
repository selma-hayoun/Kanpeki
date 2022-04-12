package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWordDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 2218386476425136746L;

	private String japanese;

	private String english;

	private String spanish;

	private String furigana;

	private String urlImage;

	private Long categoryId;

}
