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
import com.dam.kanpeki.model.dto.CreateWordDTO;
import com.dam.kanpeki.model.dto.GetWordDTO;
import com.dam.kanpeki.model.dto.mapper.WordDTOMapperStruct;
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

	@Autowired
	private WordDTOMapperStruct mapper;

	@ApiOperation(value = "getWords", notes = "Get all words from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<GetWordDTO>> getWords() {
		List<Word> wList = wService.findAllWords();

		if (wList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words registered");
		} else {
			return ResponseEntity.ok(mapper.toWordDTOList(wList.stream()));
		}
	}

	@ApiOperation(value = "getWord", notes = "Get a word by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<GetWordDTO> getWord(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Word id", example = "4") Long id) {
		Optional<Word> opWord = wService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Word not found");
		} else {
			return ResponseEntity.ok(mapper.toWordDTO(opWord.get()));
		}
	}

	@ApiOperation(value = "addNewWord", notes = "Create a new word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<GetWordDTO> addNewWord(@Valid @RequestBody CreateWordDTO w) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.toWordDTO(wService.addWord(mapper.createWordDTOtoWord(w))));

	}

	@ApiOperation(value = "deleteWord", notes = "Delete a single word by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<GetWordDTO> deleteWord(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Word id", example = "4") Long id) {
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
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<GetWordDTO> updateWord(@Valid @RequestBody GetWordDTO w,
			@RequestParam(name = "id") @ApiParam(name = "id", value = "Word id", example = "4") Long id) {

		Word mappedW = mapper.getWordDTOtoWord(w);

		Word mappedWUpdated = wService.findById(id).map(newW -> {
			newW.setJapanese(mappedW.getJapanese());
			newW.setEnglish(mappedW.getEnglish());
			newW.setSpanish(mappedW.getSpanish());
			newW.setFurigana(mappedW.getFurigana());
			newW.setUrlImage(mappedW.getUrlImage());
			newW.setCategoryId(mappedW.getCategoryId());
			wService.updateWord(newW);
			return newW;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Word not found"));

		return ResponseEntity.ok(mapper.toWordDTO(mappedWUpdated));

	}

	@ApiOperation(value = "getShuffledWordsByCategory", notes = "Get all words shuffled from a Category by category ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/shuffle/{categoryId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<GetWordDTO>> getShuffledWordsByCategory(
			@RequestParam(name = "categoryId") @ApiParam(name = "categoryId", value = "Category id", example = "3") Long id) {
		List<Word> wList = wService.findByCategoryId(id);
		Collections.shuffle(wList);

		if (wList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words registered in that category");
		} else {
			return ResponseEntity.ok(mapper.toWordDTOList(wList.stream()));
		}
	}

	@ApiOperation(value = "searchWords", notes = "Search words by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = GetWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{wString}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<GetWordDTO>> searchWords(
			@RequestParam(name = "wString") @ApiParam(name = "wString", value = "japanese, english or spanish", example = "dog") String wString) {
		List<Word> wList = wService.findWordsByMatcher(wString);

		if (wList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words contain the string");
		} else {
			return ResponseEntity.ok(mapper.toWordDTOList(wList.stream()));
		}
	}

}
