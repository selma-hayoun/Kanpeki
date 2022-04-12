package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.Answer;
import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.model.dto.AnswerDTO;
import com.dam.kanpeki.model.dto.CreateQuestionDTO;
import com.dam.kanpeki.model.dto.GetQuestionDTO;

@Mapper(componentModel = "spring")
public interface DTOMapperStruct {

	List<GetQuestionDTO> toQuestionDTOList(Stream<Question> stream);

	List<Question> toQuestionList(Stream<CreateQuestionDTO> stream);

	GetQuestionDTO toQuestionDTO(Question q);

	Question createQuestionDTOtoQuestion(CreateQuestionDTO q);

	Question getQuestionDTOtoQuestion(GetQuestionDTO q);

	List<AnswerDTO> toAnswerDTOList(Stream<Answer> stream);

	List<Answer> toAnswerList(Stream<AnswerDTO> stream);

	AnswerDTO toAnswerDTO(Answer a);

	Answer toAnswer(AnswerDTO a);

}
