package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.Word;
import com.dam.kanpeki.model.dto.CreateWordDTO;
import com.dam.kanpeki.model.dto.GetWordDTO;

@Mapper(componentModel = "spring")
public interface WordDTOMapperStruct {

	List<GetWordDTO> toWordDTOList(Stream<Word> stream);

	List<Word> toWordList(Stream<CreateWordDTO> stream);

	GetWordDTO toWordDTO(Word cat);

	Word createWordDTOtoWord(CreateWordDTO cat);

	Word getWordDTOtoWord(GetWordDTO cat);

}
