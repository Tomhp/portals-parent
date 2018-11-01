package com.didongIndex.dao;

import java.util.List;

import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didongIndex.po.Goods;
import com.didongIndex.po.Order;
import com.didongIndex.po.Table;

public interface IOrderDao extends IBaseDao<Order> {
    //未完成订单
	List<Order> getOrder(Order order);
	//订单详情
	List<Goods> getOrderGoods(String num);
	//顾客结账
	boolean updateOrder(String num);
	//分页查询历史订单
	List<Order> getHistoryOrder(Order orderNum,int limit,int start);
	//分页汇总历史订单
	Long getHistoryOrderCount(Order orderNum);
	
	//通过订单号找到正在结账操作的餐桌号 
   String getPayTableCode(String orderNum);
	
}
  