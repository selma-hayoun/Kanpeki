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

import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.service.WordServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("words")
public class WordController {

	@Autowired
	private WordServiceI wService;

	@ApiOperation(value = "getWords", notes = "Get all words from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Word>> getWords() {
		List<Word> wList = wService.findAllWords();

		if (wList != null && wList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words registered");
		} else {
			return ResponseEntity.ok(wList);
		}
	}

	@ApiOperation(value = "getWord", notes = "Get a word by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Word> getWord(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Word id", example = "34") Long id) {
		Optional<Word> opWord = wService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Word not found");
		} else {
			return ResponseEntity.ok(opWord.get());
		}
	}

	@ApiOperation(value = "addNewWord", notes = "Create a new word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Word> addNewWord(@Valid @RequestBody Word w) {

		return ResponseEntity.status(HttpStatus.CREATED).body(wService.addWord(w));

	}

	@ApiOperation(value = "deleteWord", notes = "Delete a single word by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<Word> deleteWord(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Word id", example = "23") Long id) {
		Optional<Word> opWord = wService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Word not found");
		} else {
			wService.removeWordById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateWord", notes = "Update the data from an existing word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<Word> updateWord(@Valid @RequestBody Word w,
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Word id", example = "23") Long id) {

		return wService.findById(id).map(newW -> {
			newW.setJapanese(w.getJapanese());
			newW.setEnglish(w.getEnglish());
			newW.setSpanish(w.getSpanish());
			newW.setFurigana(w.getFurigana());
			newW.setUrlImage(w.getUrlImage());
			newW.setCategoryId(w.getCategoryId());
			wService.updateWord(newW);
			return ResponseEntity.ok(newW);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Word not found"));

	}

	@ApiOperation(value = "getShuffledWordsByCategory", notes = "Get all words shuffled from a Category by category ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/shuffle/{categoryId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Word>> getShuffledWordsByCategory(
			@RequestParam(name = "categoryId") @ApiParam(name = "categoryId", value = "Category id", example = "7") Long id) {
		List<Word> wList = wService.findByCategoryId(id);
		Collections.shuffle(wList);

		if (wList != null && wList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words registered in that category");
		} else {
			return ResponseEntity.ok(wList);
		}
	}

	@ApiOperation(value = "searchWords", notes = "Search words by string contained")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Word.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{wString}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Word>> searchWords(
			@RequestParam(name = "wString") @ApiParam(name = "wString", value = "japanese, english or spanish", example = "dog") String wString) {
		List<Word> wList = wService.findWordsByMatcher(wString);

		if (wList != null && wList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words registered in that category");
		} else {
			return ResponseEntity.ok(wList);
		}
	}

}
