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

import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.service.QuestionServiceImpl;
import com.epam.quizapp.service.QuizServiceImpl;

@Controller
@RequestMapping("/questions")
public class QuestionControllerMVC {
	private static final String ALLQUESTIONS="/quizapp/questions/all";
	@Autowired
	QuestionServiceImpl questionService;
	@Autowired
	QuizServiceImpl quizService;
	
	
	@GetMapping("/all")
	public ModelAndView getAllQuestions(Model model){
		List<Question> questionList=questionService.getAllQuestions();
		model.addAttribute("questions",questionList);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("printallquestions");
		return modelAndView;
		
	}
	
	@GetMapping("/create")
	public ModelAndView question() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("question");
		return modelAndView;
	}
	
	@PostMapping("/create")
	public RedirectView createQuestion(QuestionDTO q) {
		questionService.createQuestion(q);
		return new RedirectView(ALLQUESTIONS);
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView deleteQuestion(@PathVariable("id") int id) {
		quizService.deleteQuestionFromAllQuizById(id);
		questionService.deleteQuestion(id);
		return new RedirectView(ALLQUESTIONS);
		
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView updateQuestion(@PathVariable("id") int id,Model model) {
		Question q=questionService.getQuestionById(id);
		ModelAndView modelAndView=new ModelAndView();
		model.addAttribute("question",q);
		modelAndView.setViewName("updatequestion");
		return modelAndView;
		
	}
	
	@PutMapping("/update/{id}")
	public RedirectView  updateQuestionUi(QuestionDTO q,@PathVariable("id") int id) {
    	questionService.updateQuestion(q,id);
		return new RedirectView(ALLQUESTIONS);
		
		
	}


	

}
