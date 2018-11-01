package com.didongIndex.service;

import java.util.List;

import com.didong.manager.frame.servdao.service.IBaseService;
import com.didongIndex.po.Goods;
import com.didongIndex.po.Order;
import com.didongIndex.po.Table;

public interface IOrderService extends IBaseService<Order>{

	
	public List<Order> orderList(Order order)throws Exception;
	
	public List<Goods> orderGoodsList(String num) throws Exception;
	
	public boolean updateOrders(String num) throws Exception;
	
	public List<Order> getHistoryOrders(Order orderNum,int limit,int start) throws Exception;
	
	public Long getHistoryOrderCounts(Order orderNum) throws Exception;
	//通过订单号找到正在结账操作的餐桌号 
	public String getPayTableCodes(String orderNum)throws Exception;
	
}
