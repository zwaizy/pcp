package com.vcread.pcp.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;
import com.vcread.pcp.configure.WebSecurityConfig;
import com.vcread.pcp.dao.UserRoleDao;
import com.vcread.pcp.dao.UsersDao;
import com.vcread.pcp.entity.UserRole;
import com.vcread.pcp.entity.Users;
import com.vcread.pcp.result.Result;
import com.vcread.pcp.result.ResultGenerator;

@Service
public class UserService {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public Result userLogin(String username, String password,String captcha,
			HttpServletRequest request, HttpServletResponse response) {
		
		String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(StringUtils.isEmpty(captcha) || !code.equalsIgnoreCase(captcha)){
			return ResultGenerator.genFailResult("验证码不正确，请重新输入");
		}
		
		Users user = usersDao.selectByName(username);
		// 如果没有该用户名
		if (null == user) {
			return ResultGenerator.genFailResult("用户名或密码错误");
		}
		
		if (!password.equals(user.getU_password())) {
			return ResultGenerator.genFailResult("用户名或密码错误");
		}
		
		List<Integer> roleList = new ArrayList<Integer>();
		//判断权限
		List<UserRole> list = this.userRoleDao.listByUserCode(username);
		if(CollectionUtils.isNotEmpty(list)){
			for (UserRole userRole : list) {
				roleList.add(userRole.getRoleId());
//				if(userRole.getRoleId() == 7 || userRole.getRoleId() == 8){
//					flag = true;
//					break;
//				}
			}
		}
		
		if(!roleList.contains(7) && !roleList.contains(8)){
			return ResultGenerator.genFailResult("没有权限访问");//目前仅支持角色id为7,8的角色访问
		}

		boolean flag = roleList.contains(8) ? true : false;
				
		request.getSession().setAttribute(WebSecurityConfig.SESSION_KEY, username);
		request.getSession().setAttribute(WebSecurityConfig.SESSION_ROLE, flag);

		return ResultGenerator.genSuccessResult(flag);
	}

	public Result logout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().invalidate();
		return ResultGenerator.genSuccessResult();
	}

}
