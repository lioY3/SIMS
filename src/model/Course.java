package model;

import com.lizhou.bean.Clazz;

public class Course {
	
	private String Cno;// 课程号
	private String Cname;// 课程名称
	private Class cls; //班级
	private int Credit;// 学分
	private String Term;//
	private String Hours;// 学时
	private String Tno;// 教师编号

	public String getCno() {
		return Cno;
	}

	public void setCno(String cno) {
		Cno = cno;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}
	

	public Class getCls() {
		return cls;
	}

	public void setCls(Class cls) {
		this.cls = cls;
	}

	public int getCredit() {
		return Credit;
	}

	public void setCredit(int credit) {
		Credit = credit;
	}

	public String getTerm() {
		return Term;
	}

	public void setTerm(String term) {
		Term = term;
	}

	public String getHours() {
		return Hours;
	}

	public void setHours(String hours) {
		Hours = hours;
	}

	public String getTno() {
		return Tno;
	}

	public void setTno(String tno) {
		Tno = tno;
	}

}