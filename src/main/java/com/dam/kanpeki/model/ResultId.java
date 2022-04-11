package com.dam.kanpeki.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Embeddable
public class ResultId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 671477109958708311L;

	public Long userId;

	@CreatedDate
	public LocalDateTime resultDate;

}