package com.vcread.pcp.dao.impl;

import com.vcread.pcp.dao.FrameDepartmentDao;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.util.base.JdbcDaoImpl;
import com.vcread.pcp.util.base.Page;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 
 * Title: FrameDepartmentImpl<br>
 * Description: <br>
 * @author xyy
 * @createDate 2017年11月23日
 */
@Repository
public class FrameDepartmentImpl extends JdbcDaoImpl implements FrameDepartmentDao {

	@Override
	public FrameDepartment selectByDepartmentName (String deptCode,String framCode) {
		String sql = "select * from framedepartment where dept_code =? and fram_code= ?";
		return queryForObject(sql, FrameDepartment.class, deptCode,framCode);
	}

	@Override
	public Page<FrameDepartment> selectByPage(int pageCurrent, int pageSize){
		String sql = "select * from framedepartment";
		return  queryForPage(sql,pageCurrent,pageSize,FrameDepartment.class);
	}


}
