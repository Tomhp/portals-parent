package com.didongIndex.dao;

import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didongIndex.po.User;

public interface IUserDao extends IBaseDao<User> {
	
	// 通过账号查询
	User queryByNum(String recode);

	// 添加用户信息
	boolean addUser(User user);
	
	//修改用户信息
	boolean updateUser(User user);
	
	//查询最大的用户账号
	String getMaxNum();
	//删除
	boolean delUser(String  recode);
	
	//通过id查询
	User queryById(Integer recode);
	
}
