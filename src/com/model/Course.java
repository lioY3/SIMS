package com.model;

import java.io.Serializable;

import model.String;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Cno;//�γ̺�
	private String Cname;//�γ�����
	private int Credit;//ѧ��
	private String Term;//
	private String Hours;//ѧʱ
	private String Tno;//ִ����ʦ���
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
