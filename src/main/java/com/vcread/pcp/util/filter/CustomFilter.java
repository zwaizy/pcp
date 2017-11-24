//package com.vcread.pcp.util.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.vcread.pcp.dao.UsersDao;
//
///**
// * 
// * Title: CustomFilter<br>
// * Description: <br>
// * @author ZhanWei
// * @createDate 2017年11月23日
// */
//@WebFilter(filterName = "customFilter", urlPatterns = "/*")
//public class CustomFilter implements Filter {
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("init filter");
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//        HttpServletRequest servletRequest = (HttpServletRequest) request;  
//        HttpServletResponse servletResponse = (HttpServletResponse) response;  
//        HttpSession session = servletRequest.getSession();  
//        String path = servletRequest.getServletPath();  
//        System.out.println(path);  
//        String username = (String) session.getAttribute("username");
//        // 判断如果没有取到员工信息,就跳转到登陆页面
//        if (StringUtils.isEmpty(username)) {
//        	// 跳转到登陆页面
//        	servletResponse.sendRedirect("/login.html");
//        } else {
//        	// 已经登陆,继续此次请求
//        	chain.doFilter(request, response);
//        }
//        // 登陆页面无需过滤
//        if(path.indexOf("/login.html") > -1 || path.indexOf("/home/login") > -1 || path.indexOf("/modulejs") > -1 || path.indexOf("/resources") > -1 || path.indexOf("/images") > -1 || path.indexOf("/favicon.ico") > -1) {
//	         chain.doFilter(request, response);
//	         return;
//        }
//	}
//
//	@Override
//	public void destroy() {
//		System.out.println("destroy filter");
//	}
//
//}
