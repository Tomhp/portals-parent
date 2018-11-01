package com.didongIndex.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.didong.manager.frame.api.exception.BusinessException;
import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didong.manager.frame.servdao.service.impl.BaseServiceImpl;
import com.didong.manager.frame.tool.util.pwd.PasswordAuthUtil;
import com.didongIndex.dao.IUserDao;
import com.didongIndex.po.User;
import com.didongIndex.service.IUserService;

@Repository("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	@Resource
	private IUserDao userDao;
	
	/**
	 * 功能描述：通过id查询
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月11日上午9:55:28
	 * 更新记录：
	 */
	@Override
	public User queryById(Integer recode) throws Exception {
		// TODO Auto-generated method stub
		return userDao.queryById(recode);
	}

	/**
	* 功能描述：通过用户账号查询
	* 模块作者：LIHEPING
	* 开发时间：2016年12月29日下午2:39:49
	* 更新记录：
	*/
	@Override
	public User getByNum(String recode) throws Exception {
		// TODO Auto-generated method stub
		return userDao.queryByNum(recode);
	}

	/**
	 * 功能描述： 获取最大的用户账号
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月10日下午2:04:59
	 * 更新记录：
	 */
	@Override
	public String getMaxNum() throws Exception {
		// TODO Auto-generated method stub
		return userDao.getMaxNum();
	}

	/**
	* 功能描述：添加用户
	* 模块作者：LIHEPING
	* 开发时间：2017年1月10日上午10:17:23
	* 更新记录：
	*/
	@Override
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	/**
	* 功能描述：修改用户信息
	* 模块作者：LIHEPING
	* 开发时间：2017年1月10日上午10:40:28
	* 更新记录：
	*/
	@Override
	public boolean updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	/**
	 * 功能描述：删除
	 * 模块作者：LIHEPING
	 * 开发时间：2017年1月10日下午3:33:57
	 * 更新记录：
	 */
	@Override
	public boolean delUser(String recode) throws Exception {
		// TODO Auto-generated method stub
		return userDao.delUser(recode);
	}

	@Override
	public IBaseDao<User> coreDaoImpl() throws BusinessException {
		
		return userDao;
	}

}
