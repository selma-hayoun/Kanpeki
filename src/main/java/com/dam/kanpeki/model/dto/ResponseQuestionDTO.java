package com.dam.kanpeki.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseQuestionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8900633378166476455L;

	@ApiModelProperty(notes = "Question id", example = "1", required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(notes = "Question statement", example = "友だちはしんせつですが、いつも　_______ です。", required = true)
	@NotBlank()
	@Size(max = 40)
	private String statement;

	@ApiModelProperty(notes = "Furigana version if necessary", example = "ともだちはしんせつですが、いつも　_______ です。", required = false)
	@Size(max = 40)
	private String help;

	@ApiModelProperty(notes = "Category id", example = "1", required = true)
	@NotNull
	private Long categoryId;

	@ApiModelProperty(notes = "Answers to the question", required = true)
	@NotNull
	@Size(min = 4, max = 4)
	private Set<AnswerDTO> answers;

}
