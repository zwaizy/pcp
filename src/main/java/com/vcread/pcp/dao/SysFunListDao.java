package com.vcread.pcp.dao;

import com.vcread.pcp.entity.Users;
import com.vcread.pcp.util.base.Page;

/**
 * 
 * Title: SysFunListDao<br>
 * Description: <br>
 * @author ZhanWei
 * @createDate 2017年11月23日
 */
public interface SysFunListDao {

	/**
	 * 插入
	 * 
	 * @param Users
	 * @return
	 */
	int insert(Users users);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(int id);

	/**
	 * 更新
	 * 
	 * @param Users
	 * @return
	 */
	int updateById(Users users);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	Users selectById(int id);
	
	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	Users selectByName(String name);

	/**
	 * @return
	 */
	Page<Users> queryForPage(int pageCurrent, int pageSize, String name);

}
