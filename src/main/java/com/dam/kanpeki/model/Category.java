package com.dam.kanpeki.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6881853398103419465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String unitName;

	@Column(unique = true)
	private String categoryName;

	private boolean isQuestion;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Set<Word> words;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Set<Question> questions;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Set<Result> results;

}
