package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.dto.RequestUpdateUserDTO;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;

@Mapper(componentModel = "spring")
public interface UserDTOMapperStruct {

	List<ResponseUserDTO> toUserDTOList(Stream<User> stream);

	List<User> toUserList(Stream<RequestUserDTO> stream);

	ResponseUserDTO toUserDTO(User u);

	User requestUserDTOtoUser(RequestUserDTO u);

	User responseUserDTOtoUser(ResponseUserDTO u);

	User requestUpdateUserDTOtoUser(RequestUpdateUserDTO u);

}
