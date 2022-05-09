package com.epam.quizapp.service;

import java.util.List;

import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;

public interface QuestionService {
	
	public Question createQuestion(QuestionDTO q);
	public Question deleteQuestion(int id);
	public Question updateQuestion(QuestionDTO q,int id);
	public List<Question> getAllQuestions();
	public Question getQuestionById(int id);

}
