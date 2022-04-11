package com.dam.kanpeki.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

	@Autowired
	private CategoryRepository catRepo;

	@Override
	public List<Category> findAllCategories() {
		return catRepo.findAll();
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
	public List<Category> findCategoriesByMatcher(String wField) {
		Category cat = new Category();
		cat.setUnitName(wField);
		cat.setCategoryName(wField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny()
				.withMatcher("unitName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("categoryName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Category> catExample = Example.of(cat, customExMatcher);

		return catRepo.findAll(catExample);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return catRepo.findById(id);
	}

	@Override
	public Category addWord(Category cat) {
		return catRepo.save(cat);
	}

	@Override
	public void removeCategoryById(Long id) {
		catRepo.deleteById(id);
	}

	@Override
	public void updateCategory(Category cat) {
		catRepo.save(cat);
	}

}
