package com.didongIndex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.servdao.dao.impl.BaseDaoImpl;
import com.didongIndex.dao.IStaffDao;
import com.didongIndex.po.Staff;

/**
 * 
 * 系统名称：餐厅点餐系统
 * 模块名称：staffDaoImpl
 * 功能描述：应用系统列表Dao层实现类
 * 模块作者：LIHEPING
 * 开发时间：2017年2月5日下午12:55:01
 * 模块路径:com.didongIndex.dao.impl
 * 更新记录：
 */
@Repository("staffDao")
public class staffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {

	@Override
	public int insertSelective(Staff recode) {
		String querySqlkey = entityClassName + ".insertSelective";
		return getSqlSession().insert(querySqlkey, recode);

	}

	@Override
	public List<Staff> getStaff(Staff recod) {
		String querySqlKey = entityClassName + ".getStaff";
		return getSqlSession().selectList(querySqlKey, recod);
	}

	@Override
	public String getMaxCode() {
		String querySqlkey = entityClassName + ".getMaxCode";
		return getSqlSession().selectOne(querySqlkey);
	}

	@Override
	public Staff queryStaffByCode(Integer code) {
		String querySqlkey = entityClassName + ".queryStaffByCode";
		return getSqlSession().selectOne(querySqlkey, code);
	}

	@Override
	public boolean delByCode(Integer code) {
		String querySqlkey = entityClassName + ".delByCode";
		int res = getSqlSession().delete(querySqlkey, code);
		return res > 0 ? true : false;
	}

	@Override
	public boolean updateByCode(Staff staff) {
		String querySqlkey = entityClassName + ".updateByCode";
		int res=getSqlSession().update(querySqlkey, staff);
		return res > 0 ? true : false;
	}

}
