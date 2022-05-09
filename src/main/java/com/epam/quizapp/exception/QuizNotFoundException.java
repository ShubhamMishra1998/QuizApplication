package com.epam.quizapp.exception;



public class QuizNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public QuizNotFoundException(String msg) {
		super(msg);
	}
	
 
}
