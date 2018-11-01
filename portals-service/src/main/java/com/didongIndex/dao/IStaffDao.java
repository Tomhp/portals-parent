package com.didongIndex.dao;

import java.util.List;

import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didongIndex.po.Staff;

/**
 * 
 * 系统名称：餐厅点餐系统
 * 模块名称：staffMapper
 * 功能描述：员工管理dao层
 * 模块作者：LIHEPING
 * 开发时间：2016年12月31日下午4:55:49
 * 模块路径:com.ordersystem.dao
 * 更新记录：
 */
public interface IStaffDao extends IBaseDao<Staff> {
	// 添加
	int insertSelective(Staff recode);

	// 查询或列表展示
	List<Staff> getStaff(Staff recod);
	// 查询最大的员工号
	String getMaxCode();
	//通过Code查询
	Staff queryStaffByCode(Integer code);
	//通过Code删除
	boolean delByCode(Integer code);
	//更新
	boolean updateByCode(Staff staff);
//	boolean deleteBatch(Map<String,Object> map);
	
}
