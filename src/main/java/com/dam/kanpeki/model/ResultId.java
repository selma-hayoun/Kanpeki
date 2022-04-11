package com.dam.kanpeki.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ResultId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 671477109958708311L;

	public Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	public Date resultDate;

}