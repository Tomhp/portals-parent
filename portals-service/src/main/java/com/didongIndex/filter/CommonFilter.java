package com.didongIndex.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.didong.manager.frame.core.constant.FrameConstant;
import com.didong.manager.frame.core.filter.DefaultFilter;

/**
 * 系统名称：递咚-中国互联网快递
 * 模块名称：
 * 模块描述：系统层过滤器
 * 功能列表：
 * 模块作者：
 * 开发时间：
 * 模块路径：com.didong.manager.oa.common.filter.CommonFilter
 * 更新记录：
 */
public class CommonFilter extends DefaultFilter {

	private static final Log logger = LogFactory.getLog(CommonFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

	/**
	 * 设置应用信息
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 * @since:0.6
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest sreq = null;
		if (request instanceof HttpServletRequest) {
			sreq = (HttpServletRequest) request;
		}
		HttpServletResponse resp = null;
		if (response instanceof HttpServletResponse) {
			resp = (HttpServletResponse) response;
		}
		
		// 请求的uri
		String uri = sreq.getRequestURI();
		// 不过滤的uri
		String[] notFilter = new String[] { "/statics/", "/scripts/", "/user/login"};
		
		HttpSession session = sreq.getSession();
		Object userObj = session.getAttribute(FrameConstant.DDMNG_USER_LOGIN_SESSION_KEY);
		if(null == userObj) {
			// 是否过滤
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					// 如果uri中包含不过滤的uri，则不进行过滤
					doFilter = false;
					break; 
				}
			}
			if(doFilter) {
				String str = sreq.getContextPath();
				resp.sendRedirect(str + "/user/login");
			}
		}
		
		try {
			super.doFilter(request, response, filterChain);
		} finally {
			
		}
	}
}
