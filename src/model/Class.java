package model;

import java.util.LinkedList;
import java.util.List;

public class Class {

	private String Clno;// 班级编号
	private String Clname;// 班级名称
	private String Dno;// 院系编号
	private List<Student> studentList = new LinkedList<>();

	public String getClno() {
		return Clno;
	}

	public void setClno(String clno) {
		Clno = clno;
	}

	public String getClname() {
		return Clname;
	}

	public void setClname(String clname) {
		Clname = clname;
	}

	public String getDno() {
		return Dno;
	}

	public void setDno(String dno) {
		Dno = dno;
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
