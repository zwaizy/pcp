package com.vcread.pcp.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcread.pcp.dao.FrameDepartmentDao;
import com.vcread.pcp.entity.FrameDepartment;

@Service
public class FrameDepartmentService {

	@Autowired
	private FrameDepartmentDao frameDepartmentDao;
	
	public FrameDepartment getFrameDepartment(String deptCode, String framCode) {
		return frameDepartmentDao.selectByDepartmentName(deptCode,framCode);
	}

	public List<Map<String,Object>> getFramList(){
		return frameDepartmentDao.findAll();
	}


}
