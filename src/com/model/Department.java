package com.model;

import java.io.Serializable;

import model.String;

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
