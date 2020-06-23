package model;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

	private String Tno; // 编号
	private String Tname; // 姓名
	private String Tsex; // 性别
	private List<Course> courseList = new ArrayList<>();
	
	private String[] course = new String[]{}; //课程集合

	public String getTno() {
		return Tno;
	}

	public void setTno(String tno) {
		Tno = tno;
	}

	public String getTname() {
		return Tname;
	}

	public void setTname(String tname) {
		Tname = tname;
	}

	public String getTsex() {
		return Tsex;
	}

	public void setTsex(String tsex) {
		Tsex = tsex;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
		
	}
	
	public String[] getCourse() {
		return course;
	}

	public void setCourse(String[] course) {
		this.course = course;
	}



}
