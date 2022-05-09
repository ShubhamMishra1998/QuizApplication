package com.epam.quizapp.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.quizapp.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	 User findUserByUserName(String userName);


	
}
