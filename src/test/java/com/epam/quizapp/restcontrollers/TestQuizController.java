package com.epam.quizapp.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.entities.Quiz;
import com.epam.quizapp.exception.QuizNotFoundException;
import com.epam.quizapp.service.QuizServiceImpl;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
class TestQuizController {

	@Autowired
	private MockMvc  mockMvc;
	
	@MockBean
    private QuizServiceImpl service;
	
	@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
	@Test
    void getQuizzesTest() throws Exception {
    	List<Quiz> quizs=getQuizzes();
    	when(service.findAllQuiz()).thenReturn(quizs);
    	 mockMvc.perform(get("/quizzes"))
         .andExpect(status().isOk());
    }
	
	@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
	@Test
    void getQuizTest() throws Exception {
    	Quiz quiz=getQuiz();
    	when(service.findQuizById(1)).thenReturn(quiz);
    	 mockMvc.perform(get("/quizzes/{1}",1))
         .andExpect(status().isOk());
    }
	
	@Test
    void deleteQuizTest() throws Exception {
    	Quiz quiz=getQuiz();
    	when(service.findQuizById(1)).thenReturn(quiz);
    	 mockMvc.perform(delete("/quizzes/{1}",1))
         .andExpect(status().isOk());
    }
	
	
	@Test
	void createQuizTest() throws Exception {
		Quiz q1=getQuiz();
		QuizDTO q2=getQuizDTO();
		Gson gson = new Gson();
		String quizJson = gson.toJson(q2);
		when(service.createQuiz(q2)).thenReturn(q1);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/quizzes").contentType(MediaType.APPLICATION_JSON).content(quizJson)).andExpect(status().isOk());
		
	}
	
	@Test
	void updateQuizTest() throws Exception {
		Quiz q1=getQuiz();
		QuizDTO q2=getQuizDTO();
		Gson gson = new Gson();
		String quizJson = gson.toJson(q2);
		when(service.updateQuizHelper(1,q2)).thenReturn(q1);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/quizzes/{1}",1).contentType(MediaType.APPLICATION_JSON).content(quizJson)).andExpect(status().isOk());
	}
	
	
	
	
	
	private List<Quiz> getQuizzes(){
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		Question q2=new Question("what is Java","programming language","data type","both","none","a");
		Question q3=new Question("what is C++","programming language","data type","both","none","a");
		q1.setId(1);
		q2.setId(2);
		q3.setId(3);
		Set<Question> st=new HashSet<>();
		st.add(q2);
		st.add(q1);
		List<Quiz> quizs=new ArrayList<>();
		Quiz qu1=new Quiz("Java",st);
		qu1.setId(1);
		Set<Question> st1=new HashSet<>();
		st1.add(q3);
		Quiz qu2=new Quiz("Python",st1);
		qu2.setId(2);
		quizs.add(qu2);
		quizs.add(qu1);
		return quizs;
	}
	
	public Quiz getQuiz() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		Question q2=new Question("what is Java","programming language","data type","both","none","a");
		q1.setId(1);
		q2.setId(2);
		Set<Question> st=new HashSet<>();
		st.add(q2);
		st.add(q1);
		Quiz qu1=new Quiz("Java",st);
		qu1.setId(1);
		return qu1;
	}
	
	public QuizDTO getQuizDTO() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		Question q2=new Question("what is Java","programming language","data type","both","none","a");
		q1.setId(1);
		q2.setId(2);
		Set<Integer> st=new HashSet<>();
		st.add(1);
		st.add(2);
		QuizDTO qu1=new QuizDTO("Java",st);
		qu1.setId(1);
		return qu1;
	}
	
	@Test
	void testGetQuizByIdInvalid() throws Exception {
	when(service.findQuizById(Mockito.anyInt())).thenThrow(QuizNotFoundException.class);
	mockMvc.perform(get("/quizzes/0")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	

	
}
