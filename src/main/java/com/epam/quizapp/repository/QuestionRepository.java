package com.epam.quizapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.quizapp.entities.Question;

@Repository
public interface QuestionRepository extends  CrudRepository<Question,Integer>{
	
}
