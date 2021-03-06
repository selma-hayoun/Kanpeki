package com.dam.kanpeki.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

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
import com.dam.kanpeki.exception.ParameterIncorrectFormatException;
import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;
import com.dam.kanpeki.service.ResultServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("kanpeki/results")
public class ResultController {

	@Autowired
	private ResultServiceI rService;

	@ApiOperation(value = "getResults", notes = "Get all results from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = KanpekiConstants.EMPTY_STRING, produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseResultDTO>> getResults() {
		List<ResponseResultDTO> rList = rService.findAllResults();

		if (rList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(rList);
		}
	}

	@ApiOperation(value = "getResultsCustomData", notes = "Get custom data from all results from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResultPerCategoryData.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/custom", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResultPerCategoryData>> getResultsCustomData() {
		List<ResultPerCategoryData> rDataList = rService.resultsPerCategory();

		if (rDataList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(rDataList);
		}
	}

	@ApiOperation(value = "getResultsByUser", notes = "Get all results from a user ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/result/user", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseResultDTO>> getResultsByUser(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		List<ResponseResultDTO> rList = rService.findResultsUser(id);

		if (rList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(rList);
		}
	}

	@ApiOperation(value = "getResultsByUserCustomData", notes = "Get custom data results from a user ID ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/result/user/custom", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResultPerCategoryData>> getResultsByUserCustomData(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		List<ResultPerCategoryData> rList = rService.findResultsUserPerCategory(id);

		if (rList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(rList);
		}
	}

	@ApiOperation(value = "addNewResult", notes = "Create a new result")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class),
			@ApiResponse(code = 201, message = KanpekiConstants.CONTROLLER_MSG_201, response = ResponseResultDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/result", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseResultDTO> addNewResult(@Valid @RequestBody RequestResultDTO r) {

		return ResponseEntity.status(HttpStatus.CREATED).body(rService.addResult(r));

	}

	@ApiOperation(value = "deleteResultsFromUser", notes = "Delete a single result by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class),
			@ApiResponse(code = 204, message = KanpekiConstants.CONTROLLER_MSG_204),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/result/user/{userId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseResultDTO> deleteResultsFromUser(
			@PathVariable("userId") @ApiParam(name = "userId", value = "User id", example = "1") Long userId) {
		List<ResponseResultDTO> rList = rService.findResultsUser(userId);

		if (rList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.DATA_NOT_FOUND_EX_USER_RESULTS + userId);
		} else {
			rList.stream().forEach(r -> rService.removeResult(r));
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "deleteResult", notes = "Delete a single result by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class),
			@ApiResponse(code = 204, message = KanpekiConstants.CONTROLLER_MSG_204),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/result", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseResultDTO> deleteResult(@Valid @RequestBody ResponseResultDTO r) {
		// Requiero el objeto completo para acceder a su objeto id
		ResultId resId = new ResultId(r.getUserId(), r.getResultDate());
		Optional<Result> opResult = rService.findById(resId);

		if (!opResult.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			rService.removeResultById(r);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "searchResultsBetweenDates", notes = "Search results by resultDate between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseResultDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/result/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseResultDTO>> searchResultsBetweenDates(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2022-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2022-01-31") String endDate) {

		List<ResponseResultDTO> rList = null;

		try {
			rList = rService.findResultsBetweenDates(
					new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(startDate),
					new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(endDate));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		if (rList == null) {
			throw new ParameterIncorrectFormatException(KanpekiConstants.PARAMETER_INCORRECT_FORMAT_EX_DATES);
		} else if (rList.isEmpty()) {
			throw new DataNotFoundException(
					String.format(KanpekiConstants.DATA_NOT_FOUND_EX_RESULTS_BY_DATES, startDate, endDate));
		} else {
			return ResponseEntity.ok(rList);
		}
	}

}
