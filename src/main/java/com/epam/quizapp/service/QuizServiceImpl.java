package com.epam.quizapp.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.quizapp.dto.QuizDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.entities.Quiz;
import com.epam.quizapp.exception.QuizNotFoundException;
import com.epam.quizapp.repository.QuizRepository;

@Service
@Transactional
public class QuizServiceImpl implements QuizService{
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionServiceImpl questionService; 
	
	@Override
	public Quiz createQuiz(QuizDTO quizDTO) {
		Set<Question> st=new HashSet<>();
		for(int id:quizDTO.getIds()) {
			Question q1=questionService.getQuestionById(id);
			if(q1!=null) {
				st.add(q1);
			}
		}
		 Quiz quiz= new Quiz(quizDTO.getTitle(),st);
		 quizRepository.save(quiz);
		 return quiz;
	}
	
	@Override
	public List<Quiz> findAllQuiz(){
		return (List<Quiz>) quizRepository.findAll();
	}
	
	@Override
	public Quiz deleteQuiz(int id) {
		Quiz q=findQuizById(id);
			quizRepository.delete(q);
		return q;
	}
	
	@Override
	public Quiz updateQuiz(Quiz q) {
		quizRepository.save(q);
		return q;
	}
	
	
	public Quiz updateQuizHelper(int id,QuizDTO quizDTO) {
		Quiz quiz=findQuizById(id);
		quiz.setTitle(quizDTO.getTitle());
		Set<Question> st=new HashSet<>();
		for(int id1:quizDTO.getIds()) {
			Question q1=questionService.getQuestionById(id1);
				st.add(q1);
		}
		
		quiz.setQuestions(st);
		
		return updateQuiz(quiz);
		
		
		
	}
	
	@Override
	public Quiz findQuizById(int id) {
		return quizRepository.findById(id).orElseThrow(()->new QuizNotFoundException("quiz not found"));
	}
	

	
		
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void deleteQuestionFromAllQuizById(int id){
        List<Quiz> quizzes=findAllQuiz();
            for(Quiz q:quizzes){
                List<Question> list=q.getQuestions().stream().filter(e->e.getId()==id).toList();
                if(!list.isEmpty()){
                    deleteQuestionFromQuizById(q.getId(),id);
                }
            }
    }
	
	public void deleteQuestionFromQuizById(int quizId,int quesId){
       Quiz q=findQuizById(quizId);
       Question question=questionService.getQuestionById(quesId);
       q.getQuestions().remove(question);
       updateQuiz(q);       
    }
	
     
}
