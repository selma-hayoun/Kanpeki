package com.dam.kanpeki.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dam.kanpeki.exception.DataNotFoundException;
import com.dam.kanpeki.exception.ParameterIncorrectFormatException;
import com.dam.kanpeki.model.User;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.model.dto.mapper.UserDTOMapperStruct;
import com.dam.kanpeki.service.FileSystemStorageServiceI;
import com.dam.kanpeki.service.UserServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("kanpeki/users")
public class UserController {

	@Autowired
	private UserServiceI uService;

	@Autowired
	private FileSystemStorageServiceI storeService;

	@Autowired
	private UserDTOMapperStruct mapper;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "getUsers", notes = "Get all users from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> getUsers() {
		List<User> uList = uService.findUsersOrderByDate();

		if (uList.isEmpty()) {
			throw new DataNotFoundException("");
		} else {
			return ResponseEntity.ok(mapper.toUserDTOList(uList.stream()));
		}
	}

	@ApiOperation(value = "getUser", notes = "Get a user by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseUserDTO> getUser(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {
		Optional<User> opUser = uService.findById(id);

		if (!opUser.isPresent()) {
			throw new DataNotFoundException("");
		} else {
			return ResponseEntity.ok(mapper.toUserDTO(opUser.get()));
		}
	}

	@ApiOperation(value = "addNewUser", notes = "Create a new user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/v1", produces = {
			"application/json" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDTO> addNewUserV1(@Valid @RequestPart(value = "u") RequestUserDTO u,
			@RequestPart(value = "file", required = false) MultipartFile file) {

		String urlImg = "";

		if (file != null) {
			// Almacenamos el fichero y obtenemos su URL

			if (!file.isEmpty()) {
				String img = storeService.store(file);
				urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", img, null).build()
						.toUriString();
			}
			// Seteamos la URL donde está almacenada
//			u.setUrlImage(urlImg);
		}

		User uTemp = mapper.requestUserDTOtoUser(u);
		uTemp.setUrlImage(urlImg);

		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toUserDTO(uService.addUser(uTemp)));

	}

	@ApiOperation(value = "addNewUser", notes = "Create a new user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/v2", produces = { "application/json" }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDTO> addNewUserV2(
			@Valid @Parameter(description = "User attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestUserDTO u,
			@Parameter(description = "Word image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file) {

		String urlImg = "";

		if (file != null) {
			// Almacenamos el fichero y obtenemos su URL

			if (!file.isEmpty()) {
				String img = storeService.store(file);
				urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", img, null).build()
						.toUriString();
			}
			// Seteamos la URL donde está almacenada
//			u.setUrlImage(urlImg);
		}

		User uTemp = mapper.requestUserDTOtoUser(u);
		uTemp.setUrlImage(urlImg);

		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toUserDTO(uService.addUser(uTemp)));

	}

	@ApiOperation(value = "deleteUser", notes = "Delete a single user by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseUserDTO> deleteUser(
			@PathVariable("id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {
		Optional<User> opUser = uService.findById(id);

		if (!opUser.isPresent()) {
			throw new DataNotFoundException("");
		} else {
			// Eliminamos la imagen del almacenamiento
			storeService.delete(opUser.get().getUrlImage());
			uService.removeUserById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateUser", notes = "Update the data from an existing user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/v1/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseUserDTO> updateUserV1(@Valid @RequestPart(value = "u") RequestUserDTO u,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@PathVariable("id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		Optional<User> opUser = uService.findById(id);

		if (opUser.isPresent()) {

			String urlImg = "";

			if (file != null) {
				// Eliminamos la imagen anterior del almacenamiento
				storeService.delete(opUser.get().getUrlImage());

				// Almacenamos el fichero y obtenemos su URL
				if (!file.isEmpty()) {
					String img = storeService.store(file);
					urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", img, null)
							.build().toUriString();
				}
				// Seteamos la URL donde está almacenada
//				u.setUrlImage(urlImg);
			}

			User mappedU = mapper.requestUserDTOtoUser(u);
			mappedU.setUrlImage(urlImg);

//			User mappedU = mapper.updateUserDTOtoUser(u);

			User mappedUUpdated = opUser.map(newU -> {
				newU.setEmail(u.getEmail());
				newU.setPassword(mappedU.getPassword());
				newU.setFullName(mappedU.getFullName());
				newU.setNickname(mappedU.getNickname());
				newU.setUrlImage(mappedU.getUrlImage());
				newU.setBirthday(mappedU.getBirthday());
				newU.setCity(mappedU.getCity());
				newU.setRoles(mappedU.getRoles());
				uService.updateUser(newU);
				return newU;
			}).orElseThrow(() -> new DataNotFoundException(""));

			return ResponseEntity.ok(mapper.toUserDTO(mappedUUpdated));

		} else {
			throw new DataNotFoundException("");
		}

	}

	@ApiOperation(value = "updateUser", notes = "Update the data from an existing user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/v2/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseUserDTO> updateUserV2(
			@Valid @Parameter(description = "User attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestUserDTO u,
			@Parameter(description = "User image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file,
			@PathVariable("id") @ApiParam(name = "id", value = "User id", example = "1") Long id) {

		Optional<User> opUser = uService.findById(id);

		if (opUser.isPresent()) {

			String urlImg = "";

			if (file != null) {
				// Eliminamos la imagen anterior del almacenamiento
				storeService.delete(opUser.get().getUrlImage());

				// Almacenamos el fichero y obtenemos su URL
				if (!file.isEmpty()) {
					String img = storeService.store(file);
					urlImg = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", img, null)
							.build().toUriString();
				}
				// Seteamos la URL donde está almacenada
//				u.setUrlImage(urlImg);
			}

			User mappedU = mapper.requestUserDTOtoUser(u);
			mappedU.setUrlImage(urlImg);

//			User mappedU = mapper.updateUserDTOtoUser(u);

			User mappedUUpdated = opUser.map(newU -> {
				newU.setEmail(u.getEmail());
				newU.setPassword(mappedU.getPassword());
				newU.setFullName(mappedU.getFullName());
				newU.setNickname(mappedU.getNickname());
				newU.setUrlImage(mappedU.getUrlImage());
				newU.setBirthday(mappedU.getBirthday());
				newU.setCity(mappedU.getCity());
				newU.setRoles(mappedU.getRoles());
				uService.updateUser(newU);
				return newU;
			}).orElseThrow(() -> new DataNotFoundException(""));

			return ResponseEntity.ok(mapper.toUserDTO(mappedUUpdated));

		} else {
			throw new DataNotFoundException("");
		}

	}

	@ApiOperation(value = "searchUsers", notes = "Search users by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> searchUsers(
			@RequestParam(name = "uString") @ApiParam(name = "uString", value = "email, full_name or nickname", example = "Alice") String uString) {
		List<User> uList = uService.findUsersByMatcher(uString);

		if (uList.isEmpty()) {
			throw new DataNotFoundException("No users contain the string");
		} else {
			return ResponseEntity.ok(mapper.toUserDTOList(uList.stream()));
		}
	}

	@ApiOperation(value = "searchUsersByBirthdate", notes = "Search users by birthdate between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/birthdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> searchUsersByBirthdate(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<User> uList = null;

		try {
			uList = uService.findUsersBirthdayBetweenDates(new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		}

		if (uList == null) {
			throw new ParameterIncorrectFormatException("Dates format are incorrect. Correct pattern: yyyy-MM-dd");
		} else if (uList.isEmpty()) {
			throw new DataNotFoundException("No birthdays between " + startDate + " and " + endDate);
		} else {
			return ResponseEntity.ok(mapper.toUserDTOList(uList.stream()));
		}
	}

	@ApiOperation(value = "searchUsersByCreatedAtDate", notes = "Search users by creation date between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = ResponseUserDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/creation", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<ResponseUserDTO>> searchUsersByCreatedAtDate(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<User> uList = null;

		try {
			uList = uService.findUsersCreatedAtBetweenDates(new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		}

		if (uList == null) {
			throw new ParameterIncorrectFormatException("Dates format are incorrect. Correct pattern: yyyy-MM-dd");
		} else if (uList.isEmpty()) {
			throw new DataNotFoundException("No users created between " + startDate + " and " + endDate);
		} else {
			return ResponseEntity.ok(mapper.toUserDTOList(uList.stream()));
		}
	}

}
