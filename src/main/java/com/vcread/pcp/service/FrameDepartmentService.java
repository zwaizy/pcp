package com.vcread.pcp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcread.pcp.dao.FrameDepartmentDao;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.util.base.Page;

@Service
public class FrameDepartmentService {

	@Autowired
	private FrameDepartmentDao frameDepartmentDao;
	
	public FrameDepartment getFrameDepartment(String deptCode, String framCode) {
		return frameDepartmentDao.selectByDepartmentName(deptCode,framCode);
	}

	public Page<FrameDepartment> getFramList(Integer pageCurrent, Integer pageSize){
		return frameDepartmentDao.selectByPage(pageCurrent,pageSize);
	}


}
