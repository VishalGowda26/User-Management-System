package com.jsp.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.serviceimpl.UserServiceimpl;

@RestController
public class UserController {

	@Autowired
	UserServiceimpl service;

	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
}
