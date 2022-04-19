package com.dam.kanpeki.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.InvalidOperationOnCategoryException;
import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.dto.RequestCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.mapper.CategoryDTOMapperStruct;
import com.dam.kanpeki.repository.CategoryRepository;
import com.dam.kanpeki.service.CategoryServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private CategoryDTOMapperStruct mapper;

	@Override
	public List<ResponseCategoryDTO> findAllCategories() {
		List<Category> catList = catRepo.findAll();
		return mapper.toCategoryDTOList(catList.stream());
	}

	@Override
	public List<Category> findByIsQuestionTrue() {
		return catRepo.findByIsQuestionTrue();
	}

	@Override
	public List<Category> findByIsQuestionFalse() {
		return catRepo.findByIsQuestionFalse();
	}

	@Override
	public List<ResponseCategoryDTO> findCategoriesByMatcher(String wField) {
		Category cat = new Category();
		cat.setUnitName(wField);
		cat.setCategoryName(wField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny()
				.withMatcher(KanpekiConstants.CATEGORY_UNIT_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher(KanpekiConstants.CATEGORY_CAT_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Category> catExample = Example.of(cat, customExMatcher);

		return mapper.toCategoryDTOList(catRepo.findAll(catExample).stream());
	}

	@Override
	public Optional<ResponseCategoryDTO> findById(Long id) {
		Optional<Category> opCat = catRepo.findById(id);

		if (!opCat.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return Optional.of(mapper.toCategoryDTO(opCat.get()));
		}
	}

	@Override
	public ResponseCategoryDTO addWord(RequestCategoryDTO cat) {

		return mapper.toCategoryDTO(catRepo.save(mapper.requestCategoryDTOtoCategory(cat)));
	}

	@Override
	public void removeCategoryById(Long id) {
		Optional<Category> opCat = catRepo.findById(id);

		if (!opCat.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			if ((!opCat.get().getWords().isEmpty()) || (!opCat.get().getQuestions().isEmpty())
					|| (!opCat.get().getResults().isEmpty())) {

				throw new InvalidOperationOnCategoryException((!opCat.get().getWords().isEmpty()
						? KanpekiConstants.TABLE_WORDS_NAME.concat(String.valueOf(opCat.get().getWords().size()))
						: KanpekiConstants.EMPTY_STRING)
						+ (!opCat.get().getWords().isEmpty() ? KanpekiConstants.TABLE_QUESTIONS_NAME
								.concat(String.valueOf(opCat.get().getWords().size())) : KanpekiConstants.EMPTY_STRING)
						+ (!opCat.get().getWords().isEmpty()
								? KanpekiConstants.TABLE_RESULTS_NAME
										.concat(String.valueOf(opCat.get().getWords().size()))
								: KanpekiConstants.EMPTY_STRING));
			}

			catRepo.deleteById(id);
		}

	}

	@Override
	public ResponseCategoryDTO updateCategory(RequestCategoryDTO cat, Long id) {
		Category mappedCat = mapper.requestCategoryDTOtoCategory(cat);

		Category mappedCatUpdated = catRepo.findById(id).map(newCat -> {
			newCat.setUnitName(mappedCat.getUnitName());
			newCat.setCategoryName(mappedCat.getCategoryName());
			newCat.setIsQuestion(mappedCat.getIsQuestion());
			catRepo.save(newCat);
			return newCat;
		}).orElseThrow(() -> new DataNotFoundException(""));

		return mapper.toCategoryDTO(mappedCatUpdated);

	}

}
