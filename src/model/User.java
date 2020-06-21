package model;

import java.io.Serializable;


public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 管理员类型用户
	 */
	public static final int USER_ADMIN = 1;
	
	/**
	 * 学生类型用户
	 */
	public static final int USER_STUDENT = 2;
	
	/**
	 * 教师类型用户
	 */
	public static final int USER_TEACHER = 3;

	private String username;//用户名
	private String account; //账户
	private String password;//密码
	private int level;//级别
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
