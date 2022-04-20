package com.dam.kanpeki.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;

public interface UserServiceI {

	public List<User> findAllUsers();

	public List<ResponseUserDTO> findUsersOrderByDate();

	public List<ResponseUserDTO> findUsersCreatedAtBetweenDates(Date startDate, Date endDate);

	public List<ResponseUserDTO> findUsersBirthdayBetweenDates(Date startDate, Date endDate);

	public List<User> findByCity(String city);

	public List<ResponseUserDTO> findUsersByMatcher(String uField);

	public Optional<ResponseUserDTO> findById(Long id);

	public ResponseUserDTO addUser(RequestUserDTO u, MultipartFile file);

	public void removeUserById(Long id);

	public ResponseUserDTO updateUser(RequestUserDTO u, MultipartFile file, Long id);

	public Optional<User> findByEmail(String email);

}
