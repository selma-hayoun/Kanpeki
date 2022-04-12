package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetResultDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 163330717493359419L;

	private Long userId;

	private LocalDateTime resultDate;

	private double score;

	private Long categoryId;

}
