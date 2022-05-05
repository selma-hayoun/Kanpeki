package com.dam.kanpeki.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidFKReferencesException;
import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.ResultId;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;
import com.dam.kanpeki.model.dto.mapper.ResultDTOMapperStruct;
import com.dam.kanpeki.repository.ResultRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.util.KanpekiDummyDataUtil;
import com.dam.kanpeki.utils.KanpekiConstants;

public class ResultServiceTest {

	private ResultServiceImpl resultService;
	private ResultRepository rRepo;
	private CategoryServiceI catService;
	private ResultDTOMapperStruct mapper;
	private KanpekiDummyDataUtil kanpekiDummyDataUtil;

	private List<Result> listResultDummy;
	private List<ResponseResultDTO> listResultDummyResponse;
	private List<ResultPerCategoryData> listCustomResultDummyResponse;
	private ResponseResultDTO resultDummyResponse;

	@BeforeEach
	public void setup() {
		resultService = new ResultServiceImpl();
		rRepo = Mockito.mock(ResultRepository.class);
		catService = Mockito.mock(CategoryServiceI.class);
		mapper = Mockito.mock(ResultDTOMapperStruct.class);

		resultService.rRepo = rRepo;
		resultService.catService = catService;
		resultService.mapper = mapper;

		kanpekiDummyDataUtil = new KanpekiDummyDataUtil();

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findAllResults Returns Correct List of Objects")
	void whenCallingFindAllResults_thenShouldReturnCorrectListOfObjects() throws Exception {
		Result dummyR = new Result();
		listResultDummy = new ArrayList<>();
		listResultDummy = (List<Result>) kanpekiDummyDataUtil
				.getJsonDummyDataResult("getResultsRepository.json", Optional.of(listResultDummy), Optional.of(dummyR))
				.get();
		when(rRepo.findAll()).thenReturn(listResultDummy);

		listResultDummyResponse = new ArrayList<>();
		listResultDummyResponse = (List<ResponseResultDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataResult("getResultsResponse.json", Optional.of(listResultDummyResponse), null).get();

		when(mapper.toResultDTOList(any())).thenReturn(listResultDummyResponse);

		assertEquals(resultService.findAllResults(), listResultDummyResponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When resultsPerCategory Returns Correct List of Objects")
	void whenCallingResultsPerCategory_thenShouldReturnCorrectListOfObjects() throws Exception {
		ResultPerCategoryData dummyR = new ResultPerCategoryData();
		List<ResultPerCategoryData> listCustomResultDummyRepo = new ArrayList<>();
		listCustomResultDummyRepo = (List<ResultPerCategoryData>) kanpekiDummyDataUtil.getJsonDummyDataResult(
				"getCustomResultsRepository.json", Optional.of(listCustomResultDummyRepo), Optional.of(dummyR)).get();

		when(rRepo.resultsPerCategory()).thenReturn(listCustomResultDummyRepo);

		listCustomResultDummyResponse = new ArrayList<>();
		listCustomResultDummyResponse = (List<ResultPerCategoryData>) kanpekiDummyDataUtil.getJsonDummyDataResult(
				"getCustomResultsResponse.json", Optional.of(listCustomResultDummyResponse), Optional.of(dummyR)).get();

		assertEquals(resultService.resultsPerCategory(), listCustomResultDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Correct Id Returns Correct Object")
	void whenCallingFindById_givenCorrectId_thenShouldReturnCorrectObject() throws Exception {
		Result dummyR = new Result(new ResultId(1L, null), 0, 2L);
		when(rRepo.findById(any())).thenReturn(Optional.of(dummyR));
		assertEquals(resultService.findById(new ResultId(1L, null)), Optional.of(dummyR));
	}

	@Test
	@DisplayName("Test Should Pass When addResult is Given Correct RequestResultDTO Returns Correct Object")
	void whenCallingAddResult_givenCorrectRequestResultDTO_thenShouldReturnCorrectObject() throws Exception {
		RequestResultDTO dummyRequestResult = new RequestResultDTO(2L, 0, 2L);
		Result dummyRes1 = new Result();
		dummyRes1.setId(new ResultId(dummyRequestResult.getUserId(), null));
		dummyRes1.setScore(dummyRequestResult.getScore());
		dummyRes1.setCategoryId(dummyRequestResult.getCategoryId());
		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(2L, "Lesson 1", "Family", true);

		when(catService.findById(dummyRequestResult.getCategoryId())).thenReturn(Optional.of(dummyCat));
		when(mapper.requestResultDTOtoResult(dummyRequestResult)).thenReturn(dummyRes1);

		Result dummyRes2 = new Result(dummyRes1.getId(), dummyRes1.getScore(), dummyRes1.getCategoryId());

		when(rRepo.save(dummyRes1)).thenReturn(dummyRes2);

		resultDummyResponse = (ResponseResultDTO) kanpekiDummyDataUtil
				.getJsonDummyDataResult("getResultResponse.json", Optional.of(new ResponseResultDTO()), null).get();

		when(mapper.toResultDTO(any())).thenReturn(resultDummyResponse);

		assertEquals(resultService.addResult(dummyRequestResult), resultDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When addResult is Given Incorrect RequestResultDTO (Category) Throws InvalidFKReferencesException")
	void whenCallingAddResult_givenIncorrectRequestResultDTOCategory_thenThrowInvalidFKReferencesException()
			throws Exception {
		RequestResultDTO dummyRequestResult = new RequestResultDTO(2L, 0, 2L);
		when(catService.findById(dummyRequestResult.getCategoryId())).thenThrow(DataNotFoundException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			resultService.addResult(dummyRequestResult);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addResult is Given Incorrect RequestResultDTO (UserId) Throws InvalidFKReferencesException")
	void whenCallingAddResult_givenIncorrectRequestResultDTOUserId_thenThrowInvalidFKReferencesException()
			throws Exception {
		RequestResultDTO dummyRequestResult = new RequestResultDTO(2L, 0, 2L);
		Result dummyRes1 = new Result();
		dummyRes1.setId(new ResultId(dummyRequestResult.getUserId(), null));
		dummyRes1.setScore(dummyRequestResult.getScore());
		dummyRes1.setCategoryId(dummyRequestResult.getCategoryId());
		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(2L, "Lesson 1", "Family", true);

		when(catService.findById(dummyRequestResult.getCategoryId())).thenReturn(Optional.of(dummyCat));
		when(catService.findById(dummyRequestResult.getCategoryId())).thenThrow(DataNotFoundException.class);
		when(mapper.requestResultDTOtoResult(dummyRequestResult)).thenReturn(dummyRes1);
		when(rRepo.save(dummyRes1)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			resultService.addResult(dummyRequestResult);
		});
	}

	@Test
	@DisplayName("Test Should Pass When removeResultById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingRemoveResultById_givenIncorrectId_thenThrowDataNotFoundException() throws Exception {
		resultDummyResponse = (ResponseResultDTO) kanpekiDummyDataUtil
				.getJsonDummyDataResult("getResultResponse.json", Optional.of(new ResponseResultDTO()), null).get();
		ResultId resId = new ResultId(resultDummyResponse.getUserId(), resultDummyResponse.getResultDate());

		when(rRepo.findById(resId)).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			resultService.removeResultById(resultDummyResponse);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateResult is Given Incorrect Result (Category) Throws InvalidFKReferencesException")
	void whenCallingUpdateResult_givenIncorrectResultCategoryId_thenThrowInvalidFKReferencesException()
			throws Exception {
		Result dummyRes = new Result(new ResultId(2L, null), 2, 2L);
		when(catService.findById(dummyRes.getCategoryId())).thenThrow(DataNotFoundException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			resultService.updateResult(dummyRes);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateResult is Given Incorrect Result (User Id) Throws InvalidFKReferencesException")
	void whenCallingUpdateResult_givenIncorrectResultUserId_thenThrowInvalidFKReferencesException() throws Exception {
		Result dummyRes = new Result(new ResultId(2L, null), 2, 2L);
		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(2L, "Lesson 1", "Family", true);

		when(catService.findById(dummyRes.getCategoryId())).thenReturn(Optional.of(dummyCat));
		when(rRepo.save(dummyRes)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			resultService.updateResult(dummyRes);
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findResultsBetweenDates Given Correct Dates Returns Correct List of Objects")
	void whenCallingFindResultsBetweenDates_givenCorrectDates_thenShouldReturnCorrectListOfObjects() throws Exception {
		String startDate = "2022-01-01";
		String endDate = "2022-01-31";

		Result dummyR = new Result();
		listResultDummy = new ArrayList<>();
		listResultDummy = (List<Result>) kanpekiDummyDataUtil.getJsonDummyDataResult(
				"searchBetweenDatesResultRepository.json", Optional.of(listResultDummy), Optional.of(dummyR)).get();
		when(rRepo.findResultsBetweenDates(new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(endDate))).thenReturn(listResultDummy);

		listResultDummyResponse = new ArrayList<>();
		listResultDummyResponse = (List<ResponseResultDTO>) kanpekiDummyDataUtil.getJsonDummyDataResult(
				"searchBetweenDatesResultResponse.json", Optional.of(listResultDummyResponse), null).get();

		when(mapper.toResultDTOList(any())).thenReturn(listResultDummyResponse);

		assertEquals(resultService.findResultsBetweenDates(
				new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(endDate)), listResultDummyResponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findResultsUser Given Correct User Id Returns Correct List of Objects")
	void whenCallingFindResultsUser_givenCorrectUserId_thenShouldReturnCorrectListOfObjects() throws Exception {
		Result dummyR = new Result();
		listResultDummy = new ArrayList<>();
		listResultDummy = (List<Result>) kanpekiDummyDataUtil.getJsonDummyDataResult(
				"searchByUserIdResultRepository.json", Optional.of(listResultDummy), Optional.of(dummyR)).get();
		when(rRepo.findResultsUser(1L)).thenReturn(listResultDummy);

		listResultDummyResponse = new ArrayList<>();
		listResultDummyResponse = (List<ResponseResultDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataResult("searchByUserIdResultResponse.json", Optional.of(listResultDummyResponse), null)
				.get();

		when(mapper.toResultDTOList(any())).thenReturn(listResultDummyResponse);

		assertEquals(resultService.findResultsUser(2L), listResultDummyResponse);
	}

}
