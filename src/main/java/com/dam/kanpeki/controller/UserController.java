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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.service.UserServiceI;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserServiceI uService;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "getUsers", notes = "Get all users from our database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
//		List<User> uList = uService.findAllUsers();
		List<User> uList = uService.findUsersOrderByDate();

		if (uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users registered");
		} else {
			return ResponseEntity.ok(uList);
		}
	}

	@ApiOperation(value = "getUser", notes = "Get a user by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<User> getUser(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "3") Long id) {
		Optional<User> opWord = uService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		} else {
			return ResponseEntity.ok(opWord.get());
		}
	}

	@ApiOperation(value = "addNewUser", notes = "Create a new user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<User> addNewUser(@Valid @RequestBody User u) {

		return ResponseEntity.status(HttpStatus.CREATED).body(uService.addUser(u));

	}

	@ApiOperation(value = "deleteUser", notes = "Delete a single user by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "3") Long id) {
		Optional<User> opWord = uService.findById(id);

		if (!opWord.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		} else {
			uService.removeUserById(id);
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "updateUser", notes = "Update the data from an existing user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@Valid @RequestBody User u,
			@RequestParam(name = "id") @ApiParam(name = "id", value = "User id", example = "3") Long id) {

		return uService.findById(id).map(newU -> {
			newU.setEmail(u.getEmail());
			newU.setPassword(u.getPassword());
			newU.setFullName(u.getFullName());
			newU.setNickname(u.getNickname());
			newU.setUrlImage(u.getUrlImage());
			newU.setBirthday(u.getBirthday());
			newU.setCity(u.getCity());
			newU.setRoles(u.getRoles());
			uService.updateUser(newU);
			return ResponseEntity.ok(newU);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

	}

	@ApiOperation(value = "searchUsers", notes = "Search users by string")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/{uString}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> searchUsers(
			@RequestParam(name = "uString") @ApiParam(name = "uString", value = "email, full_name or nickname", example = "Alice") String uString) {
		List<User> uList = uService.findUsersByMatcher(uString);

		if (uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users contain the string");
		} else {
			return ResponseEntity.ok(uList);
		}
	}

	@ApiOperation(value = "searchUsersByBirthdate", notes = "Search users by birthdate between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/birthdate/{startDate}{endDate}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> searchUsersByBirthdate(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<User> uList = null;

		try {
			uList = uService.findUsersBirthdayBetweenDates(new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		}

		if (uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No birthdays between " + startDate + " and " + endDate);
		} else {
			return ResponseEntity.ok(uList);
		}
	}

	@ApiOperation(value = "searchUsersByCreatedAtDate", notes = "Search users by creation date between dates")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Resources obtained correctly", response = User.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Unexpected error") })
	@RequestMapping(value = "/user/creation/{startDate}{endDate}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> searchUsersByCreatedAtDate(
			@RequestParam(name = "startDate") @ApiParam(name = "startDate", value = "Search from date", example = "2000-01-01") String startDate,
			@RequestParam(name = "endDate") @ApiParam(name = "endDate", value = "to date", example = "2010-12-31") String endDate) {

		List<User> uList = null;

		try {
			uList = uService.findUsersCreatedAtBetweenDates(new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		}

		if (uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No users created between " + startDate + " and " + endDate);
		} else {
			return ResponseEntity.ok(uList);
		}
	}

}
