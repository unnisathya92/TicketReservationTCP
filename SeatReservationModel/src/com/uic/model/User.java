package com.uic.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 787260218809795130L;
	private String fName;
	private String lName;
	private String eMailID;
	private String password;

	public User(String fName, String lName, String eMail, String password) {
		this.fName = fName;
		this.lName = lName;
		this.eMailID = eMail;
		this.password = password;
	}
	
	public User(String eMail, String password) {
		
		this.eMailID = eMail;
		this.password = password;
	}

	public User() {
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String geteMailID() {
		return eMailID;
	}

	public void seteMailID(String eMailID) {
		this.eMailID = eMailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
