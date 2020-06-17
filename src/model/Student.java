package model;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Sno;//学号
	private String Sname;//姓名
	private String Ssex;//性别
	private String Sbirthday;//生日
	private int Sage;//年龄
	private String Sid;//身份证号
	private String clno;//所在班级
	
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public String getSbirthday() {
		return Sbirthday;
	}
	public void setSbirthday(String sbirthday) {
		Sbirthday = sbirthday;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int sage) {
		Sage = sage;
	}
	public String getSid() {
		return Sid;
	}
	public void setSid(String sid) {
		Sid = sid;
	}
	public String getClno() {
		return clno;
	}
	public void setClno(String clno) {
		this.clno = clno;
	}
	
	
}
