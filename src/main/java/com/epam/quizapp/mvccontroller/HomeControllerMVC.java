package com.epam.quizapp.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeControllerMVC {
	
	@GetMapping("/")
	public ModelAndView getHome() {
		 ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("index.html");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("login.html");
		return modelAndView;
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
	      modelAndView.setViewName("signup.html");
		return modelAndView;
	}


}
