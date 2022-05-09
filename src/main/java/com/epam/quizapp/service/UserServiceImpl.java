package com.epam.quizapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.entities.User;
import com.epam.quizapp.exception.UserNotFoundException;
import com.epam.quizapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;
  
  @Override
  public User createUser(UserDTO userDto) {
	  User user=new User(userDto.getName(),userDto.getUserName(),userDto.getPassword(),userDto.isAdmin());
      PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
      user.setPassword(passwordEncoder.encode(user.getPassword()));
	  userRepository.save(user);
	  return user;
  }
  
  @Override
  public List<User> findAllUser(){
      return (List<User>)userRepository.findAll();
  }
  @Override
  public User getUserById(int id) throws UserNotFoundException {
	  return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found !"));
  }
  
  @Override
  public User isValidUser(String userName,String password) {
      PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
      User user=userRepository.findUserByUserName(userName);
  	if(user!=null){
          if(passwordEncoder.matches(password,user.getPassword())){
              return user;
          }else{
              throw new UserNotFoundException("User Not Found Please enter correct credentials");
          }
    }
      throw new UserNotFoundException("User Not Found Please enter correct credentials");
  }


//    @Override
//    public User isValidUser(String userName,String password) {
//        List<User> users=findAllUser();
//        for(User user:users) {
//            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
//                return user;
//            }
//        }
//        throw new UserNotFoundException("user not found");
//    }
  

  
	
}
