package com.dam.kanpeki.controller;

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

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.service.ResultServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("results")
public class ResultController {

	@Autowired
	private ResultServiceI rService;

	@ApiOperation(value = "getResults", notes = "Get all results from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Result.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Result>> getResults() {
		List<Result> rList = rService.findAllResults();

		if (rList != null && rList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results registered");
		} else {
			return ResponseEntity.ok(rList);
		}
	}

	@ApiOperation(value = "getResultsByUser", notes = "Get all results from a user ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Result.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Result> getWord(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "6") Long id) {

		List<Result> rList = rService.findAllResults();

		if (rList != null && rList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results registered");
		} else {
			return ResponseEntity.ok(rList);
		}
	}

	@ApiOperation(value = "addNewWord", notes = "Create a new result")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Result.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Result> addNewWord(@Valid @RequestBody Result r) {

		return ResponseEntity.status(HttpStatus.CREATED).body(rService.addResult(r));

	}

	@ApiOperation(value = "deleteWord", notes = "Delete a single result by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Result.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<Result> deleteWord(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Result id", example = "23") Long id) {
		Optional<Result> opWord = rService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");
		} else {
			rService.removeWordById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateWord", notes = "Update the data from an existing result")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Result.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<Result> updateWord(@Valid @RequestBody Result r,
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Result id", example = "23") Long id) {

		return rService.findById(id).map(newW -> {
			newW.setJapanese(r.getJapanese());
			newW.setEnglish(r.getEnglish());
			newW.setSpanish(r.getSpanish());
			newW.setFurigana(r.getFurigana());
			newW.setUrlImage(r.getUrlImage());
			newW.setCategoryId(r.getCategoryId());
			rService.updateWord(newW);
			return ResponseEntity.ok(newW);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found"));

	}

//	@ApiOperation(value = "searchWords", notes = "Search results by string")
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Result.class, responseContainer = "List"),
//			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
//			@ApiResponse(code = 500, message = "Unexpected error") })
//	@RequestMapping(value = "/result/{wString}", produces = { "application/json" }, method = RequestMethod.GET)
//	public ResponseEntity<List<Result>> searchWords(
//			@RequestParam(name = "wString") @ApiParam(name = "wString", value = "japanese, english or spanish", example = "dog") String wString) {
//		List<Result> rList = rService.findWordsByMatcher(wString);
//
//		if (rList != null && rList.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results contain the string");
//		} else {
//			return ResponseEntity.ok(rList);
//		}
//	}
}
