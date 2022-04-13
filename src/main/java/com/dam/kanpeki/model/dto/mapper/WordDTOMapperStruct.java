package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.model.dto.RequestWordDTO;
import com.dam.kanpeki.model.dto.ResponseWordDTO;

@Mapper(componentModel = "spring")
public interface WordDTOMapperStruct {

	List<ResponseWordDTO> toWordDTOList(Stream<Word> stream);

	List<Word> toWordList(Stream<RequestWordDTO> stream);

	ResponseWordDTO toWordDTO(Word cat);

	Word requestWordDTOtoWord(RequestWordDTO cat);

	Word responseWordDTOtoWord(ResponseWordDTO cat);

}
