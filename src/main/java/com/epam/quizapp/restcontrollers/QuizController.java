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

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Quiz;
import com.epam.quizapp.service.QuizServiceImpl;

@RestController
@RequestMapping("/quizzes")
@Validated
public class QuizController {
	@Autowired
	QuizServiceImpl quizService;
	
	
	@GetMapping
	public ResponseEntity<List<Quiz>> getQuizzes() {
		return new ResponseEntity<>(quizService.findAllQuiz(),HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Quiz> viewQuiz(@PathVariable("id") int id) {
		return new ResponseEntity<>(quizService.findQuizById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Quiz> createQuiz(@RequestBody @Valid QuizDTO quizDTO) {
		return new ResponseEntity<>(quizService.createQuiz(quizDTO),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Quiz> deleteQuiz(@PathVariable("id") int id) {
		return new ResponseEntity<>(quizService.deleteQuiz(id),HttpStatus.OK);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Quiz> updateQuestionUi(@PathVariable("id") int id, @RequestBody @Valid QuizDTO quizDTO) {
		
		return new ResponseEntity<>(quizService.updateQuizHelper(id,quizDTO),HttpStatus.OK);
		
	}
	
	
	
}
