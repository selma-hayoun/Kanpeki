package com.dam.kanpeki.controller;

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
import com.dam.kanpeki.model.dto.RequestCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.service.CategoryServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("kanpeki/categories")
public class CategoryController {

	@Autowired
	private CategoryServiceI catService;

	@ApiOperation(value = "getCategories", notes = "Get all categories from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseCategoryDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseCategoryDTO>> getCategories() {
		List<ResponseCategoryDTO> catList = catService.findAllCategories();

		if (catList.isEmpty()) {
			throw new DataNotFoundException("");
		} else {
			return ResponseEntity.ok(catList);
		}
	}

	@ApiOperation(value = "getCategory", notes = "Get a category by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseCategoryDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseCategoryDTO> getCategory(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "category id", example = "1") Long id) {
		Optional<ResponseCategoryDTO> opCat = catService.findById(id);

		if (!opCat.isPresent()) {
			throw new DataNotFoundException("");
		} else {
			return ResponseEntity.ok(opCat.get());
		}
	}

	@ApiOperation(value = "addNewCategory", notes = "Create a new category")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseCategoryDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseCategoryDTO> addNewCategory(@Valid @RequestBody RequestCategoryDTO cat) {
		return ResponseEntity.status(HttpStatus.CREATED).body(catService.addWord(cat));
	}

	@ApiOperation(value = "deleteCategory", notes = "Delete a single category by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseCategoryDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseCategoryDTO> deleteCategory(
			@PathVariable("id") @ApiParam(name = "id", value = "category id", example = "1") Long id) {
		Optional<ResponseCategoryDTO> opCat = catService.findById(id);

		if (!opCat.isPresent()) {
			throw new DataNotFoundException("");
		} else {
			catService.removeCategoryById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateCategory", notes = "Update the data from an existing category")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseCategoryDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseCategoryDTO> updateCategory(@Valid @RequestBody RequestCategoryDTO cat,
			@PathVariable("id") @ApiParam(name = "id", value = "category id", example = "1") Long id) {

		return ResponseEntity.ok(catService.updateCategory(cat, id));

	}

	@ApiOperation(value = "searchCategories", notes = "Search categories by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseCategoryDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/category/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseCategoryDTO>> searchCategories(
			@RequestParam(name = "catString") @ApiParam(name = "catString", value = "Unit name or Category name", example = "family") String catString) {
		List<ResponseCategoryDTO> catList = catService.findCategoriesByMatcher(catString);

		if (catList.isEmpty()) {
			throw new DataNotFoundException("No categories contain the string");
		} else {
			return ResponseEntity.ok(catList);
		}
	}
}
