/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.vcread.pcp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vcread.pcp.configure.WebSecurityConfig;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.entity.UserDept;
import com.vcread.pcp.result.Result;
import com.vcread.pcp.result.ResultGenerator;
import com.vcread.pcp.service.FrameDepartmentService;
import com.vcread.pcp.service.UserDeptService;

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
	public Result get(HttpServletRequest request) {
		boolean role= (boolean) request.getSession().getAttribute(WebSecurityConfig.SESSION_ROLE);
		String userName = (String) request.getSession().getAttribute(WebSecurityConfig.SESSION_KEY);
		if(role){
			List<Map<String,Object>> framList = frameDepartmentService.getFramList();
			for (Map<String, Object> map : framList) {
				String framCode = map.get("fram_code").toString();
				UserDept userDept = this.userDeptService.findByFramCode(framCode);
				if(userDept != null){
					map.put("user_name", userDept.getUser_code());
				}else{
					map.put("user_name", "");
				}
			}
			return ResultGenerator.genSuccessResult(framList);
		}else{
			UserDept userDept=userDeptService.getUserDept(userName);
			FrameDepartment frameDepartment = frameDepartmentService.getFrameDepartment(userDept.getUser_code(),userDept.getFram_code());
			List<FrameDepartment> framList = new ArrayList<FrameDepartment>();
			if(frameDepartment != null){
				frameDepartment.setUser_name(userName);
				framList.add(frameDepartment);
			}
			return ResultGenerator.genSuccessResult(framList);
		}
	}
}
