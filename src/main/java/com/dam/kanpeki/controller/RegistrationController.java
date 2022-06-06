package com.dam.kanpeki.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dam.kanpeki.model.UserRole;
import com.dam.kanpeki.model.dto.RequestUserDTO;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.service.UserServiceI;
import com.dam.kanpeki.service.impl.EmailService;
import com.dam.kanpeki.utils.KanpekiConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("kanpeki/registers")
public class RegistrationController {

	@Autowired
	private UserServiceI uService;

	@Autowired
	private EmailService eService;

	@ApiOperation(value = "addNewUser", notes = "Create a new user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = KanpekiConstants.CONTROLLER_MSG_200, response = ResponseUserDTO.class),
			@ApiResponse(code = 201, message = KanpekiConstants.CONTROLLER_MSG_201, response = ResponseUserDTO.class),
			@ApiResponse(code = 400, message = KanpekiConstants.CONTROLLER_MSG_400),
			@ApiResponse(code = 401, message = KanpekiConstants.CONTROLLER_MSG_401),
			@ApiResponse(code = 403, message = KanpekiConstants.CONTROLLER_MSG_403),
			@ApiResponse(code = 404, message = KanpekiConstants.CONTROLLER_MSG_404),
			@ApiResponse(code = 500, message = KanpekiConstants.CONTROLLER_MSG_500) })
	@RequestMapping(value = "/v1", produces = {
			"application/json" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDTO> addNewUserV1(@Valid @RequestPart(value = "u") RequestUserDTO u,
			@RequestPart(value = "file", required = false) MultipartFile file) {

		// Seteamos a el rol a PENDING_APPROVAL
		Set<UserRole> roles = new HashSet<>();
		roles.add(UserRole.PENDING_APPROVAL);
		u.setRoles(roles);

		ResponseUserDTO savedUser = uService.addUser(u, file);
		if (savedUser != null) {
			eService.sendRegistrationMail(u.getEmail());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

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
	@RequestMapping(value = "/v2", produces = { "application/json" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<ResponseUserDTO> addNewUserV2(
			@Valid @Parameter(description = "User attributes", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) @ModelAttribute RequestUserDTO u,
			@Parameter(description = "Word image file", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart(value = "file", required = false) MultipartFile file) {

		// Seteamos a el rol a PENDING_APPROVAL
		Set<UserRole> roles = new HashSet<>();
		roles.add(UserRole.PENDING_APPROVAL);
		u.setRoles(roles);

		ResponseUserDTO savedUser = uService.addUser(u, file);
		if (savedUser != null) {
			eService.sendRegistrationMail(u.getEmail());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

	}

}
