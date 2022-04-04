package com.dam.kanpeki.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7543984185289793199L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String statement;

	private String help;// Furigana

	// FK con categoría
	private Long categoryId;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionId")
	private Set<Answer> answers;

}
