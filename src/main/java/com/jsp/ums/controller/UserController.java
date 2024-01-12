package com.jsp.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.requestdto.UserResponse;
import com.jsp.ums.serviceimpl.UserServiceimpl;
import com.jsp.ums.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	UserServiceimpl service;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
		return service.saveUser(userRequest);
	}

	@PutMapping("/users/{userid}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int userid,
			@RequestBody User user) {
		return service.updateUser(userid, user);
	}

	@DeleteMapping("/users/{userid}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userid) {
		return service.deleteUser(userid);
	}
}
