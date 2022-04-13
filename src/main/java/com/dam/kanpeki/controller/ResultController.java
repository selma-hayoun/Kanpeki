package com.dam.kanpeki.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;
import com.dam.kanpeki.model.dto.mapper.ResultDTOMapperStruct;
import com.dam.kanpeki.service.ResultServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("kanpeki/results")
public class ResultController {

	@Autowired
	private ResultServiceI rService;

	@Autowired
	private ResultDTOMapperStruct mapper;

	private static final Logger LOG = LoggerFactory.getLogger(ResultController.class);

	@ApiOperation(value = "getResults", notes = "Get all results from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseResultDTO>> getResults() {
		List<Result> rList = rService.findAllResults();

		if (rList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results registered");
		} else {
			return ResponseEntity.ok(mapper.toResultDTOList(rList.stream()));
		}
	}

	@ApiOperation(value = "getResultsCustomData", notes = "Get custom data from all results from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResultPerCategoryData.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/custom", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResultPerCategoryData>> getResultsCustomData() {
		List<ResultPerCategoryData> rDataList = rService.resultsPerCategory();

		if (rDataList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results data registered");
		} else {
			return ResponseEntity.ok(rDataList);
		}
	}

	@ApiOperation(value = "getResultsByUser", notes = "Get all results from a user ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result/user", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseResultDTO>> getResultsByUser(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		List<Result> rList = rService.findResultsUser(id);

		if (rList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results registered");
		} else {
			return ResponseEntity.ok(mapper.toResultDTOList(rList.stream()));
		}
	}

	@ApiOperation(value = "addNewResult", notes = "Create a new result")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseResultDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseResultDTO> addNewResult(@Valid @RequestBody RequestResultDTO r) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.toResultDTO(rService.addResult(mapper.requestResultDTOtoResult(r))));

	}

	@ApiOperation(value = "deleteResultsFromUser", notes = "Delete a single result by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseResultDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result/user/{userId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseResultDTO> deleteResultsFromUser(
			@PathVariable("userId") @ApiParam(name = "userId", value = "User id", example = "1") Long userId) {
		List<Result> rList = rService.findResultsUser(userId);

		if (rList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Results from user " + userId + " not found");
		} else {
			rList.stream().forEach(r -> rService.removeResult(r));
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "deleteResult", notes = "Delete a single result by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseResultDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseResultDTO> deleteResult(@Valid @RequestBody ResponseResultDTO r) {
		// Requiero el objeto completo para acceder a su objeto id
		ResultId resId = new ResultId(r.getUserId(), r.getResultDate());
		Optional<Result> opResult = rService.findById(resId);

		if (!opResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");
		} else {
			rService.removeResultById(resId);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "searchResultsBetweenDates", notes = "Search results by resultDate between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/result/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseResultDTO>> searchResultsBetweenDates(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<Result> rList = null;

		try {
			rList = rService.findResultsBetweenDates(new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		}

		if (rList == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dates format are incorrect");
		} else if (rList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No results between " + startDate + " and " + endDate);
		} else {
			return ResponseEntity.ok(mapper.toResultDTOList(rList.stream()));
		}
	}

}
