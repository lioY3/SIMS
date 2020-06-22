package model;

public class User {

	public static final int ADMIN = 1;// 管理员用户
	public static final int TEACHER = 2;// 教师用户

	private String account; // 账户
	private String password;// 密码
	private int type;// 级别
	private String username;//用户名

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
