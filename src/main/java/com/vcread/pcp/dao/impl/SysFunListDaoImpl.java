package com.vcread.pcp.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.vcread.pcp.dao.SysFunListDao;
import com.vcread.pcp.dao.UsersDao;
import com.vcread.pcp.entity.Users;
import com.vcread.pcp.util.base.JdbcDaoImpl;
import com.vcread.pcp.util.base.Page;
import com.vcread.pcp.util.base.Sql;


/**
 * 
 * Title: SysRoleDaoImpl<br>
 * Description: <br>
 * @author ZhanWei
 * @createDate 2017年11月23日
 */
@Repository
public class SysFunListDaoImpl extends JdbcDaoImpl implements SysFunListDao {

	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/

	@Override
	public int insert(Users user) {
//		String sql = "insert into roncoo_user (name, create_time) values (?, ?)";
//		return jdbcTemplate.update(sql, user.getU_name(),);
		return 0;
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from roncoo_user where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateById(Users roncooUser) {
//		String sql = "update roncoo_user set name=?, create_time=? where id=?";
//		return jdbcTemplate.update(sql, roncooUser.getName(), roncooUser.getCreateTime(), roncooUser.getId());
		return 0;
	}

	@Override
	public Users selectById(int id) {
		String sql = "select * from users where id=?";
		/*return jdbcTemplate.queryForObject(sql, new RowMapper<Users>() {
			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users roncooUser = new Users();
				roncooUser.setId(rs.getInt("id"));
				roncooUser.setName(rs.getString("name"));
				roncooUser.setCreateTime(rs.getDate("create_time"));
				return roncooUser;
			}
		}, id);*/
		
		return queryForObject(sql, Users.class, id);
	}
	
	@Override
	public Users selectByName(String name) {
		String sql = "select * from users where u_name =?";
		/*return jdbcTemplate.queryForObject(sql, new RowMapper<Users>() {
			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users roncooUser = new Users();
				roncooUser.setId(rs.getInt("id"));
				roncooUser.setName(rs.getString("name"));
				roncooUser.setCreateTime(rs.getDate("create_time"));
				return roncooUser;
			}
		}, id);*/
		
		return queryForObject(sql, Users.class, name);
	}
	
	@Override
	public Page<Users> queryForPage(int pageCurrent, int pageSize, String name){
		// 确定参数
		/*String sql = "select * from roncoo_user where name=?";
		return queryForPage(sql.toString(), pageCurrent, pageSize, Users.class, name);*/
		
		// 若name可能为空，则要进行判定，如下
		/*StringBuffer sql = new StringBuffer("select * from roncoo_user where 1");
		if(!StringUtils.isNullOrEmpty(name)){
			// Sql.checkSql 的作用是防止sql注入
			sql.append(" and name = '").append(Sql.checkSql(name)).append("' ");
		}
		return queryForPage(sql.toString(), pageCurrent, pageSize, Users.class);*/
		
		// 若要like查询，如下
		StringBuffer sql = new StringBuffer("select * from roncoo_user where 1");
		if(!StringUtils.isEmpty(name)){
			// Sql.checkSql 的作用是防止sql注入
			sql.append(" and name like '%").append(Sql.checkSql(name)).append("%' ");
		}
		return queryForPage(sql.toString(), pageCurrent, pageSize, Users.class);
	}

}
