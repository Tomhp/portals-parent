package com.didongIndex.controller;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didong.manager.frame.api.entity.ResponseParameterEntity;
import com.didong.manager.frame.spring.controller.AbstractBaseController;
import com.didongIndex.po.Table;
import com.didongIndex.service.ITableService;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：TableController
 * 功能描述：餐桌管理控制层
 * 模块作者：LIHEPING
 * 开发时间：2017年1月2日下午10:33:51
 * 模块路径:com.ordersystem.controller
 * 更新记录：
 */

@Controller
@RequestMapping("/table")
public class TableController extends AbstractBaseController {
	@Resource
	private ITableService tableService;

	/**
	 * 功能描述：跳转至餐桌首页
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:43:12
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/tableIndex")
	public String tableIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/module/system/table/tableIndex.html";

	}

	/**
	 * 功能描述：（总/查询）列表
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:44:07
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/tableList")
	public String tableList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("recode") Table recode, @ModelAttribute("resp") ResponseParameterEntity resp)
			throws Exception {
		// 查询未结账的餐桌code集合
		List<Table> tableCodes = tableService.geTableCodess();
		

		if (!tableCodes.isEmpty()) {
			for (Iterator<Table> iterator = tableCodes.iterator(); iterator.hasNext();) {
				Table table = (Table) iterator.next();
				tableService.updateTableStatuss(table);
			}
		}
		
		List<Table> tables = tableService.query(recode, resp.getLimit(), resp.getStart());
		resp.setResultFlag(true);
		resp.setResponseEntity(tables);
		resp.setCount(tableService.getCount(recode));
		success(response, resp);
		return null;
	}

	/**
	 * 功能描述：通过餐桌号删除餐桌
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月3日下午8:54:47
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/delTableByCode")
	public String delTableByCode(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("table") Table table, @ModelAttribute("resp") ResponseParameterEntity resp)
			throws Exception {
		if (null == table || null == table.getTableCode()) {
			resp.setResultFlag(false);
			resp.setMessage("请求参数为空！");

		} else {
			boolean res = tableService.deleteTableBycode(table.getTableCode());
			resp.setResultFlag(res);
			if (!res) {
				resp.setMessage("请求处理失败，原因可能为：数据库操作失败！");
			}
		}
		success(response, resp);
		return null;
	}

	/**
	 * 功能描述：跳转至新增或是修改餐桌信息页面
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月9日下午10:27:07
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("addOrUpdateTableIndex")
	public String addOrUpdateTableIndex(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("table") Table table) throws Exception {
		if (null != table && null != table.getTableCode()) {
			Table qryTable = tableService.queryTableByCode(table.getTableCode());
			request.setAttribute("qryTable", qryTable);

		}
		return "/module/system/table/addTableIndex.html";

	}

	/**
	 * 功能描述：新增或是修改餐桌信息
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月9日下午10:36:33
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/addOrUpdateTable")
	public String addOrUpdateTable(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("table") Table table, @ModelAttribute("resp") ResponseParameterEntity resp)
			throws Exception {
		Integer Code = table.getTableCode();
		if (null != Code && Code > 0) {
			tableService.updateTable(table);
			resp.setResultFlag(true);
		} else {
			String max = tableService.getMaxCode();
			if (null == max) {
				table.setTableCode(1);
				tableService.addTables(table);
				resp.setResultFlag(true);
			} else {
				int code = Integer.parseInt(max);
				table.setTableCode(code + 1);
				tableService.addTables(table);
				resp.setResultFlag(true);
			}
		}
		success(response, resp);
		return null;
	}
}
