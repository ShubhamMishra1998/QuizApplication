package com.epam.quizapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.quizapp.dto.QuestionDTO;
import com.epam.quizapp.entities.Question;
import com.epam.quizapp.exception.QuestionNotFoundException;
import com.epam.quizapp.repository.QuestionRepository;

@Transactional
@Service
public class QuestionServiceImpl implements QuestionService{

	   @Autowired
	   QuestionRepository questionRepository;
		
	   @Override
		public Question createQuestion(QuestionDTO q) {
		    Question question=new Question(q.getProblemStatement(),q.getOp1(),q.getOp2(),q.getOp3(),q.getOp4(),q.getAns());
			questionRepository.save(question);
			return question;
		}
		
	   @Override
		public Question deleteQuestion(int id) {
			Question q=getQuestionById(id);
			questionRepository.delete(q);
			return q;
		}
		
		
		
		@Override
		public Question updateQuestion(QuestionDTO q,int id) {
			Question question=getQuestionById(id);
				question.setProblemStatement(q.getProblemStatement());
				question.setOp1(q.getOp1());
				question.setOp2(q.getOp2());
				question.setOp3(q.getOp3());
				question.setOp4(q.getOp4());
				question.setAns(q.getAns());
				questionRepository.save(question);
			return question;
		}
		
		@Override
		public List<Question> getAllQuestions(){
			return (List<Question>)questionRepository.findAll();
		}
		
		
		@Override
		public Question getQuestionById(int id) {
			return questionRepository.findById(id).orElseThrow(()->new QuestionNotFoundException("question not found"));
			
		}
		
		
}
