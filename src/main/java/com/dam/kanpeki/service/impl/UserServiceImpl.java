package com.dam.kanpeki.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.UserEmailAlreadyExistsException;
import com.dam.kanpeki.exception.UserNicknameAlreadyExistsException;
import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.model.dto.mapper.UserDTOMapperStruct;
import com.dam.kanpeki.repository.UserRepository;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.ResultServiceI;
import com.dam.kanpeki.service.UserServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

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

	@Autowired
	private PasswordEncoder passEncoder;

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
				.withMatcher(KanpekiConstants.USER_EMAIL_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher(KanpekiConstants.USER_FULLNAME_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher(KanpekiConstants.USER_NICKNAME_NAME,
						ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<User> userExample = Example.of(u, customExMatcher);

		return mapper.toUserDTOList(uRepo.findAll(userExample).stream());
	}

	@Override
	public Optional<ResponseUserDTO> findById(Long id) {
		Optional<User> opUser = uRepo.findById(id);

		if (!opUser.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return Optional.of(mapper.toUserDTO(opUser.get()));
		}
	}

	@Override
	public ResponseUserDTO addUser(RequestUserDTO u, MultipartFile file) {

		String userNick = u.getNickname();

		if (userNick != null && !userNick.isBlank() && (uRepo.countUserNicknameUnique(userNick) != 0)) {
			throw new UserNicknameAlreadyExistsException();
		} else {
			User uTemp = mapper.requestUserDTOtoUser(u);
			uTemp.setUrlImage(storeService.saveFileRequest(file));
			uTemp.setPassword(passEncoder.encode(u.getPassword()));// Codificamos la password

			// Otra forma de controlar el error de violated constraint
			try {
				return mapper.toUserDTO(uRepo.save(uTemp));
			} catch (DataIntegrityViolationException ex) {
				throw new UserEmailAlreadyExistsException();
			}
		}

	}

	@Override
	public void removeUserById(Long id) {
		Optional<User> u = uRepo.findById(id);

		if (!u.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			// Eliminamos la imagen del almacenamiento
			storeService.delete(u.get().getUrlImage());

			// Eliminamos sus resultados si los tuviera
			if (!u.get().getResults().isEmpty()) {
				rService.deleteResultsByUserId(id);
			}

			uRepo.deleteById(id);
		}
	}

	@Override
	public ResponseUserDTO updateUser(RequestUserDTO u, MultipartFile file, Long id) {

		String userNick = u.getNickname();
		Optional<User> uTemp = uRepo.findByNickname(userNick);

		if (uTemp.isPresent() && userNick != null && !userNick.isBlank()
				&& (uRepo.countUserNicknameUnique(userNick) >= 1) && (!Objects.equals(uTemp.get().getId(), id))) {
			throw new UserNicknameAlreadyExistsException();
		} else {
			User mappedU = mapper.requestUserDTOtoUser(u);
			mappedU.setUrlImage(storeService.saveFileRequest(file));

			try {
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

			} catch (DataIntegrityViolationException ex) {
				throw new UserEmailAlreadyExistsException();
			}

		}

	}

	/**
	 * Método de verificación de usuario. Uso interno
	 */
	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> u = uRepo.findByEmail(email);

		if (!u.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return u;
		}
	}

}
