package com.dam.kanpeki.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.model.dto.RequestQuestionDTO;
import com.dam.kanpeki.model.dto.ResponseQuestionDTO;
import com.dam.kanpeki.service.QuestionServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("kanpeki/questions")
public class QuestionAnswerController {

	@Autowired
	private QuestionServiceI qService;

	@ApiOperation(value = "getQuestions", notes = "Get all questions from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = KanpekiConstants.EMPTY_STRING, produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseQuestionDTO>> getQuestions() {
		List<ResponseQuestionDTO> qList = qService.findAllQuestions();

		if (qList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(qList);
		}
	}

	@ApiOperation(value = "getQuestion", notes = "Get a question by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/question", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseQuestionDTO> getQuestion(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Question id", example = "1") Long id) {
		Optional<ResponseQuestionDTO> opQuestion = qService.findById(id);

		if (!opQuestion.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(opQuestion.get());
		}
	}

	@ApiOperation(value = "addNewQuestion", notes = "Create a new question")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class),
			@ApiResponse(code = 201, message = KanpekiConstants.CONTROLLER_MSG_201, response = ResponseQuestionDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/question", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseQuestionDTO> addNewQuestion(@Valid @RequestBody RequestQuestionDTO q) {

		return ResponseEntity.status(HttpStatus.CREATED).body(qService.addQuestion(q));

	}

	@ApiOperation(value = "deleteQuestion", notes = "Delete a single question by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class),
			@ApiResponse(code = 204, message = KanpekiConstants.CONTROLLER_MSG_204),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/question/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseQuestionDTO> deleteQuestion(
			@PathVariable("id") @ApiParam(name = "id", value = "Question id", example = "1") Long id) {
		Optional<ResponseQuestionDTO> opWord = qService.findById(id);

		if (!opWord.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			qService.removeQuestionById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateQuestion", notes = "Update the data from an existing question")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/question/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseQuestionDTO> updateQuestion(@Valid @RequestBody RequestQuestionDTO q,
			@PathVariable("id") @ApiParam(name = "id", value = "Question id", example = "1") Long id) {

		return ResponseEntity.ok(qService.updateQuestion(q, id));

	}

	@ApiOperation(value = "getShuffledQuestionsByCategory", notes = "Get all questions shuffled from a Category by category ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/question/shuffle", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseQuestionDTO>> getShuffledQuestionsByCategory(
			@RequestParam(name = "categoryId") @ApiParam(name = "categoryId", value = "Category id", example = "1") Long id) {
		List<ResponseQuestionDTO> qList = qService.findByCategoryId(id);
		Collections.shuffle(qList);

		if (qList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.DATA_NOT_FOUND_EX_QUESTIONS_BY_CATEGORY);
		} else {
			return ResponseEntity.ok(qList.stream().limit(10).collect(Collectors.toList()));// Limita a 10 preguntas
		}
	}

	@ApiOperation(value = "searchQuestions", notes = "Search questions by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseQuestionDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/question/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseQuestionDTO>> searchWords(
			@RequestParam(name = "qString") @ApiParam(name = "qString", value = "statement", example = "です") String qString) {
		List<ResponseQuestionDTO> qList = qService.findQuestionsByMatcher(qString);

		if (qList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.DATA_NOT_FOUND_EX_QUESTIONS_BY_STRING);
		} else {
			return ResponseEntity.ok(qList);
		}
	}

}
