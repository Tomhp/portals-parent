package com.didongIndex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.api.exception.BusinessException;
import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didong.manager.frame.servdao.service.impl.BaseServiceImpl;
import com.didongIndex.dao.IGoodsDao;
import com.didongIndex.po.Goods;
import com.didongIndex.service.IGoodsService;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：GoodsServiceImpl
 * 功能描述：商品信息管理service实现
 * 模块作者：LIHEPING
 * 开发时间：2017年2月5日下午9:26:33
 * 模块路径:com.didongIndex.service.impl
 * 更新记录：
 */
@Repository("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService{
@Resource
private IGoodsDao goodsDao;
	@Override
	public boolean insertGoods(Goods goods) throws Exception {
		return goodsDao.addGoods(goods);
	}

	@Override
	public List<Goods> getAllGoods(Goods goods) throws Exception {
		
		return goodsDao.getGoods(goods);
	}

	@Override
	public Goods queryByCode(Integer code) throws Exception {
		
		return goodsDao.queryGoodsByCode(code);
	}

	@Override
	public boolean delGoodsByCode(Integer code) throws Exception {
		
		return goodsDao.delByCode(code);
	}

	@Override
	public boolean updateGoodsByCode(Goods goods) throws Exception {
		
		return goodsDao.updateByCode(goods);
	}

	@Override
	public IBaseDao<Goods> coreDaoImpl() throws BusinessException {
		
		return goodsDao;
	}

}
