/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.vcread.pcp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vcread.pcp.configure.WebSecurityConfig;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.entity.UserDept;
import com.vcread.pcp.result.Result;
import com.vcread.pcp.result.ResultGenerator;
import com.vcread.pcp.service.FrameDepartmentService;
import com.vcread.pcp.service.UserDeptService;
import com.vcread.pcp.util.base.Page;

/**
 *
 * @author xyy
 */
@Controller
@RequestMapping("/frameDepartment")
public class FramDepartmentController {

	@Autowired
	private FrameDepartmentService frameDepartmentService;
	@Autowired
	private UserDeptService userDeptService;

	@RequestMapping(value = "/get")
	@ResponseBody
	public Result get(int pageCurrent, int pageSize, HttpServletRequest request) {
		boolean role= (boolean) request.getSession().getAttribute(WebSecurityConfig.SESSION_ROLE);
		String userName = (String) request.getSession().getAttribute(WebSecurityConfig.SESSION_KEY);
		if(role){
			Page<FrameDepartment> framList = frameDepartmentService.getFramList(pageCurrent,pageSize);
			return ResultGenerator.genSuccessResult(framList);
		}else{
			UserDept userDept=userDeptService.getUserDept(userName);
			FrameDepartment frameDepartment = frameDepartmentService.getFrameDepartment(userDept.getUser_code(),userDept.getFram_code());
			return ResultGenerator.genSuccessResult(frameDepartment);
		}
	}
}
