package com.dam.kanpeki.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.UserEmailAlreadyExistsException;
import com.dam.kanpeki.exception.UserNicknameAlreadyExistsException;
import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.UserRole;
import com.dam.kanpeki.model.dto.RequestUpdateUserDTO;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.model.dto.mapper.UserDTOMapperStruct;
import com.dam.kanpeki.repository.UserRepository;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.ResultServiceI;
import com.dam.kanpeki.util.KanpekiDummyDataUtil;
import com.dam.kanpeki.util.KanpekiTestsConstants;

class UserServiceTest {

	private UserServiceImpl userService;
	private UserRepository uRepo;
	private UserDTOMapperStruct mapper;
	private FileSystemStorageServiceI storeService;
	private ResultServiceI rService;
	private PasswordEncoder passEncoder;
	private KanpekiDummyDataUtil kanpekiDummyDataUtil;

	private List<User> listUserDummy;
	private List<ResponseUserDTO> listUserDummyResponse;
	private ResponseUserDTO uDummyResponse;

	@BeforeEach
	public void setup() {
		userService = new UserServiceImpl();

		uRepo = Mockito.mock(UserRepository.class);
		storeService = Mockito.mock(FileSystemStorageServiceI.class);
		rService = Mockito.mock(ResultServiceI.class);
		mapper = Mockito.mock(UserDTOMapperStruct.class);
		passEncoder = Mockito.mock(PasswordEncoder.class);

		userService.uRepo = uRepo;
		userService.mapper = mapper;
		userService.storeService = storeService;
		userService.rService = rService;
		userService.passEncoder = passEncoder;

		kanpekiDummyDataUtil = new KanpekiDummyDataUtil();

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findUsersOrderByDate Returns Correct List of Objects")
	void whenCallingFindUsersOrderByDate_thenShouldReturnCorrectListOfObjects() throws Exception {
		User dummyU = new User();
		listUserDummy = new ArrayList<>();
		listUserDummy = (List<User>) kanpekiDummyDataUtil
				.getJsonDummyDataUser("getUsersRepository.json", Optional.of(listUserDummy), Optional.of(dummyU)).get();
		when(uRepo.findUsersOrderByDate()).thenReturn(listUserDummy);

		listUserDummyResponse = new ArrayList<>();
		listUserDummyResponse = (List<ResponseUserDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataUser("getUsersResponse.json", Optional.of(listUserDummyResponse), null).get();

		when(mapper.toUserDTOList(any())).thenReturn(listUserDummyResponse);

		assertEquals(userService.findUsersOrderByDate(), listUserDummyResponse);

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findUsersCreatedAtBetweenDates Given Correct Dates Returns Correct List of Objects")
	void whenCallingFindUsersCreatedAtBetweenDates_givenCorrectDates_thenShouldReturnCorrectListOfObjects()
			throws Exception {
		String startDate = "2022-01-01";
		String endDate = "2022-12-31";

		User dummyU = new User();
		listUserDummy = new ArrayList<>();
		listUserDummy = (List<User>) kanpekiDummyDataUtil.getJsonDummyDataUser("searchBetweenDatesUserRepository.json",
				Optional.of(listUserDummy), Optional.of(dummyU)).get();
		when(uRepo.findUsersCreatedAtBetweenDates(
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(endDate))).thenReturn(listUserDummy);

		listUserDummyResponse = new ArrayList<>();
		listUserDummyResponse = (List<ResponseUserDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataUser("searchBetweenDatesUserResponse.json", Optional.of(listUserDummyResponse), null)
				.get();

		when(mapper.toUserDTOList(any())).thenReturn(listUserDummyResponse);

		assertEquals(userService.findUsersCreatedAtBetweenDates(
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(endDate)), listUserDummyResponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findUsersBirthdayBetweenDates Given Correct Dates Returns Correct List of Objects")
	void whenCallingFindUsersBirthdayBetweenDates_givenCorrectDates_thenShouldReturnCorrectListOfObjects()
			throws Exception {
		String startDate = "1989-12-01";
		String endDate = "1990-01-01";

		User dummyU = new User();
		listUserDummy = new ArrayList<>();
		listUserDummy = (List<User>) kanpekiDummyDataUtil.getJsonDummyDataUser(
				"searchBetweenDatesBirthdayUserRepository.json", Optional.of(listUserDummy), Optional.of(dummyU)).get();
		when(uRepo.findUsersCreatedAtBetweenDates(
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(endDate))).thenReturn(listUserDummy);

		listUserDummyResponse = new ArrayList<>();
		listUserDummyResponse = (List<ResponseUserDTO>) kanpekiDummyDataUtil.getJsonDummyDataUser(
				"searchBetweenDatesBirthdayUserResponse.json", Optional.of(listUserDummyResponse), null).get();

		when(mapper.toUserDTOList(any())).thenReturn(listUserDummyResponse);

		assertEquals(userService.findUsersCreatedAtBetweenDates(
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(endDate)), listUserDummyResponse);
		assertEquals(userService.findUsersCreatedAtBetweenDates(
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(startDate),
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(endDate)).size(), 1);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Test Should Pass When findUsersByMatcher is Given string mar Returns Correct List of Objects")
	void whenCallingFindUsersByMatcher_givenMar_thenShouldReturnCorrectListOfObjects() throws Exception {
		String searchedStr = "mar";

		listUserDummy = new ArrayList<>();
		listUserDummy = (List<User>) kanpekiDummyDataUtil
				.getJsonDummyDataUser("getUsersRepository.json", Optional.of(listUserDummy), Optional.of(new User()))
				.get();

		when(uRepo.findAll()).thenReturn(listUserDummy);

		listUserDummyResponse = new ArrayList<>();
		listUserDummyResponse = (List<ResponseUserDTO>) kanpekiDummyDataUtil
				.getJsonDummyDataUser("searchUsersResponse.json", Optional.of(listUserDummyResponse), null).get();

		when(mapper.toUserDTOList(any())).thenReturn(listUserDummyResponse);

		assertEquals(userService.findUsersByMatcher(searchedStr), listUserDummyResponse);
	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Correct Id Returns Correct Object")
	void whenCallingFindById_givenCorrectId_thenShouldReturnCorrectObject() throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		User dummyU = new User();
		dummyU.setId(KanpekiTestsConstants.ID);
		dummyU.setEmail(KanpekiTestsConstants.USER_EMAIL_EXAMPLE);
		dummyU.setPassword(KanpekiTestsConstants.USER_PASSWORD_EXAMPLE);
		dummyU.setFullName(KanpekiTestsConstants.USER_NAME_EXAMPLE);
		dummyU.setNickname(KanpekiTestsConstants.USER_NICKNAME_EXAMPLE);
		dummyU.setBirthday(new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT)
				.parse(KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE));
		dummyU.setCity(KanpekiTestsConstants.USER_CITY_EXAMPLE);
		dummyU.setRoles(roles);

		when(uRepo.findById(any())).thenReturn(Optional.of(dummyU));

		uDummyResponse = (ResponseUserDTO) kanpekiDummyDataUtil
				.getJsonDummyDataUser("getUserResponse.json", Optional.of(new ResponseUserDTO()), null).get();

		when(mapper.toUserDTO(any())).thenReturn(uDummyResponse);

		assertEquals(userService.findById(KanpekiTestsConstants.ID), Optional.of(uDummyResponse));
		assertEquals(userService.findById(KanpekiTestsConstants.ID).get().getBirthday(), uDummyResponse.getBirthday());

	}

	@Test
	@DisplayName("Test Should Pass When findById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingFindById_givenIncorrectId_thenShouldThrowDataNotFoundException() throws Exception {
		when(uRepo.findById(KanpekiTestsConstants.ID)).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			userService.findById(KanpekiTestsConstants.ID);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addUser is Given Correct RequestUserDTO Returns Correct Object")
	void whenCallingAddUser_givenCorrectRequestUserDTO_thenShouldReturnCorrectObject() throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		RequestUserDTO dummyRequestUserDTO = new RequestUserDTO(KanpekiTestsConstants.USER_EMAIL_EXAMPLE,
				KanpekiTestsConstants.USER_PASSWORD_EXAMPLE, KanpekiTestsConstants.USER_NAME_EXAMPLE,
				KanpekiTestsConstants.USER_NICKNAME_EXAMPLE, null,
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(
						KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE),
				KanpekiTestsConstants.USER_CITY_EXAMPLE, roles);

		User dummyU1 = new User();
		dummyU1.setEmail(dummyRequestUserDTO.getEmail());
		dummyU1.setPassword(dummyRequestUserDTO.getPassword());
		dummyU1.setFullName(dummyRequestUserDTO.getFullName());
		dummyU1.setNickname(dummyRequestUserDTO.getNickname());
		dummyU1.setUrlImage(dummyRequestUserDTO.getFile() == null ? KanpekiTestsConstants.EMPTY_STRING
				: dummyRequestUserDTO.getFile().getName());
		dummyU1.setBirthday(dummyRequestUserDTO.getBirthday());
		dummyU1.setCity(dummyRequestUserDTO.getCity());
		dummyU1.setRoles(dummyRequestUserDTO.getRoles());
		dummyU1.setCreatedAt(null);
		dummyU1.setLastPasswordChangeAt(null);

		when(mapper.requestUserDTOtoUser(dummyRequestUserDTO)).thenReturn(dummyU1);

		User dummyU2 = new User();
		dummyU2.setId(KanpekiTestsConstants.ID);
		dummyU2.setEmail(dummyU1.getEmail());
		dummyU2.setPassword(dummyU1.getPassword());
		dummyU2.setFullName(dummyU1.getFullName());
		dummyU2.setNickname(dummyU1.getNickname());
		dummyU2.setUrlImage(dummyU1.getUrlImage());
		dummyU2.setBirthday(dummyU1.getBirthday());
		dummyU2.setCity(dummyU1.getCity());
		dummyU2.setRoles(dummyU1.getRoles());
		dummyU2.setCreatedAt(dummyU1.getCreatedAt());
		dummyU2.setLastPasswordChangeAt(dummyU1.getLastPasswordChangeAt());

		when(uRepo.save(dummyU1)).thenReturn(dummyU2);

		uDummyResponse = (ResponseUserDTO) kanpekiDummyDataUtil
				.getJsonDummyDataUser("getUserResponse.json", Optional.of(new ResponseUserDTO()), null).get();

		when(mapper.toUserDTO(any())).thenReturn(uDummyResponse);

		when(storeService.saveFileRequest(any())).thenReturn(KanpekiTestsConstants.EMPTY_STRING);

		assertEquals(userService.addUser(dummyRequestUserDTO, null), uDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When addUser is Given Nickname Already Exists Throws UserNicknameAlreadyExistsException")
	void whenCallingAddUser_givenNicknameAlreadyExists_thenThrowUserNicknameAlreadyExistsException() throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		RequestUserDTO dummyRequestUserDTO = new RequestUserDTO(KanpekiTestsConstants.USER_EMAIL_EXAMPLE,
				KanpekiTestsConstants.USER_PASSWORD_EXAMPLE, KanpekiTestsConstants.USER_NAME_EXAMPLE,
				KanpekiTestsConstants.USER_NICKNAME_EXAMPLE, null,
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(
						KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE),
				KanpekiTestsConstants.USER_CITY_EXAMPLE, roles);

		when(uRepo.countUserNicknameUnique(anyString())).thenReturn(1);

		assertThrows(UserNicknameAlreadyExistsException.class, () -> {
			userService.addUser(dummyRequestUserDTO, null);
		});
	}

	@Test
	@DisplayName("Test Should Pass When addUser is Given Email Already Exists Throws UserEmailAlreadyExistsException")
	void whenCallingAddUser_givenEmailAlreadyExists_thenThrowUserEmailAlreadyExistsException() throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		RequestUserDTO dummyRequestUserDTO = new RequestUserDTO(KanpekiTestsConstants.USER_EMAIL_EXAMPLE,
				KanpekiTestsConstants.USER_PASSWORD_EXAMPLE, KanpekiTestsConstants.USER_NAME_EXAMPLE,
				KanpekiTestsConstants.USER_NICKNAME_EXAMPLE, null,
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(
						KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE),
				KanpekiTestsConstants.USER_CITY_EXAMPLE, roles);

		User dummyU1 = new User();
		dummyU1.setEmail(dummyRequestUserDTO.getEmail());
		dummyU1.setPassword(dummyRequestUserDTO.getPassword());
		dummyU1.setFullName(dummyRequestUserDTO.getFullName());
		dummyU1.setNickname(dummyRequestUserDTO.getNickname());
		dummyU1.setUrlImage(dummyRequestUserDTO.getFile() == null ? KanpekiTestsConstants.EMPTY_STRING
				: dummyRequestUserDTO.getFile().getName());
		dummyU1.setBirthday(dummyRequestUserDTO.getBirthday());
		dummyU1.setCity(dummyRequestUserDTO.getCity());
		dummyU1.setRoles(dummyRequestUserDTO.getRoles());
		dummyU1.setCreatedAt(null);
		dummyU1.setLastPasswordChangeAt(null);

		when(mapper.requestUserDTOtoUser(dummyRequestUserDTO)).thenReturn(dummyU1);

		when(storeService.saveFileRequest(any())).thenReturn(KanpekiTestsConstants.EMPTY_STRING);

		when(uRepo.save(any())).thenThrow(DataIntegrityViolationException.class);

		assertThrows(UserEmailAlreadyExistsException.class, () -> {
			userService.addUser(dummyRequestUserDTO, null);
		});
	}

	@Test
	@DisplayName("Test Should Pass When removeUserById is Given Incorrect Id Throws DataNotFoundException")
	void whenCallingRemoveUserById_givenIncorrectId_thenThrowDataNotFoundException() throws Exception {
		when(uRepo.findById(anyLong())).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			userService.removeUserById(KanpekiTestsConstants.ID);
		});
	}

	@Test
	@DisplayName("Test Should Pass When updateUser is Given Correct RequestUserDTO and Id Returns Correct Object")
	void whenCallingUpdateUser_givenCorrectRequestUserDTOAndId_thenShouldReturnCorrectObject() throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		RequestUpdateUserDTO dummyRequestUserDTO = new RequestUpdateUserDTO(KanpekiTestsConstants.USER_EMAIL_EXAMPLE,
				KanpekiTestsConstants.USER_PASSWORD_EXAMPLE, KanpekiTestsConstants.USER_NAME_EXAMPLE,
				KanpekiTestsConstants.USER_NICKNAME_EXAMPLE, null,
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(
						KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE),
				KanpekiTestsConstants.USER_CITY_EXAMPLE, roles);

		User dummyU = new User();
		dummyU.setEmail(dummyRequestUserDTO.getEmail());
		dummyU.setPassword(dummyRequestUserDTO.getPassword());
		dummyU.setFullName(dummyRequestUserDTO.getFullName());
		dummyU.setNickname(dummyRequestUserDTO.getNickname());
		dummyU.setUrlImage(dummyRequestUserDTO.getFile() == null ? KanpekiTestsConstants.EMPTY_STRING
				: dummyRequestUserDTO.getFile().getName());
		dummyU.setBirthday(dummyRequestUserDTO.getBirthday());
		dummyU.setCity(dummyRequestUserDTO.getCity());
		dummyU.setRoles(dummyRequestUserDTO.getRoles());
		dummyU.setCreatedAt(null);
		dummyU.setLastPasswordChangeAt(null);

		when(uRepo.findByNickname(anyString())).thenReturn(Optional.ofNullable(null));

		when(mapper.requestUpdateUserDTOtoUser(dummyRequestUserDTO)).thenReturn(dummyU);

		when(storeService.saveFileRequest(any())).thenReturn(KanpekiTestsConstants.EMPTY_STRING);

		when(uRepo.findById(anyLong())).thenReturn(Optional.of(dummyU));

		when(uRepo.save(dummyU)).thenReturn(dummyU);

		uDummyResponse = (ResponseUserDTO) kanpekiDummyDataUtil
				.getJsonDummyDataUser("getUserResponse.json", Optional.of(new ResponseUserDTO()), null).get();

		when(mapper.toUserDTO(any())).thenReturn(uDummyResponse);

		assertEquals(userService.updateUser(dummyRequestUserDTO, null, KanpekiTestsConstants.ID), uDummyResponse);

	}

	@Test
	@DisplayName("Test Should Pass When updateUser is Given Incorrect RequestUserDTO (Nickname) Throws UserNicknameAlreadyExistsException")
	void whenCallingUpdateUser_givenIncorrectRequestUserDTONickname_thenThrowUserNicknameAlreadyExistsException()
			throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		RequestUpdateUserDTO dummyRequestUserDTO = new RequestUpdateUserDTO(KanpekiTestsConstants.USER_EMAIL_EXAMPLE,
				KanpekiTestsConstants.USER_PASSWORD_EXAMPLE, KanpekiTestsConstants.USER_NAME_EXAMPLE,
				KanpekiTestsConstants.USER_NICKNAME_EXAMPLE, null,
				new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT).parse(
						KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE),
				KanpekiTestsConstants.USER_CITY_EXAMPLE, roles);

		User dummyU1 = new User();
		dummyU1.setId(KanpekiTestsConstants.ID);
		dummyU1.setEmail(dummyRequestUserDTO.getEmail());
		dummyU1.setPassword(dummyRequestUserDTO.getPassword());
		dummyU1.setFullName(dummyRequestUserDTO.getFullName());
		dummyU1.setNickname(dummyRequestUserDTO.getNickname());
		dummyU1.setUrlImage(dummyRequestUserDTO.getFile() == null ? KanpekiTestsConstants.EMPTY_STRING
				: dummyRequestUserDTO.getFile().getName());
		dummyU1.setBirthday(dummyRequestUserDTO.getBirthday());
		dummyU1.setCity(dummyRequestUserDTO.getCity());
		dummyU1.setRoles(dummyRequestUserDTO.getRoles());
		dummyU1.setCreatedAt(null);
		dummyU1.setLastPasswordChangeAt(null);

		User dummyU2 = new User();
		dummyU2.setId(KanpekiTestsConstants.ID_ALT);
		dummyU2.setEmail(dummyU1.getEmail());
		dummyU2.setPassword(dummyU1.getPassword());
		dummyU2.setFullName(dummyU1.getFullName());
		dummyU2.setNickname(dummyU1.getNickname());
		dummyU2.setUrlImage(dummyU1.getUrlImage());
		dummyU2.setBirthday(dummyU1.getBirthday());
		dummyU2.setCity(dummyU1.getCity());
		dummyU2.setRoles(dummyU1.getRoles());
		dummyU2.setCreatedAt(dummyU1.getCreatedAt());
		dummyU2.setLastPasswordChangeAt(dummyU1.getLastPasswordChangeAt());

		when(uRepo.findByNickname(anyString())).thenReturn(Optional.of(dummyU2));

		when(uRepo.countUserNicknameUnique(anyString())).thenReturn(2);

		assertThrows(UserNicknameAlreadyExistsException.class, () -> {
			userService.updateUser(dummyRequestUserDTO, null, KanpekiTestsConstants.ID);
		});

	}

	@Test
	@DisplayName("Test Should Pass When findByEmail is Given Correct Email Returns Correct Object")
	void whenCallingFindByEmail_givenCorrectEmail_thenShouldReturnCorrectObject() throws Exception {
		Set<UserRole> roles = new HashSet<UserRole>(Collections.singletonList(UserRole.USER));
		User dummyU = new User();
		dummyU.setId(KanpekiTestsConstants.ID);
		dummyU.setEmail(KanpekiTestsConstants.USER_EMAIL_EXAMPLE);
		dummyU.setPassword(KanpekiTestsConstants.USER_PASSWORD_EXAMPLE);
		dummyU.setFullName(KanpekiTestsConstants.USER_NAME_EXAMPLE);
		dummyU.setNickname(KanpekiTestsConstants.USER_NICKNAME_EXAMPLE);
		dummyU.setBirthday(new SimpleDateFormat(KanpekiTestsConstants.DATE_FORMAT)
				.parse(KanpekiTestsConstants.USER_BIRTHDAY_EXAMPLE));
		dummyU.setCity(KanpekiTestsConstants.USER_CITY_EXAMPLE);
		dummyU.setRoles(roles);

		when(uRepo.findByEmail(anyString())).thenReturn(Optional.of(dummyU));

		assertEquals(userService.findByEmail(dummyU.getEmail()), Optional.of(dummyU));
		assertEquals(userService.findByEmail(dummyU.getEmail()).get().getBirthday(), dummyU.getBirthday());

	}

	@Test
	@DisplayName("Test Should Pass When findByEmail is Given Incorrect Email Throws DataNotFoundException")
	void whenCallingFindByEmail_givenIncorrectEmail_thenThrowDataNotFoundException() throws Exception {
		when(uRepo.findByEmail(anyString())).thenReturn(Optional.ofNullable(null));

		assertThrows(DataNotFoundException.class, () -> {
			userService.findByEmail(KanpekiTestsConstants.EMPTY_STRING);
		});
	}

}
