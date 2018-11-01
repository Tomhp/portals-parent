package com.didongIndex.service;

import java.util.List;

import com.didong.manager.frame.servdao.service.IBaseService;
import com.didongIndex.po.Table;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：ITableService
 * 功能描述：餐桌管理service接口
 * 模块作者：LIHEPING
 * 开发时间：2017年1月2日下午9:45:32
 * 模块路径:com.ordersystem.service
 * 更新记录：
 */
public interface ITableService extends IBaseService<Table> {
	// 添加
	public boolean addTables(Table table) throws Exception;

	// 修改
	public boolean updateTable(Table table) throws Exception;

	// 查询
	public List<Table> findTable(Table recode) throws Exception;

	// 查询餐桌的最大编号
	public String getMaxCode() throws Exception;

	// 通过Code查询
	public Table queryTableByCode(Integer code) throws Exception;

	// 通过Code删除
	public boolean deleteTableBycode(Integer code) throws Exception;

	// 更新餐桌的状态 改为不可用状态
	public boolean updateTableStatuss(Table code) throws Exception;

	// 查询正在使用的餐桌code集合
	public List<Table> geTableCodess() throws Exception;

	// 更新餐桌的状态 改为可用状态
	public boolean updateTableToCan(String code) throws Exception;

	

	
}
