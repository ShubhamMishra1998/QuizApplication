package com.epam.quizapp.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.service.QuestionServiceImpl;
import com.epam.quizapp.service.QuizServiceImpl;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
	
	@Autowired
	QuestionServiceImpl questionService;
	@Autowired
	QuizServiceImpl quizService;
	
	
	@GetMapping
	public ResponseEntity<List<Question>> getAllQuestions(){
		
		return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Question> getAllQuestionById(@PathVariable int id){
		
		return new ResponseEntity<>(questionService.getQuestionById(id),HttpStatus.OK);
		
	}
	
	
	@PostMapping
	public ResponseEntity<Question> createQuestion(@RequestBody @Valid QuestionDTO q) {
		return new ResponseEntity<>(questionService.createQuestion(q),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Question> deleteQuestion(@PathVariable("id") int id) {
		quizService.deleteQuestionFromAllQuizById(id);
		return new ResponseEntity<>(questionService.deleteQuestion(id),HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Question> updateQuestionUi(@RequestBody @Valid QuestionDTO q,@PathVariable("id") int id) {
    	return  new ResponseEntity<>(questionService.updateQuestion(q,id),HttpStatus.OK);
		
	}
	
	

	
}
