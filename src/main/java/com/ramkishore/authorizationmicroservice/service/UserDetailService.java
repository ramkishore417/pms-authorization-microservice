package com.ramkishore.authorizationmicroservice.service;


import com.ramkishore.authorizationmicroservice.exception.ResourceNotFound;
import com.ramkishore.authorizationmicroservice.model.User;
import com.ramkishore.authorizationmicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailService implements UserService,UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public User saveUser(User user) {
		//Using CommandLineRunner, User details are getting saved.
		log.info("User saved into Database: {}",user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			log.info("Fetching username from User: {}",username);
			User user = userRepository.findByUsername(username);

			log.info("Loading entered User details to Spring User");
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());

		}catch(Exception e)
		{
			throw new ResourceNotFound("Given Username is not found or incorrect password");
		}
	}

}
