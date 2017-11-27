/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.vcread.pcp.controller;

import com.vcread.pcp.configure.WebSecurityConfig;
import com.vcread.pcp.service.FrameDepartmentService;
import com.vcread.pcp.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xyy
 */
@Controller
@RequestMapping
public class FramDepartmentController {

	@Autowired
	private FrameDepartmentService frameDepartmentService;

	@RequestMapping(value = "/get")
	@ResponseBody
	public Map<String, Object> get(@RequestParam Map<String,Object> param, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		int role=Integer.parseInt(request.getSession().getAttribute("role").toString());
		String userName=request.getSession().getAttribute("user").toString();
		Page<Map<String,Object>> framList=new Page<Map<String, Object>>();
		if(role == 7){
		}else{
			framList=frameDepartmentService.getFramList(param);
		}
		resultMap.put("framList", "framList");
		//resultMap.put("name", name);
		return resultMap;
	}

}
