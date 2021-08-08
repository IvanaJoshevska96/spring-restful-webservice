package com.example.demo.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exceptionhandler.UserServiceException;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImplementation;
import com.example.demo.ui.model.request.UpdateDetailsRequest;
import com.example.demo.ui.model.request.UserDetailsRequest;
import com.example.demo.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users + method

public class UserController {

	Map<String, UserRest> users;

	@Autowired
	UserService userService;

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		if (true)
			throw new UserServiceException("A UserService Exception");

		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	}

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
		return "Get User Info with page " + page + " limit of " + limit + " sort is " + sort;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequest userDetails) {

		UserRest returnValue = userService.createUser(userDetails);

		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);

	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateDetailsRequest userDetails) {

		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setName(userDetails.getName());
		storedUserDetails.setLastName(userDetails.getLastName());

		users.put(userId, storedUserDetails);
		return storedUserDetails;

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {

		users.remove(id);
		return ResponseEntity.noContent().build();

	}

}
