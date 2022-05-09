package com.epam.quizapp.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuizDTO {
	
	private int id;
	@NotNull
	@Size(min = 2,max = 20,message = "title length should be greater than 2 and less than 20")
	private String title;
	@NotEmpty(message = "please enter atleast one id")
	private Set<Integer> ids;
	public QuizDTO(String title, Set<Integer> ids) {
		this.title = title;
		this.ids = ids;
	}
	
	
	public QuizDTO() {
		super();
		ids=new HashSet<>();
		
	}


	


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Set<Integer> getIds() {
		return ids;
	}


	public void setIds(Set<Integer> ids) {
		this.ids = ids;
	}
	
	public int getId() {
		return id;
	}
	

}
