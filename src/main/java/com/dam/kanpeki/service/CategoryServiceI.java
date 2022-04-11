package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Category;

public interface CategoryServiceI {

	public List<Category> findAllCategories();

	public List<Category> findByIsQuestionTrue();

	public List<Category> findByIsQuestionFalse();

	public List<Category> findCategoriesByMatcher(String wField);

	public Optional<Category> findById(Long id);

	public Category addWord(Category cat);

	public void removeCategoryById(Long id);

	public void updateCategory(Category cat);

}
