package com.dam.kanpeki.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.repository.QuestionRepository;
import com.dam.kanpeki.service.QuestionServiceI;

@Service
public class QuestionServiceImpl implements QuestionServiceI {

	@Autowired
	private QuestionRepository qRepo;

	@Override
	public List<Question> findAllQuestions() {
		return qRepo.findAll();
	}

	@Override
	public List<Question> findByCategoryId(Long id) {
		return qRepo.findByCategoryId(id);
	}

	@Override
	public List<Question> findByStatementContaining(String qSate) {
		return qRepo.findByStatementContaining(qSate);
	}

	@Override
	public Optional<Question> findById(Long id) {
		return qRepo.findById(id);
	}

	@Override
	public Question addQuestion(Question q) {
		return qRepo.save(q);
	}

	@Override
	public void removeQuestionById(Long id) {
		qRepo.deleteById(id);
	}

	@Override
	public void updateQuestion(Question q) {
		qRepo.save(q);
	}

}
