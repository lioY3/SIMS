package com.model;

import java.io.Serializable;

import model.String;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;//�û���
	private String password;//����
	private String level;//Ȩ�޼���
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	

}
