package com.dam.kanpeki.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.model.custom.ResultPerCategoryData;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseQuestionDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.model.dto.ResponseWordDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KanpekiDummyDataUtil {

	private ObjectMapper objectMapper;
	private ClassLoader classLoader;
	private File file;

	public KanpekiDummyDataUtil() {
		this.objectMapper = new ObjectMapper();
		this.classLoader = getClass().getClassLoader();
	}

	public Optional<?> getJsonDummyDataCategory(String resourceName, Optional<?> objectType) {

		Optional<?> result = null;

		// Parseamos el objeto del json
		try {
			file = new File(classLoader.getResource(resourceName).getFile());

			if (objectType.get() instanceof ResponseCategoryDTO) {
				result = Optional.of(objectMapper.readValue(file, ResponseCategoryDTO.class));
			} else if (objectType.get() instanceof Category) {
				result = Optional.of(objectMapper.readValue(file, Category.class));
			} else {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<Category>>() {
				}));
			}

		} catch (IOException e) {
			log.info("getJsonDummyDataCategory() parsing json file ERROR - " + e.getMessage());
		}
		return result;
	}

	public Optional<?> getJsonDummyDataQuestion(String resourceName, Optional<?> objectType,
			Optional<?> objectTypeElements) {

		Optional<?> result = null;

		// Parseamos el objeto del json
		try {
			file = new File(classLoader.getResource(resourceName).getFile());

			if (objectType.get() instanceof ResponseQuestionDTO) {
				result = Optional.of(objectMapper.readValue(file, ResponseQuestionDTO.class));
			} else if (objectType.get() instanceof Question) {
				result = Optional.of(objectMapper.readValue(file, Question.class));
			} else if (objectTypeElements != null && objectTypeElements.get() instanceof Question) {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<Question>>() {
				}));
			} else {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<ResponseQuestionDTO>>() {
				}));
			}

		} catch (IOException e) {
			log.info("getJsonDummyDataQuestion() parsing json file ERROR - " + e.getMessage());
		}
		return result;
	}

	public Optional<?> getJsonDummyDataResult(String resourceName, Optional<?> objectType,
			Optional<?> objectTypeElements) {

		Optional<?> result = null;

		// Parseamos el objeto del json
		try {
			file = new File(classLoader.getResource(resourceName).getFile());

			if (objectType.get() instanceof ResponseResultDTO) {
				result = Optional.of(objectMapper.readValue(file, ResponseResultDTO.class));
			} else if (objectType.get() instanceof Result) {
				result = Optional.of(objectMapper.readValue(file, Result.class));
			} else if (objectTypeElements != null && objectTypeElements.get() instanceof Result) {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<Result>>() {
				}));
			} else if (objectTypeElements != null && objectTypeElements.get() instanceof ResultPerCategoryData) {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<ResultPerCategoryData>>() {
				}));
			} else {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<ResponseResultDTO>>() {
				}));
			}

		} catch (IOException e) {
			log.info("getJsonDummyDataResult() parsing json file ERROR - " + e.getMessage());
		}
		return result;
	}

	public Optional<?> getJsonDummyDataUser(String resourceName, Optional<?> objectType,
			Optional<?> objectTypeElements) {

		Optional<?> result = null;

		// Parseamos el objeto del json
		try {
			file = new File(classLoader.getResource(resourceName).getFile());

			if (objectType.get() instanceof ResponseUserDTO) {
				result = Optional.of(objectMapper.readValue(file, ResponseUserDTO.class));
			} else if (objectType.get() instanceof User) {
				result = Optional.of(objectMapper.readValue(file, User.class));
			} else if (objectTypeElements != null && objectTypeElements.get() instanceof User) {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<User>>() {
				}));
			} else {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<ResponseUserDTO>>() {
				}));
			}

		} catch (IOException e) {
			log.info("getJsonDummyDataUser() parsing json file ERROR - " + e.getMessage());
		}
		return result;
	}

	public Optional<?> getJsonDummyDataWord(String resourceName, Optional<?> objectType,
			Optional<?> objectTypeElements) {

		Optional<?> result = null;

		// Parseamos el objeto del json
		try {
			file = new File(classLoader.getResource(resourceName).getFile());

			if (objectType.get() instanceof ResponseWordDTO) {
				result = Optional.of(objectMapper.readValue(file, ResponseWordDTO.class));
			} else if (objectType.get() instanceof Word) {
				result = Optional.of(objectMapper.readValue(file, Word.class));
			} else if (objectTypeElements != null && objectTypeElements.get() instanceof Word) {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<Word>>() {
				}));
			} else {
				result = Optional.of(objectMapper.readValue(file, new TypeReference<List<ResponseWordDTO>>() {
				}));
			}

		} catch (IOException e) {
			log.info("getJsonDummyDataWord() parsing json file ERROR - " + e.getMessage());
		}
		return result;
	}

}
