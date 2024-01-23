package com.jsp.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.ums.repo.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	/*----------------------------------> Using Map Method <-------------------------------------*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username).map(user -> new CustomUserDetails(user))
				.orElseThrow(() -> new UsernameNotFoundException("Failed to authenticate the user"));

	}
	
	/*----------------------------------> Another Way <-------------------------------------*/
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepo.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("UserName Not Found"));
//		return new CustomUserDetails(user);
//	}

}
