package com.epam.quizapp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.entities.Quiz;
import com.epam.quizapp.exception.ExceptionResponse;
import com.epam.quizapp.exception.QuizNotFoundException;
import com.epam.quizapp.repository.QuizRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class QuizServiceTest {
  
	@InjectMocks
	private QuizServiceImpl service;
	@Mock
	private QuestionServiceImpl questionService;
	@Mock
	private QuizRepository repository;
	
	@Test
	void getAllQuizTest() {
		List<Quiz> quizs=getQuizzes();
		when(repository.findAll()).thenReturn(quizs);
		assertEquals(2, service.findAllQuiz().size());
	}
	
	@Test
	void findQuizByIdTest() {
		Quiz q1=getQuiz();
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		when(repository.findById(2)).thenThrow(QuizNotFoundException.class);
		assertEquals(q1, service.findQuizById(1));
	}
	
	@Test
	void testQuizNotFoundException() {
		Quiz q1=getQuiz();
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		assertThrows(QuizNotFoundException.class, ()->service.findQuizById(2));
	}
	
	@Test
	void createQuizTest() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		Quiz quiz=getQuiz();
		QuizDTO quizDTO=getQuizDTO();
		when(repository.save(Mockito.any())).thenReturn(quiz);
		when(questionService.getQuestionById(Mockito.anyInt())).thenReturn(q1);
		when(service.createQuiz(quizDTO)).thenReturn(quiz);
		assertEquals(quiz.toString(),service.createQuiz(quizDTO).toString());
	}
	
	@Test
	void updateQuizTest() {
		Quiz q1=getQuiz();
		when(repository.save(q1)).thenReturn(q1);
		assertEquals(q1, service.updateQuiz(q1));
		
	}
	
	
	@Test
	void deleteQuizTest() {
		Quiz q1=getQuiz();
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		when(repository.findById(2)).thenThrow(QuizNotFoundException.class);
		assertEquals(q1, service.deleteQuiz(1));
	}
	
	
	
	@Test
	void updateQuizHelperTest() {
		Quiz q1=getQuiz();
		QuizDTO q2=getQuizDTO();
		q2.setId(1);
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		when(service.updateQuizHelper(q2.getId(),q2)).thenReturn(q1);
		assertEquals(q1,service.updateQuizHelper(1, q2));
		
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
//		st.add(q2);
		st.add(q1);
		Quiz qu1=new Quiz("Java",st);
		qu1.setId(0);
		return qu1;
	}
	
	public QuizDTO getQuizDTO() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		Question q2=new Question("what is Java","programming language","data type","both","none","a");
		q1.setId(1);
		q2.setId(2);
		Set<Integer> st=new HashSet<>();
		st.add(1);
//		st.add(2);
		QuizDTO qu1=new QuizDTO();
		qu1.setId(0);
		qu1.setTitle("Java");
		qu1.setIds(st);
		return qu1;
	}
	
	@Test
	void deleteQuestionFromQuizByIdTest() {
		Quiz quiz=getQuiz();
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		when(repository.save(quiz)).thenReturn(quiz);
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(quiz));
		when(questionService.getQuestionById(Mockito.anyInt())).thenReturn(q1);
		service.deleteQuestionFromQuizById(0, 1);
		assertEquals(0,quiz.getQuestions().size());
		
	}
	
	@Test
	void deleteQuestionFromAllQuizByIdTest() {
		List<Quiz> quizs=getQuizzes();
		when(service.findAllQuiz()).thenReturn(quizs);
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(quizs.get(0)));
		service.deleteQuestionFromAllQuizById(1);
		assertEquals(1, quizs.get(0).getQuestions().size());
		
	}
	
//	@Test
//	void testQuestion(){
//		Quiz q2=getQuiz();
//		QuestionDTO q3=new QuestionDTO();
//		q3.setId(5);
//		
//		Question q1=new Question("what is Python","programming language","data type","both","none","a");
//		assertNotEquals(null, q1);
//		assertNotEquals(q1,q2);
//		assertNotEquals(q1.getId(),q3.getId());
//		assertEquals(q1, q1);
//		assertEquals(q1.getId(), q2.getId());
//	}
	
	@Test
	void testExceptionResponse() {
		ExceptionResponse exceptionResponse1=new ExceptionResponse(new Date().toString(),"Not_FOUND","question not found","/questions");
		ExceptionResponse exceptionResponse2=new ExceptionResponse();
		exceptionResponse2.setTimeStamp(exceptionResponse1.getTimeStamp());
		exceptionResponse2.setError(exceptionResponse1.getError());
		exceptionResponse2.setPath(exceptionResponse1.getPath());
		exceptionResponse2.setStatus(exceptionResponse1.getStatus());
		
		assertNotEquals(exceptionResponse1, exceptionResponse2);
		
	}
	
	
	
	
	
	
}
