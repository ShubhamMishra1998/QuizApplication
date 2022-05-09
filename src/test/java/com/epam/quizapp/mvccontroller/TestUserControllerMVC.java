package com.epam.quizapp.mvccontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.entities.User;
import com.epam.quizapp.security.UserServiceSecurity;
import com.epam.quizapp.service.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControllerMVC.class)
@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
 class TestUserControllerMVC {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserServiceImpl service;
	@MockBean
	private UserServiceSecurity userServiceSecurity;
	
	@Test
	void testAdminHome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin")).andExpect(status().isOk());
	}
	
	@Test
	void testCreateUser() throws Exception {
		UserDTO user=new UserDTO("shubham","sm544735@gmail.com","1234",true);
		User user1=new User("shubham","sm544735@gmail.com","1234",true);
		when(service.createUser(user)).thenReturn(user1);
		mockMvc.perform(MockMvcRequestBuilders.post("/register")).andExpect(status().isOk());
	}
	
	@Test
	void testIsValidUser() throws Exception {
		UserDTO user=new UserDTO("shubham","sm544735@gmail.com","1234",true);
		List<User> users=new ArrayList<>();
		User user1=new User("shubham","sm544735@gmail.com","1234",true);
		users.add(user1);
		when(service.isValidUser(user.getUserName(),user.getPassword())).thenReturn(user1);
		when(service.findAllUser()).thenReturn(users);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login"))
		        .andExpect(status().isFound());
		
	}
		

}
