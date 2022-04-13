package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6554241705298199254L;

	private String unitName;

	private String categoryName;

	private Boolean isQuestion;

}
