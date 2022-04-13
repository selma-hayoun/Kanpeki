package com.dam.kanpeki.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.model.dto.RequestWordDTO;
import com.dam.kanpeki.model.dto.ResponseWordDTO;
import com.dam.kanpeki.model.dto.mapper.WordDTOMapperStruct;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.WordServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("kanpeki/words")
public class WordController {

	private static final String SERVE_FILE = "serveFile";

	@Autowired
	private WordServiceI wService;

	@Autowired
	private FileSystemStorageServiceI storeService;

	@Autowired
	private WordDTOMapperStruct mapper;

	@ApiOperation(value = "getWords", notes = "Get all words from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseWordDTO>> getWords() {
		List<Word> wList = wService.findAllWords();

		if (wList.isEmpty()) {
			throw new DataNotFoundException("");
		} else {
			return ResponseEntity.ok(mapper.toWordDTOList(wList.stream()));
		}
	}

	@ApiOperation(value = "getWord", notes = "Get a word by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseWordDTO> getWord(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "Word id", example = "1") Long id) {
		Optional<Word> opWord = wService.findById(id);

		if (!opWord.isPresent()) {
			throw new DataNotFoundException("");
		} else {
			return ResponseEntity.ok(mapper.toWordDTO(opWord.get()));
		}
	}

	@ApiOperation(value = "addNewWord", notes = "Create a new word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/v1", produces = {
			"application/json" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ResponseWordDTO> addNewWordV1(@Valid @RequestPart(value = "w") RequestWordDTO w,
			@RequestPart(value = "file", required = false) MultipartFile file) {

		String urlImg = "";

		if (file != null && !file.isEmpty()) {
			// Almacenamos el fichero y obtenemos su URL
			String img = storeService.store(file);
			urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, SERVE_FILE, img, null).build()
					.toUriString();
		}

		Word wTemp = mapper.requestWordDTOtoWord(w);
		// Seteamos la URL donde está almacenada
		wTemp.setUrlImage(urlImg);

		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toWordDTO(wService.addWord(wTemp)));

	}

	@ApiOperation(value = "addNewWord", notes = "Create a new word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/v2", produces = { "application/json" }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<ResponseWordDTO> addNewWordV2(
			@Valid @Parameter(description = "Word attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestWordDTO w,
			@Parameter(description = "Word image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file) {

		String urlImg = "";

		if (file != null && !file.isEmpty()) {
			String img = storeService.store(file);
			urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, SERVE_FILE, img, null).build()
					.toUriString();
		}

		Word wTemp = mapper.requestWordDTOtoWord(w);
		// Seteamos la URL donde está almacenada
		wTemp.setUrlImage(urlImg);

		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toWordDTO(wService.addWord(wTemp)));

	}

	@ApiOperation(value = "deleteWord", notes = "Delete a single word by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseWordDTO> deleteWord(
			@PathVariable("id") @ApiParam(name = "id", value = "Word id", example = "1") Long id) {
		Optional<Word> opWord = wService.findById(id);

		if (!opWord.isPresent()) {
			throw new DataNotFoundException("");
		} else {
			// Eliminamos la imagen del almacenamiento
			storeService.delete(opWord.get().getUrlImage());
			wService.removeWordById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateWord", notes = "Update the data from an existing word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/v1/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseWordDTO> updateWordV1(@Valid @RequestPart(value = "w") RequestWordDTO w,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@PathVariable("id") @ApiParam(name = "id", value = "Word id", example = "1") Long id) {

		Optional<Word> opWord = wService.findById(id);

		if (opWord.isPresent()) {

			String urlImg = "";

			if (file != null) {
				// Eliminamos la imagen anterior del almacenamiento
				storeService.delete(opWord.get().getUrlImage());

				// Almacenamos el fichero y obtenemos su URL
				if (!file.isEmpty()) {
					String img = storeService.store(file);
					urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, SERVE_FILE, img, null)
							.build().toUriString();
				}
			}

			Word mappedW = mapper.requestWordDTOtoWord(w);
			// Seteamos la URL donde está almacenada
			mappedW.setUrlImage(urlImg);

			Word mappedWUpdated = opWord.map(newW -> {
				newW.setJapanese(mappedW.getJapanese());
				newW.setEnglish(mappedW.getEnglish());
				newW.setSpanish(mappedW.getSpanish());
				newW.setFurigana(mappedW.getFurigana());
				newW.setUrlImage(mappedW.getUrlImage());
				newW.setCategoryId(mappedW.getCategoryId());
				wService.updateWord(newW);
				return newW;
			}).orElseThrow(() -> new DataNotFoundException(""));

			return ResponseEntity.ok(mapper.toWordDTO(mappedWUpdated));
		} else {
			throw new DataNotFoundException("");
		}

	}

	@ApiOperation(value = "updateWord", notes = "Update the data from an existing word")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/v2/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseWordDTO> updateWordV2(
			@Valid @Parameter(description = "Word attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestWordDTO w,
			@Parameter(description = "Word image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file,
			@PathVariable("id") @ApiParam(name = "id", value = "Word id", example = "1") Long id) {

		Optional<Word> opWord = wService.findById(id);

		if (opWord.isPresent()) {

			String urlImg = "";

			if (file != null) {
				// Eliminamos la imagen anterior del almacenamiento
				storeService.delete(opWord.get().getUrlImage());

				// Almacenamos el fichero y obtenemos su URL
				if (!file.isEmpty()) {
					String img = storeService.store(file);
					urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, SERVE_FILE, img, null)
							.build().toUriString();
				}
			}

			Word mappedW = mapper.requestWordDTOtoWord(w);
			// Seteamos la URL donde está almacenada
			mappedW.setUrlImage(urlImg);

			Word mappedWUpdated = opWord.map(newW -> {
				newW.setJapanese(mappedW.getJapanese());
				newW.setEnglish(mappedW.getEnglish());
				newW.setSpanish(mappedW.getSpanish());
				newW.setFurigana(mappedW.getFurigana());
				newW.setUrlImage(mappedW.getUrlImage());
				newW.setCategoryId(mappedW.getCategoryId());
				wService.updateWord(newW);
				return newW;
			}).orElseThrow(() -> new DataNotFoundException(""));

			return ResponseEntity.ok(mapper.toWordDTO(mappedWUpdated));
		} else {
			throw new DataNotFoundException("");
		}

	}

	@ApiOperation(value = "getShuffledWordsByCategory", notes = "Get all words shuffled from a Category by category ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/shuffle", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseWordDTO>> getShuffledWordsByCategory(
			@RequestParam(name = "categoryId") @ApiParam(name = "categoryId", value = "Category id", example = "1") Long id) {
		List<Word> wList = wService.findByCategoryId(id);
		Collections.shuffle(wList);

		if (wList.isEmpty()) {
			throw new DataNotFoundException("No words registered in that category");
		} else {
			return ResponseEntity.ok(mapper.toWordDTOList(wList.stream()));
		}
	}

	@ApiOperation(value = "searchWords", notes = "Search words by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseWordDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/word/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseWordDTO>> searchWords(
			@RequestParam(name = "wString") @ApiParam(name = "wString", value = "japanese, english or spanish", example = "dog") String wString) {
		List<Word> wList = wService.findWordsByMatcher(wString);

		if (wList.isEmpty()) {
			throw new DataNotFoundException("No words contain the string");
		} else {
			return ResponseEntity.ok(mapper.toWordDTOList(wList.stream()));
		}
	}

}
