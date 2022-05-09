package com.epam.quizapp.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
	private int id;
	@NotNull
	@Size(min = 3,max = 20,message = "name length should be greater than 3 and less than 20")
	private String name;
	@NotNull
	@Email(message = "plese enter correct email")
	private String userName;
	@NotNull
	@Size(min=5,max = 20,message = "password length should be greater than 5 and less than 20")
	private String password;
	@NotNull
	private boolean isAdmin;
	public UserDTO(String name, String userName, String password, boolean isAdmin) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public UserDTO() {
		this.isAdmin=true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	

}
