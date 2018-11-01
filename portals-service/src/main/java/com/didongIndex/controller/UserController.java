/**
 * 
 */
package com.didongIndex.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didong.manager.frame.api.entity.ResponseParameterEntity;
import com.didong.manager.frame.core.constant.FrameConstant;
import com.didong.manager.frame.spring.controller.AbstractBaseController;
import com.didong.manager.frame.tool.util.pwd.PasswordAuthUtil;
import com.didongIndex.po.User;
import com.didongIndex.service.IUserService;

/**
 * 系统名称：递咚-中国互联网快递
 * 模块名称：UserController
 * 模块描述：用户登陆模块控制层
 * 功能列表：
 * 模块作者：LIHEPING
 * 开发时间：2016年12月29日下午2:41:31
 * 模块路径：com.didongIndex.controller
 * 更新记录：
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractBaseController {

	// 日志操作对象
	private final Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private IUserService userService;

	/**
	 * 
	 * 功能描述：跳转至用户列表页面
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月9日下午5:19:45
	 * 更新记录：
	 */
	@RequestMapping("/userIndex")
	public String userIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {


		return "/module/system/user/userIndex.html";
	}

	/**
	 * 功能描述：用户列表（查询）
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月9日下午2:48:49
	 * 更新记录：
	 */
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user,
			@ModelAttribute("resp") ResponseParameterEntity resp) throws Exception {

		List<User> users = userService.query(user, resp.getLimit(), resp.getStart());
		resp.setResultFlag(true);
		resp.setResponseEntity(users);
		resp.setCount(userService.getCount(user));
		success(response, resp);
		return null;

	}

	/**
	 * 功能描述： 跳转至新增或是修改用户信息页面
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月10日上午10:24:45
	 * 更新记录：
	 */
	@RequestMapping("/addOrUpdateUserIndex")
	public String addOrUpdateUserIndex(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) throws Exception {
		if (null != user && null != user.getId()) {
			User qryUser = userService.queryById(user.getId());
			request.setAttribute("qryUser", qryUser);
		}

		return "/module/system/user/addOrUpdateUserIndex.html";
	}

	/**
	 * 功能描述：新增或是修改用户信息
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月10日上午11:38:59
	 * 更新记录：
	 */
	@RequestMapping("/addOrUpdateUser")
	public String addOrUpdateUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user, @ModelAttribute("resp") ResponseParameterEntity resp) throws Exception {

		/**
		 * 修改
		 */
		Integer userId = user.getId();
		if (userId != null && userId > 0) {
			String pass=PasswordAuthUtil.encryptPassword(user.getUserpwd());
			user.setUserpwd(pass);
			userService.updateUser(user);
			resp.setResultFlag(true);

		}
		else if (null == userId && userService.getByNum(user.getUsernum()) == null) {
			String string=  PasswordAuthUtil.encryptPassword(user.getUserpwd());
			user.setUserpwd(string);
			userService.addUser(user);
			resp.setResultFlag(true);

		} else {
			resp.setResultFlag(false);
			resp.setMessage("该账号已经存在，请重新输入账号!");

		}

		success(response, resp);
		return null;

	}

	/**
	 * 功能描述：删除
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月10日下午3:35:36
	 * 更新记录：
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user, @ModelAttribute("resp") ResponseParameterEntity resp) throws Exception {
		if (null == user || null == user.getUsernum()) {
			resp.setResultFlag(false);
			resp.setMessage("请求参数为空！");

		} else {
			boolean res = userService.delUser(user.getUsernum());
			resp.setResultFlag(res);
			if (!res) {
				resp.setMessage("请求处理失败，原因可能为：数据库操作失败！");
			}
		}
		success(response, resp);
		return null;
	}

	/**
	 * 
	 * 功能描述：跳转至用户登陆页面
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月9日下午5:19:45
	 * 更新记录：
	 */
//	@RequestMapping("/loginIndex")
//	public String loginIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		return "/module/system/user/reglogin/login.html";
//	}

	// 登陆
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("resp") ResponseParameterEntity resp) throws Exception {

		String usernum = request.getParameter("usernum");
		String userPwd = request.getParameter("userpwd");
		if (StringUtils.isNotBlank(usernum) && StringUtils.isNotBlank(userPwd)) {	
		// 查询用户信息
		User user = userService.getByNum(usernum);
		if (user == null) {
			resp.setResultFlag(false);
			resp.setMessage("账号不存在！请重新输入！");
			success(response, resp);
			return null;
		}

		boolean isWrong = PasswordAuthUtil.comparePassword(userPwd, user.getUserpwd());
		if (!isWrong) {
			resp.setResultFlag(false);
			resp.setMessage("账号或密码错误!");
			success(response, resp);
			return null;
		} 
			request.getSession().setAttribute(FrameConstant.DDMNG_USER_LOGIN_SESSION_KEY, user);
			resp.setResultFlag(true);
			success(response, resp);
			return null;
		}
		// 设置登录状态编码
		request.setAttribute("loginStatusCode", "UserNotLoginException");
		return "/module/system/user/reglogin/login.html";
	}
	
	// 退出系统 
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("resp") ResponseParameterEntity resp) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute(FrameConstant.DDMNG_USER_LOGIN_SESSION_KEY);
		session.invalidate();
		resp.setResultFlag(true);
		success(response, resp);
		return null;

	}
}
