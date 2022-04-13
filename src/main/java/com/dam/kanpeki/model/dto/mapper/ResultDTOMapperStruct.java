package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dam.kanpeki.model.Result;
import com.dam.kanpeki.model.dto.RequestResultDTO;
import com.dam.kanpeki.model.dto.ResponseResultDTO;

@Mapper(componentModel = "spring")
public interface ResultDTOMapperStruct {

	@Mapping(target = "userId", source = "id.userId")
	@Mapping(target = "resultDate", source = "id.resultDate")
	List<ResponseResultDTO> toResultDTOList(Stream<Result> stream);

	@Mapping(target = "id.userId", source = "userId")
	List<Result> toResultList(Stream<RequestResultDTO> stream);

	@Mapping(target = "userId", source = "id.userId")
	@Mapping(target = "resultDate", source = "id.resultDate")
	ResponseResultDTO toResultDTO(Result r);

	@Mapping(target = "id.userId", source = "userId")
	Result requestResultDTOtoResult(RequestResultDTO r);

	@Mapping(target = "id.userId", source = "userId")
	@Mapping(target = "id.resultDate", source = "resultDate")
	Result responseResultDTOtoResult(ResponseResultDTO r);
}
