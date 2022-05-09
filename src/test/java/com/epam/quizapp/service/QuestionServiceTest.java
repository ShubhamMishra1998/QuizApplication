package com.epam.quizapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.exception.QuestionNotFoundException;
import com.epam.quizapp.repository.QuestionRepository;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class QuestionServiceTest {
	@InjectMocks
    private QuestionServiceImpl service;
	@Mock
    private QuestionRepository repository;
	
	@Test
	void getAllQuestionsTest() {
		List<Question> questions=getQuestions();
		when(repository.findAll()).thenReturn(questions);
		assertEquals(2,service.getAllQuestions().size());
		
	}
	
	@Test
	void getQuestionByIdTest() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		when(repository.findById(2)).thenThrow(QuestionNotFoundException.class);
		assertEquals(q1.toString(),service.getQuestionById(1).toString());
	}
	
	@Test
	void testQuestionNotFound() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		assertThrows(QuestionNotFoundException.class, ()->service.getQuestionById(5));
	}
	
	@Test
	void createQuestionTest() {
		QuestionDTO q1=new QuestionDTO("what is Python","programming language","data type","both","none","a");
		Question q=new Question(q1.getProblemStatement(),q1.getOp1(),q1.getOp2(),q1.getOp3(),q1.getOp4(),q1.getAns());
		
		when(repository.save(q)).thenReturn(q);
	    assertEquals(q.toString(), service.createQuestion(q1).toString());
	}
	
	@Test
	void deleteQuestionTest(){
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		assertEquals(q1.toString(), service.deleteQuestion(1).toString());
		
	}
	
	
	@Test
	void updateQuestionTest() {
		Question q1=new Question("what is Python","programming language","data type","both","none","a");
		q1.setId(1);
		QuestionDTO q2=new QuestionDTO("what is Python","programming language","data type","both","none","a");
		q2.setId(1);
		when(repository.findById(1)).thenReturn(Optional.of(q1));
		assertEquals(q1.toString(), service.updateQuestion(q2,1).toString());
		
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
}
