package com.epam.quizapp.entities;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quiz_id")
    private  int id;
    @Column(name = "quiz_title")
    private String title;
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL})
    @JoinTable(name = "quiz_question",joinColumns = {@JoinColumn(name = "quiz_id")},inverseJoinColumns = {@JoinColumn(name = "ques_id")})
    private Set<Question> questions;

    public Quiz() {
    }

    public Quiz(String title, Set<Question> questions) {
    	
        this.title = title;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    
    
    

    public void setId(int id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}
