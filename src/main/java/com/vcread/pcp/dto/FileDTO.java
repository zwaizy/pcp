package com.vcread.pcp.dto;

import java.util.List;

public class FileDTO {
	
	private String path;
	
	private boolean flag;
	
	private List<String> fileName;

	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	

	
	

}
