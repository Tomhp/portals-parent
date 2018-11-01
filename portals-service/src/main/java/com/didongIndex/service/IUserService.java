package com.didongIndex.service;

import com.didong.manager.frame.servdao.service.IBaseService;
import com.didongIndex.po.User;

/**
 * 系统名称：递咚-中国互联网快递
 * 模块名称：IUserService
 * 模块描述：用户登陆service接口
 * 功能列表：
 * 模块作者：LIHEPING
 * 开发时间：2016年12月29日下午2:22:29
 * 模块路径：com.didongIndex.service
 * 更新记录：
 */
public interface IUserService extends IBaseService<User> {
	
	public boolean addUser(User user) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public User getByNum(String recode)throws Exception;
	public String getMaxNum() throws Exception;
	public boolean delUser(String recode) throws Exception;		
	public User queryById(Integer recode)throws Exception;

}
