package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateResultDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1999392315749197646L;

	private Long userId;

	private double score;

	private Long categoryId;

}
