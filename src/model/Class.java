package model;

import java.io.Serializable;

public class Class implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Clno;//班级号
	private String Clname;//班级名
	private String Dno;//学院编号
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
