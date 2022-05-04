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

import com.dam.kanpeki.exception.CategoryAlreadyExistsException;
import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidOperationOnCategoryException;
import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.dto.RequestCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.mapper.CategoryDTOMapperStruct;
import com.dam.kanpeki.repository.CategoryRepository;
import com.dam.kanpeki.util.KanpekiDummyDataUtil;

public class CategoryServiceTest {

	private CategoryRepository catRepo;
	private CategoryDTOMapperStruct mapper;
	private CategoryServiceImpl categoryService;
	private KanpekiDummyDataUtil kanpekiDummyDataUtil;

	private List<Category> listCatDummy;
	private List<ResponseCategoryDTO> listCatDummyResponse;
	private ResponseCategoryDTO catDummyResponse;

	@BeforeEach
	public void setup() {
		categoryService = new CategoryServiceImpl();
		mapper = Mockito.mock(CategoryDTOMapperStruct.class);
		catRepo = Mockito.mock(CategoryRepository.class);

		categoryService.mapper = mapper;
		categoryService.catRepo = catRepo;

		kanpekiDummyDataUtil = new KanpekiDummyDataUtil();

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findAllCategories Returns Correct List of Objects")
	void whenCallingFindAllCategories_thenShouldReturnCorrectListOfObjects() throws Exception {
		listCatDummy = new ArrayList<>();
		listCatDummy = (List<Category>) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoriesRepository.json", Optional.of(listCatDummy)).get();
		when(catRepo.findAll()).thenReturn(listCatDummy);

		listCatDummyResponse = new ArrayList<>();
		listCatDummyResponse = (List<ResponseCategoryDTO>) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoriesResponse.json", Optional.of(listCatDummyResponse)).get();

		when(mapper.toCategoryDTOList(any())).thenReturn(listCatDummyResponse);

		assertEquals(categoryService.findAllCategories(), listCatDummyResponse);

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findCategoriesByMatcher is Given family Returns Correct List of Objects")
	void whenCallingFindCategoriesByMatcher_givenFamily_thenShouldReturnCorrectListOfObjects() throws Exception {
		listCatDummy = new ArrayList<>();
		listCatDummy = (List<Category>) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoriesRepository.json", Optional.of(listCatDummy)).get();
		when(catRepo.findAll()).thenReturn(listCatDummy);

		listCatDummyResponse = new ArrayList<>();
		listCatDummyResponse = (List<ResponseCategoryDTO>) kanpekiDummyDataUtil
				.getJsonDummyData("searchCategoriesResponse.json", Optional.of(listCatDummyResponse)).get();

		when(mapper.toCategoryDTOList(any())).thenReturn(listCatDummyResponse);

		assertEquals(categoryService.findAllCategories(), listCatDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Correct Id Returns Correct Object")
	void whenCallingFindById_givenCorrectId_thenShouldReturnCorrectObject() throws Exception {
		Category dummyCat = new Category(1L, "Lesson 1", "Family", false, null, null, null);
		when(catRepo.findById(1L)).thenReturn(Optional.of(dummyCat));

		catDummyResponse = (ResponseCategoryDTO) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoryResponse.json", Optional.of(new ResponseCategoryDTO())).get();

		when(mapper.toCategoryDTO(any())).thenReturn(catDummyResponse);

		assertEquals(categoryService.findById(1L), Optional.of(catDummyResponse));

	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingFindById_givenIncorrectId_thenShouldThrowDataNotFoundException() throws Exception {
		when(catRepo.findById(1L)).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			categoryService.findById(1L);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addCategory is Given Correct RequestCategoryDTO Returns Correct Object")
	void whenCallingAddCategory_givenCorrectRequestCategoryDTO_thenShouldReturnCorrectObject() throws Exception {
		RequestCategoryDTO dummyRequestCatDTO = new RequestCategoryDTO("Lesson 1", "Family", false);
		Category dummyCat1 = new Category();
		dummyCat1.setUnitName(dummyRequestCatDTO.getUnitName());
		dummyCat1.setCategoryName(dummyRequestCatDTO.getCategoryName());
		dummyCat1.setIsQuestion(dummyRequestCatDTO.getIsQuestion());

		when(catRepo.countCategoriesUnique(anyString(), anyString())).thenReturn(0);
		when(mapper.requestCategoryDTOtoCategory(dummyRequestCatDTO)).thenReturn(dummyCat1);

		Category dummyCat2 = new Category(1L, dummyCat1.getUnitName(), dummyCat1.getCategoryName(),
				dummyCat1.getIsQuestion(), null, null, null);

		when(catRepo.save(dummyCat1)).thenReturn(dummyCat2);

		catDummyResponse = (ResponseCategoryDTO) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoryResponse.json", Optional.of(new ResponseCategoryDTO())).get();

		when(mapper.toCategoryDTO(any())).thenReturn(catDummyResponse);

		assertEquals(categoryService.addCategory(dummyRequestCatDTO), catDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When addCategory is Given Incorrect RequestCategoryDTO Throws CategoryAlreadyExistsException")
	void whenCallingAddCategory_givenIncorrectRequestCategoryDTO_thenThrowCategoryAlreadyExistsException()
			throws Exception {
		RequestCategoryDTO dummyRequestCatDTO = new RequestCategoryDTO("Lesson 1", "Family", false);
		when(catRepo.countCategoriesUnique(anyString(), anyString())).thenReturn(1);

		assertThrows(CategoryAlreadyExistsException.class, () -> {
			categoryService.addCategory(dummyRequestCatDTO);
		});

	}

	@Test
	@DisplayName("Test Should Pass When removeCategoryById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingRemoveCategoryById_givenIncorrectId_thenThrowDataNotFoundException() throws Exception {
		when(catRepo.findById(1L)).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			categoryService.removeCategoryById(1L);
		});
	}

	@Test
	@DisplayName("Test Should Pass When removeCategoryById is Given Id of Category with children Throws InvalidOperationOnCategoryException")
	void whenCallingRemoveCategoryById_givenIncorrectId_thenThrowInvalidOperationOnCategoryException()
			throws Exception {
		Category dummyCat = (Category) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoryRepository.json", Optional.of(new Category())).get();
		when(catRepo.findById(1L)).thenReturn(Optional.of(dummyCat));

		assertThrows(InvalidOperationOnCategoryException.class, () -> {
			categoryService.removeCategoryById(1L);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateCategory is Given Correct RequestCategoryDTO and ID Returns Correct Object")
	void whenCallingupdateCategory_givenCorrectRequestCategoryDTOAndId_thenShouldReturnCorrectObject()
			throws Exception {
		RequestCategoryDTO dummyRequestCatDTO = new RequestCategoryDTO("Lesson 1", "Family", false);
		Category dummyCat1 = new Category();
		dummyCat1.setUnitName(dummyRequestCatDTO.getUnitName());
		dummyCat1.setCategoryName(dummyRequestCatDTO.getCategoryName());
		dummyCat1.setIsQuestion(dummyRequestCatDTO.getIsQuestion());

		when(catRepo.findByUnitNameAndCategoryName(dummyRequestCatDTO.getUnitName(),
				dummyRequestCatDTO.getCategoryName())).thenReturn(Optional.ofNullable(null));

		when(mapper.requestCategoryDTOtoCategory(dummyRequestCatDTO)).thenReturn(dummyCat1);
		when(catRepo.findById(1L)).thenReturn(Optional.of(dummyCat1));
		when(catRepo.save(dummyCat1)).thenReturn(dummyCat1);

		catDummyResponse = (ResponseCategoryDTO) kanpekiDummyDataUtil
				.getJsonDummyData("getCategoryResponse.json", Optional.of(new ResponseCategoryDTO())).get();

		when(mapper.toCategoryDTO(any())).thenReturn(catDummyResponse);

		assertEquals(categoryService.updateCategory(dummyRequestCatDTO, 1L), catDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When updateCategory is Given Existing Category Throws CategoryAlreadyExistsException")
	void whenCallingupdateCategory_givenExistingCategory_thenThrowCategoryAlreadyExistsException() throws Exception {
		RequestCategoryDTO dummyRequestCatDTO = new RequestCategoryDTO("Lesson 1", "Family", false);
		Category dummyCat1 = new Category();
		dummyCat1.setId(1L);
		dummyCat1.setUnitName(dummyRequestCatDTO.getUnitName());
		dummyCat1.setCategoryName(dummyRequestCatDTO.getCategoryName());
		dummyCat1.setIsQuestion(dummyRequestCatDTO.getIsQuestion());

		when(catRepo.findByUnitNameAndCategoryName(dummyRequestCatDTO.getUnitName(),
				dummyRequestCatDTO.getCategoryName())).thenReturn(Optional.of(dummyCat1));

		when(catRepo.countCategoriesUnique(anyString(), anyString())).thenReturn(2);

		assertThrows(CategoryAlreadyExistsException.class, () -> {
			categoryService.updateCategory(dummyRequestCatDTO, 2L);
		});

	}

}
