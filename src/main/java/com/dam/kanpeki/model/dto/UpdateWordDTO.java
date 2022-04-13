package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWordDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7082509432808365937L;

	private String japanese;

	private String english;

	private String spanish;

	private String furigana;

	private MultipartFile file;

}
