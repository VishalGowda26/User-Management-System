package com.jsp.ums.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exception.UserNotFoundException;
import com.jsp.ums.repo.UserRepo;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepo repo;
	@Autowired
	ResponseStructure<UserResponse> structure;
	@Autowired
	ResponseStructure<List<UserResponse>> lstructure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user2 = mapToUser(userRequest);
		user2 = repo.save(user2);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Data Saved Successfully");
		structure.setData(mapToUserResponse(user2));
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userid, User user) {
		User user1 = repo.findById(userid).map(u -> {
			user.setUserid(userid);
			return repo.save(user);
		}).orElseThrow(() -> new RuntimeException());

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Data Updated");
		structure.setData(mapToUserResponse(user1));

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> getUsers() {
		List<User> user1 = new ArrayList<User>();
		try {
			user1 = repo.findAll();

		} catch (Exception e) {
			throw new UserNotFoundException("No Users details available");
		}
		List<UserResponse> uresponse = new ArrayList<UserResponse>();
		for (User user : user1) {
			uresponse.add(mapToUserResponse(user));
		}
		lstructure.setStatus(HttpStatus.FOUND.value());
		lstructure.setMessage("Users Found Succcessfully");
		lstructure.setData(uresponse);
		return new ResponseEntity<ResponseStructure<List<UserResponse>>>(lstructure, HttpStatus.FOUND);
	}

// ANOTHER WAY
//	@Override
//	public ResponseEntity<ResponseStructure<User>> updateUser(int userid, User user) {
//		User user1 = repo.findById(userid).orElseThrow(() -> new RuntimeException());
//		user.setUserid(userid);
//		user1 = repo.save(user);
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setMessage("Data Updated");
//		structure.setData(user1);
//		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
//	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userid) {
		User user1 = repo.findById(userid).orElseThrow(() -> new UserNotFoundException("Pls enter the correct userid"));
		repo.delete(user1);
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Data Successfully Deleted");
		structure.setData(mapToUserResponse(user1));
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(int userid) {
		User user1 = repo.findById(userid).orElseThrow(() -> new UserNotFoundException("Given user with id not found"));
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("User details for the given id successfully fetched");
		structure.setData(mapToUserResponse(user1));
		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.FOUND);
	}

	private User mapToUser(UserRequest userRequest) {
		return User.builder().username(userRequest.getUsername()).email(userRequest.getEmail())
				.password(userRequest.getPassword()).build();

	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userid(user.getUserid()).username(user.getUsername()).email(user.getEmail())
				.build();

	}

}
