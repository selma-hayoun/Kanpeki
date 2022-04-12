package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.dto.CreateResultDTO;
import com.dam.kanpeki.model.dto.GetResultDTO;

@Mapper(componentModel = "spring")
public interface ResultDTOMapperStruct {

	@Mapping(target = "userId", source = "id.userId")
	@Mapping(target = "resultDate", source = "id.resultDate")
	List<GetResultDTO> toResultDTOList(Stream<Result> stream);

	@Mapping(target = "id.userId", source = "userId")
	List<Result> toResultList(Stream<CreateResultDTO> stream);

	@Mapping(target = "userId", source = "id.userId")
	@Mapping(target = "resultDate", source = "id.resultDate")
	GetResultDTO toResultDTO(Result r);

	@Mapping(target = "id.userId", source = "userId")
	Result createResultDTOtoResult(CreateResultDTO r);

	@Mapping(target = "id.userId", source = "userId")
	@Mapping(target = "id.resultDate", source = "resultDate")
	Result getResultDTOtoResult(GetResultDTO r);
}
