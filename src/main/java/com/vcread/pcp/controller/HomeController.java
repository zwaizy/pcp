package com.vcread.pcp.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vcread.pcp.result.Result;
import com.vcread.pcp.result.ResultCode;
import com.vcread.pcp.result.ResultGenerator;
import com.vcread.pcp.service.UserService;

/**
 * 
 * Title: HomeController<br>
 * Description: <br>
 * @author ZhanWei
 * @createDate 2017年11月21日
 */
@Controller
@RequestMapping
public class HomeController {
	
	@Autowired
	private UserService userService;
    
    //接收表单，包含用户名和密码
  	@RequestMapping(value="/loginPost",method=RequestMethod.POST)
  	public String userLogin(String username,String password,String captcha,
  			HttpServletRequest  request,HttpServletResponse response,Map<String, Object> map){
  		try {
  			Result result = userService.userLogin(username, password,captcha,request,response);
  			if(result.getCode() == ResultCode.FAIL.code){
  				map.put("error", result.getMessage());
  				return "/login";
  			}
  			if((boolean)result.getData()){
  				return "redirect:/admin";
  			}
  			return "redirect:/user";
  		} catch (Exception e) {	
  			e.printStackTrace();
  		}
  		return "redirect:/login";
  	}
  	
  	@RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public Result time(){
        //时间解析  
        DateTime dateTime = DateTime.now();
        
        String time = dateTime.toString("yyyy年MM月dd日 EE",Locale.CHINESE);  
        
        return ResultGenerator.genSuccessResult(time);
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

}