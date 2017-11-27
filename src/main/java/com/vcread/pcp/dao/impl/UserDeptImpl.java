package com.vcread.pcp.dao.impl;

import com.vcread.pcp.dao.UserDeptDao;
import com.vcread.pcp.dao.UsersDao;
import com.vcread.pcp.entity.UserDept;
import com.vcread.pcp.entity.Users;
import com.vcread.pcp.util.base.JdbcDaoImpl;
import com.vcread.pcp.util.base.Page;
import com.vcread.pcp.util.base.Sql;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


/**
 * 
 * Title: UsersDaoImpl<br>
 * Description: <br>
 * @author ZhanWei
 * @createDate 2017年11月23日
 */
@Repository
public class UserDeptImpl extends JdbcDaoImpl implements UserDeptDao {

	
	@Override
	public UserDept selectByName(String name) {
		String sql = "select * from user_dept where user_code =?";
		return queryForObject(sql, UserDept.class, name);
	}
	

}
