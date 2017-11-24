package com.vcread.pcp.dao;

import java.util.List;

import com.vcread.pcp.entity.UserRole;

/**
 * 
 * Title: UserRoleDao<br>
 * Description: <br>
 * @author ZhanWei
 * @createDate 2017年11月23日
 */
public interface UserRoleDao {

	List<UserRole> listByUserCode(String userCode);

}
