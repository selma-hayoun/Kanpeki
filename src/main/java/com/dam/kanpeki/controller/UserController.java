package com.dam.kanpeki.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.ParameterIncorrectFormatException;
import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.dto.RequestUpdateUserDTO;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.model.dto.mapper.UserDTOMapperStruct;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.UserServiceI;
import com.dam.kanpeki.utils.KanpekiConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("kanpeki/users")
public class UserController {

	@Autowired
	private UserServiceI uService;

	@Autowired
	private FileSystemStorageServiceI storeService;

	@Autowired
	private UserDTOMapperStruct mapper;

	@ApiOperation(value = "getUsers", notes = "Get all users from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = KanpekiConstants.EMPTY_STRING, produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> getUsers() {
		List<ResponseUserDTO> uList = uService.findUsersOrderByDate();

		if (uList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(uList);
		}
	}

	@ApiOperation(value = "getUser", notes = "Get a user by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseUserDTO> getUser(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {
		Optional<ResponseUserDTO> opUser = uService.findById(id);

		if (!opUser.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			return ResponseEntity.ok(opUser.get());
		}
	}

	/**
	 * REVISAR: Método para autenticación - autorización
	 * 
	 * @param theUser
	 * @return
	 */
	@ApiOperation(value = "me", notes = "Get user me")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/me", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseUserDTO> me(@AuthenticationPrincipal User theUser) {
		return ResponseEntity.ok(mapper.toUserDTO(theUser));
	}

	@ApiOperation(value = "addNewUser", notes = "Create a new user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 201, message = KanpekiConstants.CONTROLLER_MSG_201, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/v1", produces = {
			"application/json" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDTO> addNewUserV1(@Valid @RequestPart(value = "u") RequestUserDTO u,
			@RequestPart(value = "file", required = false) MultipartFile file) {

		return ResponseEntity.status(HttpStatus.CREATED).body(uService.addUser(u, file));

	}

	@ApiOperation(value = "addNewUser", notes = "Create a new user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 201, message = KanpekiConstants.CONTROLLER_MSG_201, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/v2", produces = { "application/json" }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDTO> addNewUserV2(
			@Valid @Parameter(description = "User attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestUserDTO u,
			@Parameter(description = "Word image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file) {

		return ResponseEntity.status(HttpStatus.CREATED).body(uService.addUser(u, file));

	}

	@ApiOperation(value = "deleteUser", notes = "Delete a single user by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 204, message = KanpekiConstants.CONTROLLER_MSG_204),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseUserDTO> deleteUser(
			@PathVariable("id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {
		Optional<ResponseUserDTO> opUser = uService.findById(id);

		if (!opUser.isPresent()) {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		} else {
			uService.removeUserById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateUser", notes = "Update the data from an existing user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/v1/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseUserDTO> updateUserV1(@Valid @RequestPart(value = "u") RequestUpdateUserDTO u,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@PathVariable("id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		Optional<ResponseUserDTO> opUser = uService.findById(id);

		if (opUser.isPresent()) {

			if (file != null) {
				// Eliminamos la imagen anterior del almacenamiento
				storeService.delete(opUser.get().getUrlImage());
			}

			return ResponseEntity.ok(uService.updateUser(u, file, id));

		} else {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		}

	}

	@ApiOperation(value = "updateUser", notes = "Update the data from an existing user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/v2/{id}",
//	consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, 
			produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseUserDTO> updateUserV2(
			@Valid @Parameter(description = "User attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestUpdateUserDTO u,
			@Parameter(description = "User image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file,
			@PathVariable("id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		Optional<ResponseUserDTO> opUser = uService.findById(id);

		if (opUser.isPresent()) {

			if (file != null) {
				// Eliminamos la imagen anterior del almacenamiento
				storeService.delete(opUser.get().getUrlImage());
			}

			return ResponseEntity.ok(uService.updateUser(u, file, id));

		} else {
			throw new DataNotFoundException(KanpekiConstants.EMPTY_STRING);
		}

	}

	@ApiOperation(value = "searchUsers", notes = "Search users by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> searchUsers(
			@RequestParam(name = "uString") @ApiParam(name = "uString", value = "email, full_name or nickname", example = "Alice") String uString) {
		List<ResponseUserDTO> uList = uService.findUsersByMatcher(uString);

		if (uList.isEmpty()) {
			throw new DataNotFoundException(KanpekiConstants.DATA_NOT_FOUND_EX_USER_BY_STRING);
		} else {
			return ResponseEntity.ok(uList);
		}
	}

	@ApiOperation(value = "searchUsersByBirthdate", notes = "Search users by birthdate between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/birthdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> searchUsersByBirthdate(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<ResponseUserDTO> uList = null;

		try {
			uList = uService.findUsersBirthdayBetweenDates(
					new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(startDate),
					new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(endDate));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		if (uList == null) {
			throw new ParameterIncorrectFormatException(KanpekiConstants.PARAMETER_INCORRECT_FORMAT_EX_DATES);
		} else if (uList.isEmpty()) {
			throw new DataNotFoundException(
					String.format(KanpekiConstants.DATA_NOT_FOUND_EX_USERS_BIRTHDAY_BY_DATES, startDate, endDate));
		} else {
			return ResponseEntity.ok(uList);
		}
	}

	@ApiOperation(value = "searchUsersByCreatedAtDate", notes = "Search users by creation date between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/user/creation", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> searchUsersByCreatedAtDate(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<ResponseUserDTO> uList = null;

		try {
			uList = uService.findUsersCreatedAtBetweenDates(
					new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(startDate),
					new SimpleDateFormat(KanpekiConstants.DATE_FORMAT).parse(endDate));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		if (uList == null) {
			throw new ParameterIncorrectFormatException(KanpekiConstants.PARAMETER_INCORRECT_FORMAT_EX_DATES);
		} else if (uList.isEmpty()) {
			throw new DataNotFoundException(
					String.format(KanpekiConstants.DATA_NOT_FOUND_EX_USERS_CREATED_BY_DATES, startDate, endDate));
		} else {
			return ResponseEntity.ok(uList);
		}
	}

}
