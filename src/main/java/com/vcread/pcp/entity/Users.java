package com.vcread.pcp.entity;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642045378054115702L;
	
	private String u_name;
	
	private String u_password;

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	
	
	
}