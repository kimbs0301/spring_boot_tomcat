package com.example.boot.account.model;

/**
 * @author gimbyeongsu
 * 
 */
public class Account {

	private int id;

	private String name;

	private String ssn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", ssn=" + ssn + "]";
	}
}
