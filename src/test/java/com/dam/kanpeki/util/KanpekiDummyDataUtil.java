package com.dam.kanpeki.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;
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

	public Optional<?> getJsonDummyData(String resourceName, Optional<?> objectType) {

//		ClassLoader classLoader = getClass().getClassLoader();
//		File file;
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
			log.info("getJsonDummyData() parsing json file ERROR - " + e.getMessage());
		}
		return result;
	}

}
