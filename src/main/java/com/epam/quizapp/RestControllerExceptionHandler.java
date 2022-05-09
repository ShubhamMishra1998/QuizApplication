package com.epam.quizapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.quizapp.exception.ExceptionResponse;
import com.epam.quizapp.exception.QuestionNotFoundException;
import com.epam.quizapp.exception.QuizNotFoundException;
import com.epam.quizapp.exception.UserNotFoundException;


@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest req) {
        List<String> inputErrorStrings=new ArrayList<>();
        ex.getAllErrors().forEach(err->inputErrorStrings.add(err.getDefaultMessage()));

        return new ExceptionResponse(new Date().toString(),HttpStatus.BAD_REQUEST.name(),inputErrorStrings.toString(),req.getDescription(false));
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse userNotFoundException(UserNotFoundException exception,WebRequest req) {

        return new ExceptionResponse(new Date().toString(),HttpStatus.NOT_FOUND.name(),exception.getMessage(),req.getDescription(false));

    }


    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse questionNotFoundException(QuestionNotFoundException exception,WebRequest req) {

        return new ExceptionResponse(new Date().toString(),HttpStatus.NOT_FOUND.name(),exception.getMessage(),req.getDescription(false));

    }


    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse quizNotFoundException(QuizNotFoundException exception,WebRequest req) {

        return new ExceptionResponse(new Date().toString(),HttpStatus.NOT_FOUND.name(),exception.getMessage(),req.getDescription(false));

    }




}
