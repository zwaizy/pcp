package com.vcread.pcp.entity;
import java.io.Serializable;

public class UserDept implements Serializable {

	/**
	 * 用户名
	 */
	private String user_code;

	/**
	 *商户ID
	 */
	private String fram_seq;

	/**
	 * 商户标识
	 */
	private  String fram_code;

	private  String ordr_no;

	public String getUser_code() {
		return user_code;
	}

	public String getFram_seq() {
		return fram_seq;
	}

	public String getFram_code() {
		return fram_code;
	}

	public String getOrdr_no() {
		return ordr_no;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public void setFram_seq(String fram_seq) {
		this.fram_seq = fram_seq;
	}

	public void setFram_code(String fram_code) {
		this.fram_code = fram_code;
	}

	public void setOrdr_no(String ordr_no) {
		this.ordr_no = ordr_no;
	}
}