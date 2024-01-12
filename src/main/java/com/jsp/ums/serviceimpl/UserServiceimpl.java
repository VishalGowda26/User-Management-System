package com.jsp.ums.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.repo.UserRepo;
import com.jsp.ums.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepo repo;

	public User saveUser(User user) {
		return repo.save(user);
	}

}
