package model;

import java.io.Serializable;

<<<<<<< HEAD
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Dno;//ϵ���
	private String Dname;//ϵ��
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	

}
=======

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Dno;//院系编号
	private String Dname;//院系名称
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	

}
>>>>>>> branch 'master' of https://gitee.com/chen295/student_information_management.git
