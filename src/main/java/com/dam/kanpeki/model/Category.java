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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", uniqueConstraints = { @UniqueConstraint(columnNames = { "unitName", "categoryName" }) })
public class Category implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6881853398103419465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String unitName;

	private String categoryName;

	private Boolean isQuestion;

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
