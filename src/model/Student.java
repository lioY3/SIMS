package model;

public class Student {

	private String Sno;// 学号
	private String Sname;// 姓名
	private String Ssex;// 性别
	private String Sbirthday;// 生日
	private String Sid;// 身份证
	private String Snation;// 民族
	private String clno;// 班级号

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

	public String getSnation() {
		return Snation;
	}

	public void setSnation(String snation) {
		Snation = snation;
	}

}
