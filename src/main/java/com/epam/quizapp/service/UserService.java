package com.epam.quizapp.service;

import java.util.List;


import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.entities.User;
import com.epam.quizapp.exception.UserNotFoundException;


public interface UserService {

	 public User createUser(UserDTO user);
	 public User isValidUser(String userName,String password);
	 public List<User> findAllUser();
	 public User getUserById(int id) throws UserNotFoundException;
	  
}
