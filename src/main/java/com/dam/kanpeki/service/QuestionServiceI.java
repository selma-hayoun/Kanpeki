package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.dto.RequestQuestionDTO;
import com.dam.kanpeki.model.dto.ResponseQuestionDTO;

public interface QuestionServiceI {

	public List<ResponseQuestionDTO> findAllQuestions();

	public List<ResponseQuestionDTO> findByCategoryId(Long id);

//	public List<Question> findByStatementContaining(String qSate);

	public List<ResponseQuestionDTO> findQuestionsByMatcher(String qField);

	public Optional<ResponseQuestionDTO> findById(Long id);

	public ResponseQuestionDTO addQuestion(RequestQuestionDTO q);

	public void removeQuestionById(Long id);

	public ResponseQuestionDTO updateQuestion(RequestQuestionDTO q, Long id);

}
