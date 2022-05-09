package com.epam.quizapp.exception;

public class QuestionNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public QuestionNotFoundException(String msg) {
		super(msg);
	}

}
