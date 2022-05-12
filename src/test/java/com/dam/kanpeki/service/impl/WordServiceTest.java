package com.dam.kanpeki.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import com.dam.kanpeki.exception.WordJapaneseAlreadyExistsException;
import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.model.dto.RequestWordDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseWordDTO;
import com.dam.kanpeki.model.dto.mapper.WordDTOMapperStruct;
import com.dam.kanpeki.repository.WordRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.util.KanpekiDummyDataUtil;
import com.dam.kanpeki.util.KanpekiTestsConstants;

class WordServiceTest {

	private WordServiceImpl wordService;
	private WordRepository wRepo;
	private WordDTOMapperStruct mapper;
	private FileSystemStorageServiceI storeService;
	private CategoryServiceI catService;
	private KanpekiDummyDataUtil kanpekiDummyDataUtil;

	private List<Word> listWordDummy;
	private List<ResponseWordDTO> listWordDummyResponse;
	private ResponseWordDTO wDummyResponse;

	@BeforeEach
	public void setup() {
		wordService = new WordServiceImpl();

		wRepo = Mockito.mock(WordRepository.class);
		storeService = Mockito.mock(FileSystemStorageServiceI.class);
		catService = Mockito.mock(CategoryServiceI.class);
		mapper = Mockito.mock(WordDTOMapperStruct.class);

		wordService.wRepo = wRepo;
		wordService.mapper = mapper;
		wordService.storeService = storeService;
		wordService.catService = catService;

		kanpekiDummyDataUtil = new KanpekiDummyDataUtil();

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findAllWords Returns Correct List of Objects")
	void whenCallingFindAllWords_thenShouldReturnCorrectListOfObjects() throws Exception {
		Word dummyW = new Word();
		listWordDummy = new ArrayList<>();
		listWordDummy = (List<Word>) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordsRepository.json", Optional.of(listWordDummy), Optional.of(dummyW)).get();
		when(wRepo.findAll()).thenReturn(listWordDummy);

		listWordDummyResponse = new ArrayList<>();
		listWordDummyResponse = (List<ResponseWordDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordsResponse.json", Optional.of(listWordDummyResponse), null).get();

		when(mapper.toWordDTOList(any())).thenReturn(listWordDummyResponse);

		assertEquals(wordService.findAllWords(), listWordDummyResponse);

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findWordsByMatcher is Given string dog Returns Correct List of Objects")
	void whenCallingFindWordsByMatcher_givenDog_thenShouldReturnCorrectListOfObjects() throws Exception {
		String searchedStr = "dog";
		listWordDummy = new ArrayList<>();
		listWordDummy = (List<Word>) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordsRepository.json", Optional.of(listWordDummy), Optional.of(new User()))
				.get();

		when(wRepo.findAll()).thenReturn(listWordDummy);

		listWordDummyResponse = new ArrayList<>();
		listWordDummyResponse = (List<ResponseWordDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataWord("searchWordsResponse.json", Optional.of(listWordDummyResponse), null).get();

		when(mapper.toWordDTOList(any())).thenReturn(listWordDummyResponse);

		assertEquals(wordService.findWordsByMatcher(searchedStr), listWordDummyResponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findByCategoryId Given Correct Category Id Returns Correct List of Objects")
	void whenCallingFindByCategoryId_givenCorrectCategoryId_thenShouldReturnCorrectListOfObjects() throws Exception {
		listWordDummy = new ArrayList<>();
		listWordDummy = (List<Word>) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordsByCategoryRepository.json", Optional.of(listWordDummy), null).get();
		when(wRepo.findAll()).thenReturn(listWordDummy);

		listWordDummyResponse = new ArrayList<>();
		listWordDummyResponse = (List<ResponseWordDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordsByCategoryResponse.json", Optional.of(listWordDummyResponse), null)
				.get();

		when(mapper.toWordDTOList(any())).thenReturn(listWordDummyResponse);

		assertEquals(wordService.findByCategoryId(KanpekiTestsConstants.ID), listWordDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Correct Id Returns Correct Object")
	void whenCallingFindById_givenCorrectId_thenShouldReturnCorrectObject() throws Exception {
		Word dummyW = new Word(KanpekiTestsConstants.ID, KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.EMPTY_STRING,
				KanpekiTestsConstants.ID);

		when(wRepo.findById(KanpekiTestsConstants.ID)).thenReturn(Optional.of(dummyW));

		wDummyResponse = (ResponseWordDTO) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordResponse.json", Optional.of(new ResponseWordDTO()), null).get();

		when(mapper.toWordDTO(any())).thenReturn(wDummyResponse);

		assertEquals(wordService.findById(KanpekiTestsConstants.ID), Optional.of(wDummyResponse));

	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingFindById_givenIncorrectId_thenShouldThrowDataNotFoundException() throws Exception {
		when(wRepo.findById(KanpekiTestsConstants.ID)).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			wordService.findById(KanpekiTestsConstants.ID);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addWord is Given Correct RequestWordDTO Returns Correct Object")
	void whenCallingAddWord_givenCorrectRequestWordDTO_thenShouldReturnCorrectObject() throws Exception {
		RequestWordDTO dummyRequestWordDTO = new RequestWordDTO(KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.ID, null);

		Word dummyW1 = new Word();
		dummyW1.setJapanese(dummyRequestWordDTO.getJapanese());
		dummyW1.setEnglish(dummyRequestWordDTO.getEnglish());
		dummyW1.setSpanish(dummyRequestWordDTO.getSpanish());
		dummyW1.setFurigana(dummyRequestWordDTO.getFurigana());
		dummyW1.setUrlImage(dummyRequestWordDTO.getFile() == null ? KanpekiTestsConstants.EMPTY_STRING
				: dummyRequestWordDTO.getFile().getName());
		dummyW1.setCategoryId(dummyRequestWordDTO.getCategoryId());

		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(KanpekiTestsConstants.ID_ALT,
				KanpekiTestsConstants.UNIT_NAME_EXAMPLE, KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, true);

		when(catService.findById(dummyRequestWordDTO.getCategoryId())).thenReturn(Optional.of(dummyCat));
		when(wRepo.countWordJapaneseUnique(dummyRequestWordDTO.getJapanese())).thenReturn(0);
		when(mapper.requestWordDTOtoWord(dummyRequestWordDTO)).thenReturn(dummyW1);

		Word dummyW2 = new Word();
		dummyW2.setId(KanpekiTestsConstants.ID);
		dummyW2.setJapanese(dummyW1.getJapanese());
		dummyW2.setEnglish(dummyW1.getEnglish());
		dummyW2.setSpanish(dummyW1.getSpanish());
		dummyW2.setFurigana(dummyW1.getFurigana());
		dummyW2.setUrlImage(dummyW1.getUrlImage());
		dummyW2.setCategoryId(dummyW1.getCategoryId());

		when(wRepo.save(dummyW1)).thenReturn(dummyW2);

		wDummyResponse = (ResponseWordDTO) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordResponse.json", Optional.of(new ResponseWordDTO()), null).get();

		when(mapper.toWordDTO(any())).thenReturn(wDummyResponse);

		when(storeService.saveFileRequest(any())).thenReturn(KanpekiTestsConstants.EMPTY_STRING);

		assertEquals(wordService.addWord(dummyRequestWordDTO, null), wDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When addWord is Given Japanese Already Exists Throws WordJapaneseAlreadyExistsException")
	void whenCallingAddWord_givenJapaneseAlreadyExists_thenThrowWordJapaneseAlreadyExistsException() throws Exception {
		RequestWordDTO dummyRequestWordDTO = new RequestWordDTO(KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.ID, null);

		when(wRepo.countWordJapaneseUnique(anyString())).thenReturn(1);

		assertThrows(WordJapaneseAlreadyExistsException.class, () -> {
			wordService.addWord(dummyRequestWordDTO, null);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addWord is Given Incorrect RequestWordDTO (CategoryId) Throws InvalidFKReferencesException")
	void whenCallingAddWord_givenIncorrectRequestWordDTO_thenThrowInvalidFKReferencesException() throws Exception {
		RequestWordDTO dummyRequestWordDTO = new RequestWordDTO(KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.ID, null);
		when(catService.findById(dummyRequestWordDTO.getCategoryId())).thenThrow(DataNotFoundException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			wordService.addWord(dummyRequestWordDTO, null);
		});
	}

	@Test
	@DisplayName("Test Should Pass When removeWordById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingRemoveWordById_givenIncorrectId_thenThrowDataNotFoundException() throws Exception {
		when(wRepo.findById(anyLong())).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			wordService.removeWordById(KanpekiTestsConstants.ID);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateWord is Given Correct RequestWordDTO And Id Returns Correct Object")
	void whenCallingUpdateWord_givenCorrectRequestWordDTOAndId_thenShouldReturnCorrectObject() throws Exception {
		RequestWordDTO dummyRequestWordDTO = new RequestWordDTO(KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.ID, null);

		Word dummyW1 = new Word();
		dummyW1.setJapanese(dummyRequestWordDTO.getJapanese());
		dummyW1.setEnglish(dummyRequestWordDTO.getEnglish());
		dummyW1.setSpanish(dummyRequestWordDTO.getSpanish());
		dummyW1.setFurigana(dummyRequestWordDTO.getFurigana());
		dummyW1.setUrlImage(dummyRequestWordDTO.getFile() == null ? KanpekiTestsConstants.EMPTY_STRING
				: dummyRequestWordDTO.getFile().getName());
		dummyW1.setCategoryId(dummyRequestWordDTO.getCategoryId());

		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(KanpekiTestsConstants.ID_ALT,
				KanpekiTestsConstants.UNIT_NAME_EXAMPLE, KanpekiTestsConstants.CATEGORY_NAME_EXAMPLE, true);

		when(catService.findById(dummyRequestWordDTO.getCategoryId())).thenReturn(Optional.of(dummyCat));
		when(wRepo.countWordJapaneseUnique(dummyRequestWordDTO.getJapanese())).thenReturn(0);
		when(wRepo.findByJapanese(dummyRequestWordDTO.getJapanese())).thenReturn(Optional.ofNullable(null));
		when(mapper.requestWordDTOtoWord(dummyRequestWordDTO)).thenReturn(dummyW1);

		Word dummyW2 = new Word(KanpekiTestsConstants.ID, dummyW1.getJapanese(), dummyW1.getEnglish(),
				dummyW1.getSpanish(), dummyW1.getFurigana(), dummyW1.getUrlImage(), dummyW1.getCategoryId());

		when(wRepo.save(dummyW1)).thenReturn(dummyW2);

		wDummyResponse = (ResponseWordDTO) kanpekiDummyDataUtil
				.getJsonDummyDataWord("getWordResponse.json", Optional.of(new ResponseWordDTO()), null).get();

		when(mapper.toWordDTO(any())).thenReturn(wDummyResponse);

		when(storeService.saveFileRequest(any())).thenReturn(KanpekiTestsConstants.EMPTY_STRING);

		assertEquals(wordService.addWord(dummyRequestWordDTO, null), wDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When updateWord is Given Incorrect RequestWordDTO (CategoryId) Throws InvalidFKReferencesException")
	void whenCallingUpdateWord_givenIncorrectRequestWordDTO_thenThrowInvalidFKReferencesException() throws Exception {
		RequestWordDTO dummyRequestWordDTO = new RequestWordDTO(KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.ID, null);
		when(catService.findById(dummyRequestWordDTO.getCategoryId())).thenThrow(DataNotFoundException.class);

		assertThrows(InvalidFKReferencesException.class, () -> {
			wordService.updateWord(dummyRequestWordDTO, null, KanpekiTestsConstants.ID);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateWord is Given Japanese Already Exists Throws WordJapaneseAlreadyExistsException")
	void whenCallingUpdateWord_givenJapaneseAlreadyExists_thenThrowWordJapaneseAlreadyExistsException()
			throws Exception {
		RequestWordDTO dummyRequestWordDTO = new RequestWordDTO(KanpekiTestsConstants.WORD_JAPANESE_EXAMPLE,
				KanpekiTestsConstants.WORD_ENGLISH_EXAMPLE, KanpekiTestsConstants.WORD_SPANISH_EXAMPLE,
				KanpekiTestsConstants.WORD_FURIGANA_EXAMPLE, KanpekiTestsConstants.ID, null);
		Word dummyW = new Word(KanpekiTestsConstants.ID_ALT, "りょうしん", "parents", "padres", "りょうしん",
				KanpekiTestsConstants.EMPTY_STRING, KanpekiTestsConstants.ID);
		ResponseCategoryDTO dummyCat = new ResponseCategoryDTO(KanpekiTestsConstants.ID_ALT,
				KanpekiTestsConstants.UNIT_NAME_EXAMPLE, KanpekiTestsConstants.CATEGORY_NAME_EXAMPLE, true);

		when(catService.findById(dummyRequestWordDTO.getCategoryId())).thenReturn(Optional.of(dummyCat));
		when(wRepo.findByJapanese(dummyRequestWordDTO.getJapanese())).thenReturn(Optional.of(dummyW));
		when(wRepo.countWordJapaneseUnique(dummyRequestWordDTO.getJapanese())).thenReturn(2);

		assertThrows(WordJapaneseAlreadyExistsException.class, () -> {
			wordService.updateWord(dummyRequestWordDTO, null, KanpekiTestsConstants.ID);
		});
	}

}
