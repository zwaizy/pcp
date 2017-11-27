package com.vcread.pcp.service;
import com.vcread.pcp.dao.UserDeptDao;
import com.vcread.pcp.entity.UserDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeptService {

	@Autowired
	private UserDeptDao userDeptDao;
	
	public UserDept getUserDept(String username) {
		UserDept listDept=userDeptDao.selectByName(username);
		return  listDept;
	}


}
