package com.epam.quizapp.mvccontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.security.UserServiceSecurity;
import com.epam.quizapp.service.QuestionServiceImpl;
import com.epam.quizapp.service.QuizServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuestionControllerMVC.class)
@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
class TestQuestionController {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	QuestionServiceImpl service;
	
	@MockBean
	QuizServiceImpl quizService;
	
	@MockBean
	UserServiceSecurity userServiceSecurity;
	
	@Test
	void testGetAllQuestion() throws Exception {
		List<Question> questions=getAllQuestions();
		when(service.getAllQuestions()).thenReturn(questions);
		mockMvc.perform(MockMvcRequestBuilders.get("/questions/all")).andExpect(status().isOk());
		
	}
	
	@Test
	void testCreateQuestionUI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/questions/create")).andExpect(status().isOk());
	}
	
	
	@Test
	void testDeleteQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		when(service.deleteQuestion(1)).thenReturn(q1);
		mockMvc.perform(get("/questions/delete/{1}",1))
		.andExpect(status().isFound());
		
	}
	
	@Test
	void testUpDateQuestionUI() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		when(service.getQuestionById(Mockito.anyInt())).thenReturn(q1);
		mockMvc.perform(MockMvcRequestBuilders.get("/questions/update/{1}",1)).andExpect(status().isOk());
	}
	
	
	@Test
	void testUpDateQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		QuestionDTO q2=new QuestionDTO("what is Python","programming language","data type","both","none","a");
		when(service.updateQuestion(q2,1)).thenReturn(q1);
		mockMvc.perform(MockMvcRequestBuilders.put("/questions/update/{12}",12)).andExpect(status().isFound());
	}
	
	@Test
	void testCreateQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		QuestionDTO q2=new QuestionDTO("what is Python","programming language","data type","both","none","a");
		when(service.createQuestion(q2)).thenReturn(q1);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/questions/create"))
		        .andExpect(status().isFound());
	}
	
	
	private List<Question> getAllQuestions(){
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		Question q2=new Question("what is Java","programming language","data type","both","none","a");
		List<Question> questions=new ArrayList<>();
		q1.setId(1);
		q2.setId(2);
		questions.add(q1);
		questions.add(q2);
		return questions;
		
	}
	

}
