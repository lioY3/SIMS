package model;

import java.io.Serializable;

<<<<<<< HEAD
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

=======

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;//用户名
	private String password;//密码
	private String level;//级别
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
>>>>>>> branch 'master' of https://gitee.com/chen295/student_information_management.git
