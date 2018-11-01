package com.didongIndex.controller;

import java.util.List;

import javax.accessibility.AccessibleRelation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didong.manager.frame.api.entity.ResponseParameterEntity;
import com.didong.manager.frame.spring.controller.AbstractBaseController;
import com.didongIndex.po.Goods;
import com.didongIndex.po.Order;
import com.didongIndex.service.IOrderService;
import com.didongIndex.service.ITableService;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：OrderController
 * 功能描述：
 * 模块作者：LIHEPING
 * 开发时间：2017年2月18日下午3:57:31
 * 模块路径:com.didongIndex.controller
 * 更新记录：
 */
@Controller
@RequestMapping("/order")
public class OrderController extends AbstractBaseController {
	// 日志操作对象
	private final Logger logger = Logger.getLogger(OrderController.class);

	// 注入service
	@Resource
	private IOrderService orderService;
	
	@Resource
	private ITableService tableService;

	/**
	 * 功能描述：跳转至顾客订单（未完成）首页
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月18日下午3:58:16
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/orderIndex")
	public String orderIndex(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return "/module/system/order/orderIndex.html";

	}
	/**
	 * 功能描述：顾客订单列表（未完成订单）
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月18日下午4:11:22
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/orderList")
	public String orderList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("order") Order order,@ModelAttribute("resp") ResponseParameterEntity resp) throws Exception{
		List<Order> orderList=orderService.query(order, resp.getLimit(), resp.getStart());
		resp.setResultFlag(true);
		resp.setResponseEntity(orderList);
		resp.setCount(orderService.getCount(order));
		success(response, resp);
		return null;
		
	}
	/**
	 * 功能描述：顾客订单详情
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月18日下午7:02:03
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/orderGoodsList")
	public String orderGoodsList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("resp") ResponseParameterEntity resp) throws Exception{
		String num=request.getParameter("orderNum");
		List<Goods> orderGoodsList=orderService.orderGoodsList(num);
		resp.setResultFlag(true);
		resp.setResponseEntity(orderGoodsList);
		resp.setCount(5l);
		success(response, resp);
		return null;
		
	}
	
	/**
	 * 功能描述：跳转至顾客订单详情页面（弹出）
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月18日下午7:08:23
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/orderGoodsListIndex")
	public String orderGoodsListIndex(HttpServletRequest request,HttpServletResponse response
		) throws Exception{
		String orderNum = request.getParameter("orderNum");
		if (StringUtils.isNotEmpty(orderNum)){
			//
	  List<Goods> orderGoodsList=orderService.orderGoodsList(orderNum);
	   request.setAttribute("orderGoodsList", orderGoodsList);
		}
		return "/module/system/order/orders.html";
	}
	
	/**
	 * 功能描述：顾客结账
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月18日下午7:02:03
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/updateOrders")
	public String updateOrders(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("resp") ResponseParameterEntity resp)throws Exception{
		String orderNum = request.getParameter("orderNum");
		String tableCode = orderService.getPayTableCodes(orderNum);
		orderService.updateOrders(orderNum);
		//同时更新该餐桌的状态
		tableService.updateTableToCan(tableCode);
		resp.setResultFlag(true);
		resp.setMessage("结账成功！");
		success(response, resp);
		return null;
		
	}
	/**
	 * 功能描述：跳转至历史订单首页
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月19日下午8:51:22
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/histotyOrderIndex")
	public String histotyOrderIndex(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "/module/system/HistoryOrder/historyOrderIndex.html";
		
	}
	/**
	 * 功能描述：跳转至历史订单首页
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月19日下午8:52:45
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/historyOrderList")
	public String historyOrderList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("order") Order order,@ModelAttribute("resp") ResponseParameterEntity resp) throws Exception{
			List<Order> historyOrderList = orderService.getHistoryOrders(order, resp.getLimit(), resp.getStart());
			resp.setResultFlag(true);
			resp.setResponseEntity(historyOrderList);
			resp.setCount(orderService.getHistoryOrderCounts(order));
			success(response, resp);
		   return null;
	}
	/**
	 * 功能描述：跳转至顾客订单详情页面（弹出）
	 * 模块作者：LIHEPING
	 * 开发时间：2017年2月18日下午7:08:23
	 * 更新记录：
	 * 返回数据：String
	 */
	@RequestMapping("/historyOrderListIndex")
	public String historyOrderListIndex(HttpServletRequest request,HttpServletResponse response
		) throws Exception{
		String orderNum = request.getParameter("orderNum");
		if (StringUtils.isNotEmpty(orderNum)){
			//
	  List<Goods> orderGoodsList=orderService.orderGoodsList(orderNum);
	   request.setAttribute("orderGoodsList", orderGoodsList);
		}
		return "/module/system/HistoryOrder/historyOrders.html";
	}
	
}
