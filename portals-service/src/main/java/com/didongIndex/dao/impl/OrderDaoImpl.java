package com.didongIndex.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.didong.manager.frame.servdao.dao.impl.BaseDaoImpl;
import com.didongIndex.dao.IOrderDao;
import com.didongIndex.po.Goods;
import com.didongIndex.po.Order;
import com.didongIndex.po.Table;
/**
 * 系统名称：餐厅点餐系统
 * 模块名称：OrderDaoImpl
 * 功能描述：
 * 模块作者：LIHEPING
 * 开发时间：2017年2月18日下午12:29:04
 * 模块路径:com.didongIndex.dao.impl
 * 更新记录：
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements IOrderDao {

	@Override
	public List<Order> getOrder(Order order) {
       String querySqlKey = entityClassName + ".getOrder";
		
		return getSqlSession().selectList(querySqlKey, order);
	}

	@Override
	public List<Goods> getOrderGoods(String num) {
		 String querySqlKey = entityClassName + ".getOrderGoods";
		return getSqlSession().selectList(querySqlKey, num);
	}

	@Override
	public boolean updateOrder(String num) {
		String querySqlKey = entityClassName + ".updateOrder";
		int res= getSqlSession().update(querySqlKey, num);
		return res > 0 ? true :false;
	}

	@Override
	public List<Order> getHistoryOrder(Order orderNum, int limit, int start) {
		String querySqlKey = entityClassName + ".getHistoryOrder";
		RowBounds rowBounds = new RowBounds(start, limit);
		return getSqlSession().selectList(querySqlKey, orderNum, rowBounds);
	}

	@Override
	public Long getHistoryOrderCount(Order orderNum) {
		String querySqlKey = entityClassName + ".getHistoryOrderCount";
		return getSqlSession().selectOne(querySqlKey, orderNum);
	}

	@Override
	public String getPayTableCode(String orderNum) {
	String querySqlKey = entityClassName + ".getPayTableCode";
		return getSqlSession().selectOne(querySqlKey, orderNum);
	}

	

}
