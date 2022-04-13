package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1782849004587666808L;

	private Long id;

	private String unitName;

	private String categoryName;

	private Boolean isQuestion;

}
