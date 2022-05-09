package com.epam.quizapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ques_id")
    private int id;
    @Column(name = "problem_statement")
    private String problemStatement;
    @Column(name = "option1")
    private String op1;
    @Column(name = "option2")
    private String op2;
    @Column(name = "option3")
    private String op3;
    @Column(name = "option4")
    private String op4;
    @Column(name = "ans")
    private String ans;

    public Question(String problemStatement, String op1, String op2, String op3, String op4, String ans) {
        this.problemStatement = problemStatement;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.ans = ans;
        
    }

    public Question() {
    }

   


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", problemStatement='" + problemStatement + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", op4='" + op4 + '\'' +
                ", ans='" + ans + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(ans, id, op1, op2, op3, op4, problemStatement);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(ans, other.ans) && id == other.id && Objects.equals(op1, other.op1)
				&& Objects.equals(op2, other.op2) && Objects.equals(op3, other.op3) && Objects.equals(op4, other.op4)
				&& Objects.equals(problemStatement, other.problemStatement);
	}
    
    

    
}
