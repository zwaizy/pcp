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
		String sql = "select * from framedepartment where dept_code =? and fram_code="+framCode;
		return queryForObject(sql, FrameDepartment.class, deptCode);
	}

	@Override
	public Page<Map<String, Object>> selectByPage(Map<String,Object> param){
		String sql = "select * from framedepartment";
		int pageCurrent =Integer.parseInt(param.get("pageCurrent").toString());
		int pageSize=Integer.parseInt(param.get("pageCurrent").toString());
		return  queryForPage(sql,pageCurrent,pageSize);
	}


}
