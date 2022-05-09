package com.epam.quizapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class QuestionDTO {
	
	    private int id;
	    @NotNull
		@Size(min = 5,message = "problem statement length should be greater than 5")
	    private String problemStatement;
	    @NotNull
		@Size(min = 1,message = "option length should be greater than 5")
	    private String op1;
	    @NotNull
		@Size(min = 1,message = "option length should be greater than 5")
	    private String op2;
	    @NotNull
		@Size(min = 1,message = "option length should be greater than 5")
	    private String op3;
	    @NotNull
		@Size(min = 1,message = "option length should be greater than 5")
	    private String op4;
	    @NotNull
	    @Pattern(regexp = "^[a-d]",message = "ans should be in between a-d")
	    private String ans;

	    public QuestionDTO(String problemStatement, String op1, String op2, String op3, String op4, String ans) {
	        this.problemStatement = problemStatement;
	        this.op1 = op1;
	        this.op2 = op2;
	        this.op3 = op3;
	        this.op4 = op4;
	        this.ans = ans;
	        
	    }

	    public QuestionDTO() {
	    }

	    public int getId() {
	        return id;
	    }
	    
	    public void setId(int id) {
	        this.id=id;
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

}
