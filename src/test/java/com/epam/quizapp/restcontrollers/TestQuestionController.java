package com.epam.quizapp.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.exception.QuestionNotFoundException;
import com.epam.quizapp.service.QuestionServiceImpl;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
class TestQuestionController {
	
	@Autowired
	private MockMvc  mockMvc;
	
	@MockBean
    private QuestionServiceImpl service;
	
	@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
	@Test
	void getQuestionsTest() throws Exception {
		List<Question> questions=getQuestions();
		when(service.getAllQuestions()).thenReturn(questions);
		 mockMvc.perform(get("/questions"))
         .andExpect(status().isOk());
	}
	
	@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
	@Test
	void getQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		when(service.getQuestionById(1)).thenReturn(q1);
		mockMvc.perform(get("/questions/{1}",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void deleteQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		
		when(service.deleteQuestion(1)).thenReturn(q1);
		mockMvc.perform(delete("/questions/{1}",1))
		.andExpect(status().isOk());
	}
	
	@Test
	void testCreateQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		QuestionDTO q2=new QuestionDTO("what is Python","programming language","data type","both","none","a");
		q2.setId(1);
		Gson gson = new Gson();
		String quizJson = gson.toJson(q1);
		when(service.createQuestion(q2)).thenReturn(q1);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/questions").contentType(MediaType.APPLICATION_JSON).content(quizJson)).andExpect(status().isOk());
		
	}
	
	@Test
	void testUpdateQuestion() throws Exception {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		QuestionDTO q2=new QuestionDTO("what is Python","programming language","data type","both","none","a");
		q2.setId(1);
		Gson gson = new Gson();
		String quizJson = gson.toJson(q2);
		when(service.updateQuestion(q2, 1)).thenReturn(q1);
		
		mockMvc.perform(
				MockMvcRequestBuilders.put("/questions/{1}",1).contentType(MediaType.APPLICATION_JSON).content(quizJson)).andExpect(status().isOk());
	}
	
	
	private List<Question> getQuestions(){
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		Question q2=new Question("what is Java","programming language","data type","both","none","a");
		List<Question> questions=new ArrayList<>();
		q1.setId(1);
		q2.setId(2);
		questions.add(q1);
		questions.add(q2);
		return questions;
		
	}
	@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
	@Test
	void testGetQuestionByIdInvalid() throws Exception {
	when(service.getQuestionById(Mockito.anyInt())).thenThrow(QuestionNotFoundException.class);
	mockMvc.perform(get("/questions/0")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	

}
