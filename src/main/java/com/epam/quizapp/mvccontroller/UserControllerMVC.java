package com.epam.quizapp.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epam.quizapp.dto.UserDTO;
import com.epam.quizapp.exception.UserNotFoundException;
import com.epam.quizapp.service.UserServiceImpl;

@Controller
public class UserControllerMVC {
	
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/register")
     public ModelAndView  createUser(UserDTO user){
		 userService.createUser(user);
         ModelAndView modelAndView=new ModelAndView();
         modelAndView.setViewName("admin");
         return modelAndView;
     }
	
	@GetMapping("/admin")
	public ModelAndView getAdminPage() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin");
		return modelAndView;
		
	}
	
	@PostMapping("/login")
	public RedirectView login(UserDTO user) throws UserNotFoundException{
		userService.isValidUser(user.getUserName(), user.getPassword());
			  return new RedirectView("/quizapp/admin");
			 
	}


}
