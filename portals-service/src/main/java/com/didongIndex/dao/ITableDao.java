package com.didongIndex.dao;

import java.util.List;

import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didongIndex.po.Table;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：TableMapper
 * 功能描述：餐桌管理dao层
 * 模块作者：LIHEPING
 * 开发时间：2017年1月2日下午8:56:57
 * 模块路径:com.ordersystem.dao
 * 更新记录：
 */
public interface ITableDao extends IBaseDao<Table> {
	//添加
	 boolean insertTable(Table table);
	 //修改
	 boolean updateTable(Table table);
	 //查询
	 List<Table> getTable(Table recode);
	 //查询最大的餐桌号
	 String getMaxTableCode();
	 //通过Code查询
	 Table queryTableByCode(Integer code);
	 //通过Code删除
	 boolean delByCode(Integer code);
	 //更新餐桌的状态 改为不可用状态
	 boolean updateTableStatus(Table code);
	 //查询正在使用的餐桌code集合
	 List<Table> geTableCodes();
	 
	//更新餐桌的状态 --改为可用状态
    boolean updateTableToCan(String code);
   
	
 }

