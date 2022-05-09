package com.epam.quizapp.exception;


public class ExceptionResponse {
	
	private String timeStamp;
	private String status;
	private String error;
	private String path;
	public ExceptionResponse(String timeStamp, String badRequest, String error, String path) {
		super();
		this.timeStamp = timeStamp;
		this.status = badRequest;
		this.error = error;
		this.path = path;
	}
	public ExceptionResponse() {
		super();
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	

}
