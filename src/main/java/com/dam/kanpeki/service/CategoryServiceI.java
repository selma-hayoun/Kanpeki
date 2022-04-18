package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.dto.RequestCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;

public interface CategoryServiceI {

	public List<ResponseCategoryDTO> findAllCategories();

	public List<Category> findByIsQuestionTrue();

	public List<Category> findByIsQuestionFalse();

	public List<ResponseCategoryDTO> findCategoriesByMatcher(String wField);

	public Optional<ResponseCategoryDTO> findById(Long id);

	public ResponseCategoryDTO addWord(RequestCategoryDTO cat);

	public void removeCategoryById(Long id);

	public ResponseCategoryDTO updateCategory(RequestCategoryDTO cat, Long id);

}
