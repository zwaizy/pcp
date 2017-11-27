package com.vcread.pcp.configure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * Title: WebSecurityConfig<br>
 * Description: <br>
 * 
 * @author ZhanWei
 * @createDate 2017年11月24日
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

	@Value("${sp.excelsPath}")
	private String excelsPath;

	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "user";
	public final static String SESSION_ROLE = "role";

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry
				.addInterceptor(getSecurityInterceptor());

		// 排除配置"/modulejs/**", "/resources/**", "/login", "/kaptcha.jpg","
		addInterceptor.excludePathPatterns("/error");
		addInterceptor.excludePathPatterns("/login**");
		addInterceptor.excludePathPatterns("/modulejs/**");
		addInterceptor.excludePathPatterns("/resources/**");
		addInterceptor.excludePathPatterns("/images/kaptcha.jpg");

		// 拦截配置
		addInterceptor.addPathPatterns("/**");
	}

	private class SecurityInterceptor extends HandlerInterceptorAdapter {

		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			HttpSession session = request.getSession();
			if (session.getAttribute(SESSION_KEY) != null)
				return true;

			// 跳转登录
			String url = "/login";
			response.sendRedirect(url);
			return false;
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations(excelsPath);
	}
}