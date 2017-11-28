package com.vcread.pcp.dao;
import com.vcread.pcp.entity.UserDept;

/**
 * 
 * Title: UserDeptDao<br>
 * Description: <br>
 * @author xyy
 * @createDate 2017年11月23日
 */
public interface UserDeptDao {
	
	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	UserDept selectByName(String name);

	UserDept selectByFramCode(String framCode);

}
