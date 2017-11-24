/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.vcread.pcp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.vcread.pcp.configure.WebSecurityConfig;

/**
 *
 * @author zhanwei
 */
@Controller
@RequestMapping
public class WebController {

	@GetMapping("/")
	public String index(
			@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account,
			Model model) {
		model.addAttribute("name", account);
		return "index";
	}

	@RequestMapping(value = "/index")
	public String index(ModelMap map) {
		map.put("title", " wo hello word");
		return "index"; // 开头不要加上/，linux下面会出错
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login"; // 开头不要加上/，linux下面会出错
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 移除session
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "redirect:/login";
	}

}
