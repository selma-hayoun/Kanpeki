package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.Answer;
import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.model.dto.AnswerDTO;
import com.dam.kanpeki.model.dto.RequestQuestionDTO;
import com.dam.kanpeki.model.dto.ResponseQuestionDTO;

@Mapper(componentModel = "spring")
public interface QuestionAnswerDTOMapperStruct {

	List<ResponseQuestionDTO> toQuestionDTOList(Stream<Question> stream);

	List<Question> toQuestionList(Stream<RequestQuestionDTO> stream);

	ResponseQuestionDTO toQuestionDTO(Question q);

	Question requestQuestionDTOtoQuestion(RequestQuestionDTO q);

	Question responseQuestionDTOtoQuestion(ResponseQuestionDTO q);

	List<AnswerDTO> toAnswerDTOList(Stream<Answer> stream);

	List<Answer> toAnswerList(Stream<AnswerDTO> stream);

	AnswerDTO toAnswerDTO(Answer a);

	Answer toAnswer(AnswerDTO a);

}
