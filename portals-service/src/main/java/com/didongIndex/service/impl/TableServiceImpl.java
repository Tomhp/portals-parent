package com.didongIndex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.api.exception.BusinessException;
import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didong.manager.frame.servdao.service.impl.BaseServiceImpl;
import com.didongIndex.dao.ITableDao;
import com.didongIndex.po.Table;
import com.didongIndex.service.ITableService;

@Repository("tableService")
public class TableServiceImpl extends BaseServiceImpl<Table> implements ITableService {
	@Resource
	private ITableDao tableDao;

	/**
	 * 功能描述：添加餐桌service
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:26:17
	 * 更新记录：
	 */
	public boolean addTables(Table table) throws Exception {
		// TODO Auto-generated method stub
		return tableDao.insertTable(table);

	}

	/**
	 * 功能描述：查询餐桌service层
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:27:28
	 * 更新记录：
	 */

	public List<Table> findTable(Table recode) throws Exception {
		// TODO Auto-generated method stub
		return tableDao.getTable(recode);
	}

	/**
	 * 功能描述：查找餐桌最大编号
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:28:09
	 * 更新记录：
	 */
	public String getMaxCode() throws Exception {
		return tableDao.getMaxTableCode();
	}

	/**
	 * 功能描述：通过餐桌号查询餐桌
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:28:47
	 * 更新记录：
	 */
	public Table queryTableByCode(Integer code) throws Exception {
		// TODO Auto-generated method stub
		return tableDao.queryTableByCode(code);
	}

	/**
	 * 功能描述：通过餐桌号删除餐桌
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月2日下午10:29:49
	 * 更新记录：
	 */
	public boolean deleteTableBycode(Integer code) throws Exception {
		// TODO Auto-generated method stub
		return tableDao.delByCode(code);
	}

	/**
	 * 功能描述：通过tableCode修改
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月9日下午10:18:54
	 * 更新记录：
	 */
	public boolean updateTable(Table table) throws Exception {
		// TODO Auto-generated method stub
		return tableDao.updateTable(table);
	}

	@Override
	public IBaseDao<Table> coreDaoImpl() throws BusinessException {
		// TODO Auto-generated method stub
		return tableDao;
	}

	@Override
	public boolean updateTableStatuss(Table code) throws Exception {
		return tableDao.updateTableStatus(code);
	}

	@Override
	public List<Table> geTableCodess() throws Exception {
		// TODO Auto-generated method stub
		return tableDao.geTableCodes();
	}

	@Override
	public boolean updateTableToCan(String code) throws Exception {
		// TODO Auto-generated method stub
		return tableDao.updateTableToCan(code);
	}

	

}
