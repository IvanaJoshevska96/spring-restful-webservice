package com.example.demo.service;

import com.example.demo.ui.model.request.UserDetailsRequest;
import com.example.demo.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequest userDetails);

}
