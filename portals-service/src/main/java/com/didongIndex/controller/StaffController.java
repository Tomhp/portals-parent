package com.didongIndex.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didong.manager.frame.api.entity.ResponseParameterEntity;
import com.didong.manager.frame.spring.controller.AbstractBaseController;
import com.didongIndex.po.Staff;
import com.didongIndex.service.IStaffService;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：StaffController
 * 功能描述：员工模块控制层
 * 模块作者：LIHEPING
 * 开发时间：2016年12月31日下午5:35:20
 * 模块路径:com.ordersystem.controller
 * 更新记录：
 */
@Controller
@RequestMapping("/staff")
public class StaffController extends AbstractBaseController {
	// 日志操作对象
	private final Logger logger = Logger.getLogger(StaffController.class);
	// 注入
	@Resource
	private IStaffService staffService;

	/**
	 * 功能描述： 跳转至员工信息首页
	 * 模块作者：LIHEPING
	 * 开发时间：2016年12月31日下午5:42:43
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/module/system/staff/index.html";

	}

	/**
	 * 功能描述：通过对象属性查询
	 * 模块作者：LIHEPING
	 * 开发时间：2016年12月31日下午8:45:47
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/staffList")
	public String staffList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("recod") Staff recod, @ModelAttribute("resp") ResponseParameterEntity resp)
			throws Exception {

		List<Staff> staffList = staffService.query(recod, resp.getLimit(), resp.getStart());
		resp.setResultFlag(true);
		resp.setResponseEntity(staffList);
		resp.setCount(staffService.getCount(recod));
		success(response, resp);
		return null;

	}

	/**
	 * 功能描述：跳转至新增或是修改员工信息页面
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月1日上午11:08:08
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/addOrUpdateIndex")
	public String addOrUpdateIndex(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("staff") Staff staff) throws Exception {
		if (null != staff && null != staff.getStaffCode()) {
			Staff qryStaff = staffService.queryByCode(staff.getStaffCode());
         request.setAttribute("qryStaff", qryStaff);
		}
	return "/module/system/staff/addStaffIndex.html";
	}

	/**
	 * 功能描述： 添加或修改员工信息，并实现自动生成工号
	 * 模块作者：LIHEPING
	 * 开发时间：2016年12月31日下午5:55:44
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/addOrUpdateStaff")
	public String addOrUpdateStaff(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("recode") Staff recode, @ModelAttribute("resp") ResponseParameterEntity resp)
			throws Exception {
	Integer taffCode = recode.getStaffCode();
	//修改
		if(taffCode!=null && taffCode>0){ 
			
			staffService.updateStaff(recode);
			resp.setResultFlag(true);
			
		} else {
			String max = staffService.getMax();
			if (max == null) {
				recode.setStaffCode(2016001);
				staffService.addStaffs(recode);
				resp.setResultFlag(true);
	
			} else {
				int num = Integer.parseInt(max);
				recode.setStaffCode(num + 1);
				staffService.addStaffs(recode);
				resp.setResultFlag(true);
			}
		}
		success(response, resp);
		return null;

	}
	/**
	 * 功能描述：通过Code删除
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月1日下午10:14:15
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/delStaffByCode")
	public String delProduceById(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("staff") Staff staff, @ModelAttribute("resp") ResponseParameterEntity resp)
			throws Exception {
//		String string=request.getParameter("data");
//		System.out.println(string);
//	String	string2=request.getParameter("staffCode");
//	System.out.println(string2);
		if (null == staff || null == staff.getStaffCode()) {
			resp.setResultFlag(false);
			resp.setMessage("请求参数为空！");
		} else {
			boolean res = staffService.deleteBycode(staff.getStaffCode());
			resp.setResultFlag(res);
			if (!res) {
				resp.setMessage("请求处理失败，原因可能为：数据库操作失败！");
			}
		}
		success(response, resp);
		return null;
	}
   
		
		
}
