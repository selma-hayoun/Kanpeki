package com.dam.kanpeki.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Question implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7543984185289793199L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String statement;
	
	private String help;//Furigana
	
	//FK con categoría
	private Long category_id;

}
