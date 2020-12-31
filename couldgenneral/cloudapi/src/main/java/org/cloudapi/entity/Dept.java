package org.cloudapi.entity;

import java.io.Serializable;

public class Dept implements Serializable{
	
	private static final long serialVersionUID = 5975243864699322743L;

	private long dno;
	private String dname;
	private String dsource;
	
	
	public Dept() {
		super();
	}
	public Dept(long dno, String dname, String dsource) {
		super();
		this.dno = dno;
		this.dname = dname;
		this.dsource = dsource;
	}
	public long getDno() {
		return dno;
	}
	public void setDno(long dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDsource() {
		return dsource;
	}
	public void setDsource(String dsource) {
		this.dsource = dsource;
	}
	@Override
	public String toString() {
		return "Dept [dno=" + dno + ", dname=" + dname + ", dsource=" + dsource + "]";
	}
	
}
