package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Question;

public interface QuestionServiceI {

	public List<Question> findAllQuestions();

	public List<Question> findByCategoryId(Long id);

	public List<Question> findByStatementContaining(String qSate);

	public List<Question> findQuestionsByMatcher(String qField);

	public Optional<Question> findById(Long id);

	public Question addQuestion(Question q);

	public void removeQuestionById(Long id);

	public void updateQuestion(Question q);

}
