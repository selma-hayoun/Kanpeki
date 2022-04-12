package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.Category;
import com.dam.kanpeki.model.dto.CreateCategoryDTO;
import com.dam.kanpeki.model.dto.GetCategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryDTOMapperStruct {

	List<GetCategoryDTO> toCategoryDTOList(Stream<Category> stream);

	List<Category> toCategoryList(Stream<CreateCategoryDTO> stream);

	GetCategoryDTO toCategoryDTO(Category cat);

	Category createCategoryDTOtoCategory(CreateCategoryDTO cat);

	Category getCategoryDTOtoCategory(GetCategoryDTO cat);

}
