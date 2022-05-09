package com.epam.quizapp.restcontrollers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.entities.User;
import com.epam.quizapp.exception.UserNotFoundException;
import com.epam.quizapp.security.UserServiceSecurity;
import com.epam.quizapp.service.UserServiceImpl;
import com.google.gson.Gson;

@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class TestUserController {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserServiceImpl userService;
	@MockBean
	UserServiceSecurity userServiceSecurity;
	
	@Test
	void testUserSignUp() throws Exception {
		UserDTO userDTO=new UserDTO();
		userDTO.setName("Shubham");
		userDTO.setUserName("sm544735@gmail.com");
		userDTO.setPassword("123456");
		userDTO.setId(1);
		userDTO.setAdmin(true);
		Gson gson = new Gson();
		String json = gson.toJson(userDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());	
}
	@Test
	void testUserLogin() throws Exception {
		UserDTO userDTO=new UserDTO();
		userDTO.setName("Shubham");
		userDTO.setUserName("sm544735@gmail.com");
		userDTO.setPassword("123456");
		userDTO.setId(1);
		userDTO.setAdmin(true);
		Gson gson = new Gson();
		String json = gson.toJson(userDTO);
		mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());
	}
	
	@Test
	void getUserTest() throws Exception{
		User user1=new User("shubham","sm544@gmail.com","12345",true);
		user1.setId(1);
		when(userService.getUserById(1)).thenReturn(user1);
		mockMvc.perform(get("/users/{1}",1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name",is("shubham")))
		.andExpect(jsonPath("$.userName",is("sm544@gmail.com")))
		.andExpect(jsonPath("$.password",is("12345")));
		verify(userService,times(1)).getUserById(1);
		verifyNoMoreInteractions(userService);
		
	}
	    @Test
	    void getUsersTest() throws Exception {
		List<User> users=getUsers();
		when(userService.findAllUser()).thenReturn(users);
		 mockMvc.perform(get("/users"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].id", is(1)))
         .andExpect(jsonPath("$[0].name", is("shubham")))
         .andExpect(jsonPath("$[0].userName", is("sm544@gmail.com")))
         .andExpect(jsonPath("$[0].password", is("12345")))
         .andExpect(jsonPath("$[1].id", is(2)))
         .andExpect(jsonPath("$[1].name", is("shyam")))
         .andExpect(jsonPath("$[1].userName", is("sm@gmail.com")))
         .andExpect(jsonPath("$[1].password", is("12345")));
		
		 verify(userService,times(1)).findAllUser();
	        reset(userService);
	}
	
	
	private List<User> getUsers() {
		User user1=new User("shubham","sm544@gmail.com","12345",true);
		User user2=new User("shyam","sm@gmail.com","12345",true);
		List<User> users=new ArrayList<>();
		users.add(user1);
		users.add(user2);
		user1.setId(1);
		user2.setId(2);
		return users;
	}
	
	@Test
	void testGetUserByIdInvalid() throws Exception {
	when(userService.getUserById(Mockito.anyInt())).thenThrow(UserNotFoundException.class);
	mockMvc.perform(get("/users/0")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	
	@Test
	void testInvalidUserSignUp() throws Exception {
		UserDTO userDTO=new UserDTO();
		userDTO.setName("Shubham");
		userDTO.setUserName("sm54473");
		userDTO.setPassword("123456");
		userDTO.setId(1);
		userDTO.setAdmin(true);
		Gson gson = new Gson();
		String json = gson.toJson(userDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());	
}
	
	
	
	
}
