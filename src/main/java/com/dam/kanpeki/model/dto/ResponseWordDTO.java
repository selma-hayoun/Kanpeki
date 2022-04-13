package com.dam.kanpeki.model.dto;

import java.io.Serializable;

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

	private Long id;

	private String japanese;

	private String english;

	private String spanish;

	private String furigana;

	private String urlImage;

}
