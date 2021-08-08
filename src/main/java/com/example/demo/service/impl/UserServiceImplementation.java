package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.service.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.ui.model.request.UserDetailsRequest;
import com.example.demo.ui.model.response.UserRest;

@Service
public class UserServiceImplementation implements UserService {

	Map<String, UserRest> users;

	Utils utils;

	@Autowired
	public UserServiceImplementation(Utils utils) {
		this.utils = utils;
	}

	public UserServiceImplementation() {

	}

	@Override
	public UserRest createUser(UserDetailsRequest userDetails) {

		UserRest returnValue = new UserRest();

		returnValue.setName(userDetails.getName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmail(userDetails.getEmail());

		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		if (users == null)
			users = new HashMap<>();
		users.put(userId, returnValue);
		return returnValue;

	}

}
