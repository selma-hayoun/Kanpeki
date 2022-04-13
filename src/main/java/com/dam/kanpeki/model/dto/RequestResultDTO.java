package com.dam.kanpeki.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResultDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1999392315749197646L;

	private Long userId;

	@Min(0)
	@Max(10)
	private double score;

	private Long categoryId;

}
