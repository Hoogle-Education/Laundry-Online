
  package com.laundry.myApp.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto  {
      
		
	  private Long id;
      @NotNull
      private String name;
      
      @NotNull
      private String username;
      
      @NotEmpty(message = "Email should not be empty")
      @Email
      private String email;
      
      @NotEmpty(message = "Password should not be empty")
      private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
      
      
      
  }