package com.dam.kanpeki.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidFKReferencesException;
import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.model.dto.RequestQuestionDTO;
import com.dam.kanpeki.model.dto.ResponseQuestionDTO;
import com.dam.kanpeki.model.dto.mapper.QuestionAnswerDTOMapperStruct;
import com.dam.kanpeki.repository.QuestionRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.service.QuestionServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

@Service
public class QuestionServiceImpl implements QuestionServiceI {

	@Autowired
	protected QuestionRepository qRepo;

	@Autowired
	protected CategoryServiceI catService;

	@Autowired
	protected QuestionAnswerDTOMapperStruct mapper;

	@Override
	public List<ResponseQuestionDTO> findAllQuestions() {
		List<Question> qList = qRepo.findAll();
		return mapper.toQuestionDTOList(qList.stream());
	}

	@Override
	public List<ResponseQuestionDTO> findByCategoryId(Long id) {
		return mapper.toQuestionDTOList(qRepo.findByCategoryId(id).stream());
	}

//	@Override
//	public List<Question> findByStatementContaining(String qSate) {
//		return qRepo.findByStatementContaining(qSate);
//	}

	@Override
	public Optional<ResponseQuestionDTO> findById(Long id) {
		Optional<Question> opQuestion = qRepo.findById(id);
		if (!opQuestion.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return Optional.of(mapper.toQuestionDTO(opQuestion.get()));
		}
	}

	@Override
	public ResponseQuestionDTO addQuestion(RequestQuestionDTO q) {
		// Verificamos si la categoría existe
		try {
			catService.findById(q.getCategoryId());
		} catch (DataNotFoundException ex) {
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID);
		}

		return mapper.toQuestionDTO(qRepo.save(mapper.requestQuestionDTOtoQuestion(q)));
	}

	@Override
	public void removeQuestionById(Long id) {
		qRepo.deleteById(id);
	}

	@Override
	public ResponseQuestionDTO updateQuestion(RequestQuestionDTO q, Long id) {

		// Verificamos si la categoría existe
		try {
			catService.findById(q.getCategoryId());
		} catch (DataNotFoundException ex) {
			throw new InvalidFKReferencesException(KanpekiConstants.INVALID_REFERENCES_RESULT_EX_CATEGORY_ID);
		}

		Question mappedQ = mapper.requestQuestionDTOtoQuestion(q);

		Question mappedQUpdated = qRepo.findById(id).map(newQ -> {
			newQ.setStatement(mappedQ.getStatement());
			newQ.setHelp(mappedQ.getHelp());
			newQ.setCategoryId(mappedQ.getCategoryId());
			newQ.setAnswers(mappedQ.getAnswers());
			qRepo.save(newQ);
			return newQ;
		}).orElseThrow(() -> new DataNotFoundException(KanpekiConstants.EMPTY_STRING));

		return mapper.toQuestionDTO(mappedQUpdated);
	}

	@Override
	public List<ResponseQuestionDTO> findQuestionsByMatcher(String qField) {
		Question q = new Question();
		q.setStatement(qField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny().withMatcher(
				KanpekiConstants.QUESTION_STATEMENT_NAME,
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Question> qExample = Example.of(q, customExMatcher);

		return mapper.toQuestionDTOList(qRepo.findAll(qExample).stream());
	}

}
