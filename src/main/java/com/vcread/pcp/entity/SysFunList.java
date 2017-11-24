package com.vcread.pcp.entity;

import java.io.Serializable;

public class SysFunList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4475544766754995371L;
	
	private int funId;
	
	private String funName;
	
	private String url;

	public int getFunId() {
		return funId;
	}

	public void setFunId(int funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}