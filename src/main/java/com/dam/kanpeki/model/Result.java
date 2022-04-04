package com.dam.kanpeki.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private double result;

	// FK con categoría
	private Long categoryId;

}

//Composite ID
@Embeddable
class ResultId implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1843659334823963068L;

	// FK con User
	protected Long userId;

//	@CreatedDate
//	private LocalDateTime createdAt;//dateResult

	@Temporal(TemporalType.TIMESTAMP)
	private Date resultDate;

}
