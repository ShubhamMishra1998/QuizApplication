package com.epam.quizapp.mvccontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Quiz;
import com.epam.quizapp.service.QuizServiceImpl;

@Controller
@RequestMapping("/quizzes")
public class QuizControllerMVC {
	
	private static final String ALLQUIZ="/quizapp/quizzes/all";
	
	@Autowired
	QuizServiceImpl quizService;

	@GetMapping("/all")
	public ModelAndView getQuizzes(Model model) {
		List<Quiz> quizzes=quizService.findAllQuiz();
		ModelAndView modelAndView=new ModelAndView();
		model.addAttribute("quizzes",quizzes);
		modelAndView.setViewName("quizzes");
		return modelAndView;
	}
	
	@GetMapping("/add")
	public ModelAndView getQuiz() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("quiz");
		return modelAndView;
	}

	
	@PostMapping("/add")
	public RedirectView createQuiz(QuizDTO quizDTO) {
		quizService.createQuiz(quizDTO);
		return new RedirectView(ALLQUIZ);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView deleteQuiz(@PathVariable("id") int id) {
		quizService.deleteQuiz(id);
		return new RedirectView(ALLQUIZ);
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateQuiz(@PathVariable("id") int id,Model model) {
		Quiz q=quizService.findQuizById(id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("updatequiz");
		model.addAttribute("quiz",q);
		return modelAndView;
	}
	
	
	@PutMapping("/update/{id}")
	public RedirectView  updateQuestionUi(@PathVariable("id") int id,QuizDTO quizDTO) {
		
		quizService.updateQuizHelper(id,quizDTO);
		return new RedirectView(ALLQUIZ);
		
	}
	
	@GetMapping("/view/{id}")
	public ModelAndView viewQuiz(@PathVariable("id") int id,Model model) {
		Quiz q=quizService.findQuizById(id);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("viewquiz");
		model.addAttribute("quiz",q);
		return modelAndView;
	}

	





}
