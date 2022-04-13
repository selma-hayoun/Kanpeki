package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.dto.RequestCategoryDTO;
import com.dam.kanpeki.model.dto.ResponseCategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryDTOMapperStruct {

	List<ResponseCategoryDTO> toCategoryDTOList(Stream<Category> stream);

	List<Category> toCategoryList(Stream<RequestCategoryDTO> stream);

	ResponseCategoryDTO toCategoryDTO(Category cat);

	Category requestCategoryDTOtoCategory(RequestCategoryDTO cat);

	Category responseCategoryDTOtoCategory(ResponseCategoryDTO cat);

}
