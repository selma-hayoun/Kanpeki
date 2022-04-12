package com.dam.kanpeki.model.dto.mapper;

import java.util.List;
import java.util.stream.Stream;

import org.mapstruct.Mapper;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.dto.CreateUserDTO;
import com.dam.kanpeki.model.dto.GetUserDTO;
import com.dam.kanpeki.model.dto.UpdateUserDTO;

@Mapper(componentModel = "spring")
public interface UserDTOMapperStruct {

	List<GetUserDTO> toUserDTOList(Stream<User> stream);

	List<User> toUserList(Stream<CreateUserDTO> stream);

	GetUserDTO toUserDTO(User u);

	User createUserDTOtoUser(CreateUserDTO u);

	User getUserDTOtoUser(GetUserDTO u);

	User updateUserDTOtoUser(UpdateUserDTO u);

}
