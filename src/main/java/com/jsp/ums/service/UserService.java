package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.requestdto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestParam int userid, @RequestBody User user);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userid);

	public ResponseEntity<ResponseStructure<UserResponse>> getUser(@PathVariable int userid);

	public ResponseEntity<ResponseStructure<List<UserResponse>>> getUsers();

}
