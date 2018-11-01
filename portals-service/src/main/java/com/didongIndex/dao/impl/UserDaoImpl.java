package com.didongIndex.dao.impl;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.servdao.dao.impl.BaseDaoImpl;
import com.didongIndex.dao.IUserDao;
import com.didongIndex.po.User;

/**
 * 系统名称：递咚-中国互联网快递
 * 模块名称：UserDaoImpl
 * 模块描述：
 * 功能列表：
 * 模块作者：LIHEPING
 * 开发时间：2017年1月11日下午4:52:16
 * 模块路径：com.didongIndex.dao.impl
 * 更新记录：
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User queryByNum(String recode) {
		String querySqlkey = entityClassName + ".queryByNum";
		return getSqlSession().selectOne(querySqlkey, recode);
	}

	@Override
	public boolean addUser(User user) {
		String querySqlkey = entityClassName + ".addUser";
		int res = getSqlSession().insert(querySqlkey, user);
		return res > 0 ? true : false;
	}

	@Override
	public boolean updateUser(User user) {
		String querySqlkey = entityClassName + ".updateUser";
		int res = getSqlSession().update(querySqlkey, user);
		return res > 0 ? true : false;
	}

	@Override
	public String getMaxNum() {
		String querySqlkey = entityClassName + ".getMaxNum";
		return getSqlSession().selectOne(querySqlkey);
	}

	@Override
	public boolean delUser(String recode) {
		String querySqlkey = entityClassName + ".delUser";
		int res = getSqlSession().delete(querySqlkey, recode);
		return res > 0 ? true : false;
	}

	@Override
	public User queryById(Integer recode) {
		String querySqlkey = entityClassName + ".queryById";
		return getSqlSession().selectOne(querySqlkey, recode);
	}

}
