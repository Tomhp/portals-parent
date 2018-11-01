package com.didongIndex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.servdao.dao.impl.BaseDaoImpl;
import com.didongIndex.dao.ITableDao;
import com.didongIndex.po.Table;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：TableDaoImpl
 * 功能描述：
 * 模块作者：LIHEPING
 * 开发时间：2017年2月5日下午4:28:47
 * 模块路径:com.didongIndex.dao.impl
 * 更新记录：
 */
@Repository("tableDao")
public class TableDaoImpl extends BaseDaoImpl<Table> implements ITableDao {

	@Override
	public boolean insertTable(Table table) {
		String querySqlKey = entityClassName + ".insertTable";
		int res = getSqlSession().insert(querySqlKey, table);
		return res > 0 ? true : false;
	}

	@Override
	public boolean updateTable(Table table) {
		String querySqlKey = entityClassName + ".updateTable";
		int res=getSqlSession().update(querySqlKey, table);
		return res > 0 ? true : false;
	}

	@Override
	public List<Table> getTable(Table recode) {
		String querySqlKey = entityClassName + ".getTable";
		return getSqlSession().selectList(querySqlKey, recode);
	}

	@Override
	public String getMaxTableCode() {
        String querySqlKey=entityClassName +".getMaxTableCode";
		return getSqlSession().selectOne(querySqlKey);
	}

	@Override
	public Table queryTableByCode(Integer code) {
		String querySqlKey=entityClassName +".queryTableByCode";
		return getSqlSession().selectOne(querySqlKey, code);
	}

	@Override
	public boolean delByCode(Integer code) {
		String querySqlKey=entityClassName +".delByCode";
		int res=getSqlSession().delete(querySqlKey, code);
		return res > 0 ? true : false;
	}

	@Override
	public boolean updateTableStatus(Table code) {
		String querySqlKey=entityClassName +".updateTableStatus";
		int res = getSqlSession().update(querySqlKey, code);
		return res > 0 ? true : false;
	}

	@Override
	public List<Table> geTableCodes() {
		String querySqlKey=entityClassName +".geTableCodes";
		return getSqlSession().selectList(querySqlKey);
	}

	@Override
	public boolean updateTableToCan(String code) {
		String querySqlKey = entityClassName + ".updateTableToCan";
		int res = getSqlSession().update(querySqlKey, code);
		return res > 0 ? true : false;
	}



}
