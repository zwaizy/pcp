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
public class FrameDepartmentDaoImpl extends JdbcDaoImpl implements FrameDepartmentDao {

	@Override
	public FrameDepartment selectByDepartmentName (String deptCode,String framCode) {
		String sql = "select * from framedepartment where dept_code =? and fram_code= ?";
		try {
			return queryForObject(sql, FrameDepartment.class, deptCode,framCode);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> findAll(){
		String sql = "select * from framedepartment ";
		try {
			return this.jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


}
