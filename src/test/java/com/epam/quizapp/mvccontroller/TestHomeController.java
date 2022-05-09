package com.epam.quizapp.mvccontroller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.quizapp.security.UserServiceSecurity;

@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeControllerMVC.class)
   class TestHomeController {
    
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserServiceSecurity userServiceSecurity;
	
	@Test
	void testShowHome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk());
		
	}
	
	@Test
	void testLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(status().isOk());
		
	}
	
	@Test
	void testRegister() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/register")).andExpect(status().isOk());
		
	}
}
