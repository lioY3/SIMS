package com.model;

import java.io.Serializable;

import model.String;

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Dno;//Ïµ±àºÅ
	private String Dname;//ÏµÃû
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
