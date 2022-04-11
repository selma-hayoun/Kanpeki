package com.dam.kanpeki.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.service.CategoryServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryServiceI catService;

	@ApiOperation(value = "getCategories", notes = "Get all categories from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Category.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> catList = catService.findAllCategories();

		if (catList != null && catList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No categories registered");
		} else {
			return ResponseEntity.ok(catList);
		}
	}

	@ApiOperation(value = "getCategory", notes = "Get a category by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Category.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Category> getCategory(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "category id", example = "3") Long id) {
		Optional<Category> opWord = catService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		} else {
			return ResponseEntity.ok(opWord.get());
		}
	}

	@ApiOperation(value = "addNewCategory", notes = "Create a new category")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Category.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Category> addNewCategory(@Valid @RequestBody Category cat) {
		return ResponseEntity.status(HttpStatus.CREATED).body(catService.addWord(cat));
	}

	@ApiOperation(value = "deleteCategory", notes = "Delete a single category by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Category.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "category id", example = "3") Long id) {
		Optional<Category> opWord = catService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		} else {
			catService.removeCategoryById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateCategory", notes = "Update the data from an existing category")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Category.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category cat,
			@RequestParam(name = "id") @ApiParam(name = "id", value = "category id", example = "3") Long id) {

		return catService.findById(id).map(newCat -> {
			newCat.setUnitName(cat.getUnitName());
			newCat.setCategoryName(cat.getCategoryName());
			newCat.setQuestion(cat.isQuestion());
			catService.updateCategory(newCat);
			return ResponseEntity.ok(newCat);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"));

	}

	@ApiOperation(value = "searchCategories", notes = "Search categories by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = Category.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/{catString}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> searchCategories(
			@RequestParam(name = "catString") @ApiParam(name = "catString", value = "Unit name or Category name", example = "family") String catString) {
		List<Category> catList = catService.findCategoriesByMatcher(catString);

		if (catList != null && catList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No categories contain the string");
		} else {
			return ResponseEntity.ok(catList);
		}
	}
}
