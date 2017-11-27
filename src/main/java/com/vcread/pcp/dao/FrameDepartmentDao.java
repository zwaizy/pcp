package com.vcread.pcp.dao;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.util.base.Page;

import java.util.Map;

/**
 * 
 * Title: UserDeptDao<br>
 * Description: <br>
 * @author xyy
 * @createDate 2017年11月23日
 */
public interface FrameDepartmentDao {
	
	/**
	 * 查找
	 *
	 * @param deptCode,framCode
	 * @return
	 */
	FrameDepartment selectByDepartmentName(String deptCode,String framCode);


	public Page<FrameDepartment> selectByPage(int pageCurrent, int pageSize);

}
