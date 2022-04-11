package com.dam.kanpeki.model.custom;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultPerCategoryData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7484198983497404031L;

	private Long categoryId;

	private double numResults;

	private double avgResults;

}
