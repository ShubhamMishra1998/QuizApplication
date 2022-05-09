package com.epam.quizapp.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.entities.User;
import com.epam.quizapp.exception.UserNotFoundException;
import com.epam.quizapp.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException{
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
		
	}
	
	
	@PostMapping("/register")
     public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDto){
		User user=userService.createUser(userDto);
         return new ResponseEntity<>(user,HttpStatus.OK);
     }
	
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserDTO user) throws UserNotFoundException {
	 
		return new ResponseEntity<>(userService.isValidUser(user.getUserName(),user.getPassword()),HttpStatus.OK);	    
		  
	}
	
	
}
