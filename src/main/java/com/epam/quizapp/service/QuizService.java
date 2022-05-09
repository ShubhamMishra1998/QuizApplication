package com.epam.quizapp.service;

import java.util.List;

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Quiz;

public interface QuizService {

	public Quiz createQuiz(QuizDTO q);
	
	public List<Quiz> findAllQuiz();
	
	public Quiz deleteQuiz(int id);
	
	public Quiz updateQuiz(Quiz q);
	
	public Quiz findQuizById(int id);
}
