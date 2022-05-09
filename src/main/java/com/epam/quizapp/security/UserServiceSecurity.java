package com.epam.quizapp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.quizapp.entities.User;
import com.epam.quizapp.repository.UserRepository;

@Service
public class UserServiceSecurity implements UserDetailsService {

	private UserRepository userRepository;



	public UserServiceSecurity(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Cannot find username : " + username);
		}
		return new UserPrincipal(user);

	}

}
