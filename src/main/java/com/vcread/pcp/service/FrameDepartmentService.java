package com.vcread.pcp.service;
import com.vcread.pcp.dao.FrameDepartmentDao;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class FrameDepartmentService {

	@Autowired
	private FrameDepartmentDao frameDepartmentDao;
	
	public FrameDepartment getFrameDepartment(String deptCode, String framCode ) {
		FrameDepartment listDept=frameDepartmentDao.selectByDepartmentName(deptCode,framCode);
		return  listDept;
	}

	public Page<Map<String, Object>> getFramList(Map<String,Object> param){
		return frameDepartmentDao.selectByPage(param);
	}


}
