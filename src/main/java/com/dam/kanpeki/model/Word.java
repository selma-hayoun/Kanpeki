package com.dam.kanpeki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Word implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3205344804607092882L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String japanese;

	private String english;

	private String spanish;

	private String furigana;

	private String URLimage;

	// FK con categoría
	private Long categoryId;

}
