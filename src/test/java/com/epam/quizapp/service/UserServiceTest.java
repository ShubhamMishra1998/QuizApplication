package com.epam.quizapp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.entities.User;
import com.epam.quizapp.exception.UserNotFoundException;
import com.epam.quizapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository repository;
	
	@Test
	void findAllUserTest() {
		List<User> users=giveUsesrsList();
		
		when(repository.findAll()).thenReturn(users);
		assertEquals(2,userService.findAllUser().size());
		
	}
	
	@Test
	void createUserTest() {
		UserDTO userdto=new UserDTO("shubham","sm544@gmail.com","1234",true);
		User user=new User(userdto.getName(),userdto.getUserName(),userdto.getPassword(),userdto.isAdmin());
		user.setId(userdto.getId());
		when(repository.save(user)).thenReturn(user);
		User user2=userService.createUser(userdto);
		assertEquals(user.getUserName(),user2.getUserName());
		assertEquals(user.getName(),user2.getName());
	}
	
	@Test
	void isValidUserTest(){
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		User user=new User("shubham","sm544@gmail.com","1234",true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		when(repository.findUserByUserName(user.getUserName())).thenReturn(user);
		assertEquals(user.getUserName(),userService.isValidUser(user.getUserName(),"1234").getUserName());
		assertThrows(UserNotFoundException.class, ()->userService.isValidUser(user.getUserName(),"12"));

	}
	
	@Test
	void getUserByIdTest() throws Exception {
		User user1=new User();
		user1.setId(1);
		user1.setAdmin(true);
		user1.setName("shubham");
		user1.setUserName("sm544@gmail.com");
		user1.setPassword("1234");
		when(repository.findById(1)).thenReturn(Optional.of(user1));
		assertEquals(user1.toString(), userService.getUserById(1).toString());
		
	}
	
	@Test
	void testUserNotFoundException() {
		User user1=new User("shubham","sm544@gmail.com","1234",true);
		user1.setId(1);
		when(repository.findById(1)).thenReturn(Optional.of(user1));
		assertThrows(UserNotFoundException.class, ()->userService.getUserById(2));
	}
	
	private List<User> giveUsesrsList(){
		User user1=new User("shubham","sm544@gmail.com","1234",true);
		User user2=new User("shubham1","sm5644@gmail.com","12345",false);
		user1.setId(1);
		user2.setId(2);
		List<User> users=new ArrayList<>();
		users.add(user1);
		users.add(user2);
		return users;
		
	}
	
	
	

}
