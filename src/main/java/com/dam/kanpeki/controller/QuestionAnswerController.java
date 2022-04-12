package com.dam.kanpeki.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.service.QuestionServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("questions")
public class QuestionAnswerController {

	@Autowired
	private QuestionServiceI qService;

	@ApiOperation(value = "getQuestions", notes = "Get all questions from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Question>> getQuestions() {
		List<Question> qList = qService.findAllQuestions();

		if (qList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No questions registered");
		} else {
			return ResponseEntity.ok(qList);
		}
	}

	@ApiOperation(value = "getQuestion", notes = "Get a question by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Question> getQuestion(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Question id", example = "6") Long id) {
		Optional<Question> opQuestion = qService.findById(id);

		if (!opQuestion.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
		} else {
			return ResponseEntity.ok(opQuestion.get());
		}
	}

	@ApiOperation(value = "addNewQuestion", notes = "Create a new question")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Question> addNewQuestion(@Valid @RequestBody Question q) {

		return ResponseEntity.status(HttpStatus.CREATED).body(qService.addQuestion(q));

	}

	@ApiOperation(value = "deleteQuestion", notes = "Delete a single question by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<Question> deleteQuestion(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Question id", example = "6") Long id) {
		Optional<Question> opWord = qService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
		} else {
			qService.removeQuestionById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateQuestion", notes = "Update the data from an existing question")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<Question> updateQuestion(@Valid @RequestBody Question q,
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Question id", example = "6") Long id) {

		return qService.findById(id).map(newQ -> {
			newQ.setStatement(q.getStatement());
			newQ.setHelp(q.getHelp());
			newQ.setCategoryId(q.getCategoryId());
			newQ.setAnswers(q.getAnswers());
			qService.updateQuestion(newQ);
			return ResponseEntity.ok(newQ);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));

	}

	@ApiOperation(value = "getShuffledQuestionsByCategory", notes = "Get all questions shuffled from a Category by category ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question/shuffle/{categoryId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Question>> getShuffledQuestionsByCategory(
			@RequestParam(name = "categoryId") @ApiParam(name = "categoryId", value = "Category id", example = "3") Long id) {
		List<Question> qList = qService.findByCategoryId(id);
		Collections.shuffle(qList);

		if (qList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No questions registered in that category");
		} else {
			return ResponseEntity.ok(qList);
		}
	}

	@ApiOperation(value = "searchQuestions", notes = "Search questions by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Question.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/question/{qString}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Question>> searchWords(
			@RequestParam(name = "qString") @ApiParam(name = "qString", value = "statement", example = "私") String qString) {
		List<Question> qList = qService.findByStatementContaining(qString);

		if (qList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No questions contain the string");
		} else {
			return ResponseEntity.ok(qList);
		}
	}

}
