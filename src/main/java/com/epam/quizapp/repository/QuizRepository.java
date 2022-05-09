package com.epam.quizapp.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.quizapp.entities.Quiz;

@Repository
public interface QuizRepository extends CrudRepository<Quiz,Integer>{
      
	
}
