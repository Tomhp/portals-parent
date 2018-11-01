package com.didongIndex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.api.exception.BusinessException;
import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didong.manager.frame.servdao.service.impl.BaseServiceImpl;
import com.didongIndex.dao.IOrderDao;
import com.didongIndex.po.Goods;
import com.didongIndex.po.Order;
import com.didongIndex.po.Table;
import com.didongIndex.service.IOrderService;
@Repository("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements IOrderService{

	@Resource
	private IOrderDao orderDao;
	@Override
	public List<Order> orderList(Order order) throws Exception {
		return orderDao.getOrder(order);
	}
	
	@Override
	public List<Goods> orderGoodsList(String num) throws Exception {
		return orderDao.getOrderGoods(num);
	}

	@Override
	public boolean updateOrders(String num) throws Exception {
		return orderDao.updateOrder(num);
	}
	@Override
	public IBaseDao<Order> coreDaoImpl() throws BusinessException {
		return orderDao;
	}

	@Override
	public List<Order> getHistoryOrders(Order orderNum, int limit, int start) throws Exception {
	try {
		return orderDao.getHistoryOrder(orderNum, limit, start);
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
	}

	@Override
	public Long getHistoryOrderCounts(Order orderNum) throws Exception {
		
		try {
			return orderDao.getHistoryOrderCount(orderNum);
		} catch (Exception e) {
        throw new Exception(e.getMessage());
}
	}

	@Override
	public String getPayTableCodes(String orderNum) throws Exception {
		return orderDao.getPayTableCode(orderNum);
	}

	

	
	

}
