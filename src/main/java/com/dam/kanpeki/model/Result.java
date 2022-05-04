package com.dam.kanpeki.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8905651416974962387L;

	@EmbeddedId
	private ResultId id;

	private double score;

	// FK con categoría
	private Long categoryId;

}
