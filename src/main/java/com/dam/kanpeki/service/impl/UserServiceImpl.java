package com.dam.kanpeki.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.model.Question;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.model.dto.mapper.UserDTOMapperStruct;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.ResultServiceI;
import com.dam.kanpeki.utils.FileUtils;
import com.dam.kanpeki.utils.KanpekiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.repository.UserRepository;
import com.dam.kanpeki.service.UserServiceI;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private UserDTOMapperStruct mapper;

	@Autowired
	private FileSystemStorageServiceI storeService;

	@Autowired
	private ResultServiceI rService;

	@Override
	public List<User> findAllUsers() {
		return uRepo.findAll();
	}

	@Override
	public List<ResponseUserDTO> findUsersOrderByDate() {

		return mapper.toUserDTOList(uRepo.findUsersOrderByDate().stream());
	}

	@Override
	public List<ResponseUserDTO> findUsersCreatedAtBetweenDates(Date startDate, Date endDate) {
		return mapper.toUserDTOList(uRepo.findUsersCreatedAtBetweenDates(startDate, endDate).stream());
	}

	@Override
	public List<ResponseUserDTO> findUsersBirthdayBetweenDates(Date startDate, Date endDate) {
		return mapper.toUserDTOList(uRepo.findUsersBirthdayBetweenDates(startDate, endDate).stream());
	}

	@Override
	public List<User> findByCity(String city) {
		return uRepo.findByCity(city);
	}

	@Override
	public List<ResponseUserDTO> findUsersByMatcher(String uField) {
		User u = new User();
		u.setEmail(uField);
		u.setFullName(uField);
		u.setNickname(uField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("fullName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<User> userExample = Example.of(u, customExMatcher);

		return mapper.toUserDTOList(uRepo.findAll(userExample).stream());
	}

	@Override
	public Optional<ResponseUserDTO> findById(Long id) {
		Optional<User> opUser = uRepo.findById(id);
		if(!opUser.isPresent()){
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return Optional.of(mapper.toUserDTO(uRepo.findById(id).get()));
		}
	}

	@Override
	public ResponseUserDTO addUser(RequestUserDTO u, MultipartFile file) {
		User uTemp = mapper.requestUserDTOtoUser(u);
		uTemp.setUrlImage(FileUtils.saveFileRequest(file));
		return mapper.toUserDTO(uRepo.save(uTemp));
	}

	@Override
	public void removeUserById(Long id) {
		Optional<User> u = uRepo.findById(id);
		// Eliminamos la imagen del almacenamiento
		storeService.delete(u.get().getUrlImage());

		// Eliminamos sus resultados si los tuviera
		if (!u.get().getResults().isEmpty()) {
			rService.deleteResultsByUserId(id);
		}

		uRepo.deleteById(id);
	}

	@Override
	public ResponseUserDTO updateUser(RequestUserDTO u, MultipartFile file, Long id) {

		User mappedU = mapper.requestUserDTOtoUser(u);
		mappedU.setUrlImage(FileUtils.saveFileRequest(file));

		User mappedUUpdated = uRepo.findById(id).map(newU -> {
			newU.setEmail(mappedU.getEmail());
			newU.setPassword(mappedU.getPassword());
			newU.setFullName(mappedU.getFullName());
			newU.setNickname(mappedU.getNickname());
			newU.setUrlImage(mappedU.getUrlImage());
			newU.setBirthday(mappedU.getBirthday());
			newU.setCity(mappedU.getCity());
			newU.setRoles(mappedU.getRoles());
			uRepo.save(newU);
			return newU;
		}).orElseThrow(() -> new DataNotFoundException(KanpekiConstants.EMPTY_STRING));

		return mapper.toUserDTO(mappedUUpdated);
	}

}
