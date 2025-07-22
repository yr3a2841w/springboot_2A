package com.college.yi.bookmanager.entity;

import lombok.Data;

@Data
public class UserEntity {

	private int id;
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	
}
