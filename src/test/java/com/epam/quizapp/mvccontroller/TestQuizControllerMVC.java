package com.epam.quizapp.mvccontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.entities.Quiz;
import com.epam.quizapp.security.UserServiceSecurity;
import com.epam.quizapp.service.QuizServiceImpl;
@WithMockUser(username = "sm544735@gmail.com",roles={"USER","ADMIN"})
@ExtendWith(SpringExtension.class)
@WebMvcTest(QuizControllerMVC.class)
class TestQuizControllerMVC {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	QuizServiceImpl quizService;
	
	@MockBean
	private UserServiceSecurity userServiceSecurity;
	
	@Test
	void testGetAllQuiz() throws Exception {
		List<Quiz> quizzs = getQuizzes();
		when(quizService.findAllQuiz()).thenReturn(quizzs);
		mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/all")).andExpect(status().isOk());
	}
	
	@Test
	void testQuizAdd() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/add")).andExpect(status().isOk());
	}
	
	@Test
	void testAddQuiz() throws Exception {
		QuizDTO q1=getQuizDTO();
		Quiz q2=getQuiz();
		when(quizService.createQuiz(q1)).thenReturn(q2);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/quizzes/add"))
		        .andExpect(status().isFound());
	}
	
	@Test
	void testDeleteQuestion() throws Exception {
		Quiz q1=getQuiz();
		when(quizService.deleteQuiz(Mockito.anyInt())).thenReturn(q1);
		mockMvc.perform(get("/quizzes/delete/{1}",1))
		.andExpect(status().isFound());
	}
	
	@Test
	void testUpdateQuizUI() throws Exception {
		Quiz q1=getQuiz();
		when(quizService.findQuizById(Mockito.anyInt())).thenReturn(q1);
		mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/update/{1}",1)).andExpect(status().isOk());
	}
	
	@Test
	void testUpdateQuiz() throws Exception {
		QuizDTO q1=getQuizDTO();
		Quiz q2=getQuiz();
		when(quizService.updateQuizHelper(1, q1)).thenReturn(q2);
		mockMvc.perform(MockMvcRequestBuilders.put("/quizzes/update/{1}",1)).andExpect(status().isFound());
	}
	
	@Test
	void testViewQuiz() throws Exception {
		Quiz q1=getQuiz();
		when(quizService.findQuizById(Mockito.anyInt())).thenReturn(q1);
		mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/view/{1}",1)).andExpect(status().isOk());
		
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
}
