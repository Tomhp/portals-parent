package com.didongIndex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.servdao.dao.impl.BaseDaoImpl;
import com.didongIndex.dao.IGoodsDao;
import com.didongIndex.po.Goods;

/**
 * 
 * 系统名称：餐厅点餐系统
 * 模块名称：goodsDaoImpl
 * 功能描述：商品信息dao实现
 * 模块作者：LIHEPING
 * 开发时间：2017年2月5日下午9:08:26
 * 模块路径:com.didongIndex.dao.impl
 * 更新记录：
 */
@Repository("goodsDao")
public class goodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao{

	@Override
	public boolean addGoods(Goods goods) {
		String querySqlKey = entityClassName +".addGoods";
		int res=getSqlSession().insert(querySqlKey, goods);
		
		return res > 0 ? true:false;
	}

	@Override
	public List<Goods> getGoods(Goods goods) {
		String querySqlKey = entityClassName +".getGoods";
		return getSqlSession().selectList(querySqlKey, goods);
	}

	@Override
	public Goods queryGoodsByCode(Integer code) {
		String querySqlKey = entityClassName +".queryGoodsByCode";
		return getSqlSession().selectOne(querySqlKey, code);
	}

	@Override
	public boolean delByCode(Integer code) {
		String querySqlKey = entityClassName +".delByCode";
		int res=getSqlSession().delete(querySqlKey, code);
		return res > 0 ? true:false;
	}

	@Override
	public boolean updateByCode(Goods goods) {
		String querySqlKey = entityClassName +".updateByCode";
		int res=getSqlSession().update(querySqlKey, goods);
		return res > 0 ? true:false;
	}

}
