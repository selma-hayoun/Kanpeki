package com.dam.kanpeki.model;

import java.io.Serializable;

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
public class Answer implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9175365689647594705L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String answer;

	private String furigana;

	private boolean isCorrect;

	// FK con question
	private Long questionId;

}
