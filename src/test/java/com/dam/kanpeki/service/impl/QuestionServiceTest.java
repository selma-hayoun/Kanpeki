package com.dam.kanpeki.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidFKReferencesException;
import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.model.dto.RequestQuestionDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseQuestionDTO;
import com.dam.kanpeki.model.dto.mapper.QuestionAnswerDTOMapperStruct;
import com.dam.kanpeki.repository.QuestionRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.util.KanpekiDummyDataUtil;

public class QuestionServiceTest {

	private QuestionServiceImpl questionService;
	private QuestionRepository qRepo;
	private CategoryServiceI catService;
	private QuestionAnswerDTOMapperStruct mapper;
	private KanpekiDummyDataUtil kanpekiDummyDataUtil;

	private List<Question> listQuestionDummy;
	private List<ResponseQuestionDTO> listQuestionDummyResponse;
	private ResponseQuestionDTO qDummyResponse;

	@BeforeEach
	public void setup() {
		questionService = new QuestionServiceImpl();
		qRepo = Mockito.mock(QuestionRepository.class);
		catService = Mockito.mock(CategoryServiceI.class);
		mapper = Mockito.mock(QuestionAnswerDTOMapperStruct.class);

		questionService.qRepo = qRepo;
		questionService.catService = catService;
		questionService.mapper = mapper;

		kanpekiDummyDataUtil = new KanpekiDummyDataUtil();

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findAllQuestions Returns Correct List of Objects")
	void whenCallingFindAllQuestions_thenShouldReturnCorrectListOfObjects() throws Exception {
		Question dummyQ = new Question();
		listQuestionDummy = new ArrayList<>();
		listQuestionDummy = (List<Question>) kanpekiDummyDataUtil.getJsonDummyDataQuestion(
				"getQuestionsRepository.json", Optional.of(listQuestionDummy), Optional.of(dummyQ)).get();
		when(qRepo.findAll()).thenReturn(listQuestionDummy);

		listQuestionDummyResponse = new ArrayList<>();
		listQuestionDummyResponse = (List<ResponseQuestionDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataQuestion("getQuestionsResponse.json", Optional.of(listQuestionDummyResponse), null)
				.get();

		when(mapper.toQuestionDTOList(any())).thenReturn(listQuestionDummyResponse);

		assertEquals(questionService.findAllQuestions(), listQuestionDummyResponse);

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findByCategoryId Given Correct Category Id Returns Correct List of Objects")
	void whenCallingFindByCategoryId_givenCorrectCategoryId_thenShouldReturnCorrectListOfObjects() throws Exception {
		listQuestionDummy = new ArrayList<>();
		listQuestionDummy = (List<Question>) kanpekiDummyDataUtil.getJsonDummyDataQuestion(
				"getQuestionsByCategoryIdRepository.json", Optional.of(listQuestionDummy), null).get();
		when(qRepo.findAll()).thenReturn(listQuestionDummy);

		listQuestionDummyResponse = new ArrayList<>();
		listQuestionDummyResponse = (List<ResponseQuestionDTO>) kanpekiDummyDataUtil.getJsonDummyDataQuestion(
				"getQuestionsByCategoryIdResponse.json", Optional.of(listQuestionDummyResponse), null).get();

		when(mapper.toQuestionDTOList(any())).thenReturn(listQuestionDummyResponse);

		assertEquals(questionService.findByCategoryId(2L), listQuestionDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Correct Id Returns Correct Object")
	void whenCallingFindById_givenCorrectId_thenShouldReturnCorrectObject() throws Exception {
		Question dummyQ = new Question(1L, "statement", "furigana help", 2L, null);

		when(qRepo.findById(1L)).thenReturn(Optional.of(dummyQ));

		qDummyResponse = (ResponseQuestionDTO) kanpekiDummyDataUtil
				.getJsonDummyDataQuestion("getQuestionResponse.json", Optional.of(new ResponseQuestionDTO()), null)
				.get();

		when(mapper.toQuestionDTO(any())).thenReturn(qDummyResponse);

		assertEquals(questionService.findById(1L), Optional.of(qDummyResponse));

	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingFindById_givenIncorrectId_thenShouldThrowDataNotFoundException() throws Exception {
		when(qRepo.findById(1L)).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			questionService.findById(1L);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addQuestion is Given Correct RequestQuestionDTO Returns Correct Object")
	void whenCallingAddQuestion_givenCorrectRequestQuestionDTO_thenShouldReturnCorrectObject() throws Exception {
		RequestQuestionDTO dummyRequestQuestionDTO = new RequestQuestionDTO("あの 人は ジムさんの お父さんです。", "", 2L, null);
		Question dummyQ1 = new Question();
		dummyQ1.setStatement(dummyRequestQuestionDTO.getStatement());
		dummyQ1.setHelp(dummyRequestQuestionDTO.getHelp());
		dummyQ1.setCategoryId(dummyRequestQuestionDTO.getCategoryId());
		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(2L, "Lesson 1", "Family", true);

		when(catService.findById(dummyRequestQuestionDTO.getCategoryId())).thenReturn(Optional.of(dummyCat));

		when(mapper.requestQuestionDTOtoQuestion(dummyRequestQuestionDTO)).thenReturn(dummyQ1);

		Question dummyQ2 = new Question(1L, dummyQ1.getStatement(), dummyQ1.getHelp(), dummyQ1.getCategoryId(), null);

		when(qRepo.save(dummyQ1)).thenReturn(dummyQ2);

		qDummyResponse = (ResponseQuestionDTO) kanpekiDummyDataUtil
				.getJsonDummyDataQuestion("getQuestionResponse.json", Optional.of(new ResponseQuestionDTO()), null)
				.get();

		when(mapper.toQuestionDTO(any())).thenReturn(qDummyResponse);

		assertEquals(questionService.addQuestion(dummyRequestQuestionDTO), qDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When addQuestion is Given Incorrect RequestQuestionDTO Throws InvalidFKReferencesException")
	void whenCallingAddQuestion_givenIncorrectRequestQuestionDTO_thenThrowInvalidFKReferencesException()
			throws Exception {
		RequestQuestionDTO dummyRequestQuestionDTO = new RequestQuestionDTO("あの 人は ジムさんの お父さんです。", "", 2L, null);
		when(catService.findById(dummyRequestQuestionDTO.getCategoryId())).thenThrow(DataNotFoundException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			questionService.addQuestion(dummyRequestQuestionDTO);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateQuestion is Given Correct RequestQuestionDTO and ID Returns Correct Object")
	void whenCallingUpdateQuestion_givenCorrectRequestQuestionDTOAndId_thenShouldReturnCorrectObject()
			throws Exception {
		RequestQuestionDTO dummyRequestQuestionDTO = new RequestQuestionDTO("あの 人は ジムさんの お父さんです。", "", 2L, null);
		Question dummyQ1 = new Question();
		dummyQ1.setStatement(dummyRequestQuestionDTO.getStatement());
		dummyQ1.setHelp(dummyRequestQuestionDTO.getHelp());
		dummyQ1.setCategoryId(dummyRequestQuestionDTO.getCategoryId());

		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(2L, "Lesson 1", "Family", true);

		when(catService.findById(dummyRequestQuestionDTO.getCategoryId())).thenReturn(Optional.of(dummyCat));

		when(mapper.requestQuestionDTOtoQuestion(dummyRequestQuestionDTO)).thenReturn(dummyQ1);
		when(qRepo.findById(1L)).thenReturn(Optional.of(dummyQ1));

		Question dummyQ2 = new Question(1L, dummyQ1.getStatement(), dummyQ1.getHelp(), dummyQ1.getCategoryId(), null);

		when(qRepo.save(dummyQ1)).thenReturn(dummyQ2);

		qDummyResponse = (ResponseQuestionDTO) kanpekiDummyDataUtil
				.getJsonDummyDataQuestion("getQuestionResponse.json", Optional.of(new ResponseQuestionDTO()), null)
				.get();

		when(mapper.toQuestionDTO(any())).thenReturn(qDummyResponse);

		assertEquals(questionService.addQuestion(dummyRequestQuestionDTO), qDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When updateQuestion is Given Incorrect RequestQuestionDTO Throws InvalidFKReferencesException")
	void whenCallingUpdateQuestion_givenIncorrectRequestQuestionDTO_thenThrowInvalidFKReferencesException()
			throws Exception {
		RequestQuestionDTO dummyRequestQuestionDTO = new RequestQuestionDTO("あの 人は ジムさんの お父さんです。", "", 2L, null);
		when(catService.findById(dummyRequestQuestionDTO.getCategoryId())).thenThrow(DataNotFoundException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			questionService.updateQuestion(dummyRequestQuestionDTO, 1L);
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findQuestionsByMatcher is Given です Returns Correct List of Objects")
	void whenCallingFindQuestionsByMatcher_givenDesu_thenShouldReturnCorrectListOfObjects() throws Exception {
		Question dummyQ = new Question();
		listQuestionDummy = new ArrayList<>();
		listQuestionDummy = (List<Question>) kanpekiDummyDataUtil.getJsonDummyDataQuestion(
				"getQuestionsRepository.json", Optional.of(listQuestionDummy), Optional.of(dummyQ)).get();
		when(qRepo.findAll()).thenReturn(listQuestionDummy);

		listQuestionDummyResponse = new ArrayList<>();
		listQuestionDummyResponse = (List<ResponseQuestionDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataQuestion("searchQuestionsResponse.json", Optional.of(listQuestionDummyResponse), null)
				.get();

		when(mapper.toQuestionDTOList(any())).thenReturn(listQuestionDummyResponse);

		assertEquals(questionService.findQuestionsByMatcher(anyString()), listQuestionDummyResponse);
	}

}
