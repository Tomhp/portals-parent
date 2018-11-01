package com.didongIndex.service;

import java.util.List;

import com.didong.manager.frame.servdao.service.IBaseService;
import com.didongIndex.po.Staff;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：IStaffService
 * 功能描述：员工管理service接口
 * 模块作者：LIHEPING
 * 开发时间：2016年12月31日下午5:24:33
 * 模块路径:com.ordersystem.service
 * 更新记录：
 */
public interface IStaffService  extends IBaseService<Staff> {
	//添加
	public boolean addStaffs(Staff recode) throws Exception;
	//查询
	public List<Staff> findStaff(Staff recod)throws Exception;
   public String getMax() throws Exception;
   //通过Code查询
   public Staff queryByCode(Integer code)throws Exception;
   //通过Code删除
   public boolean deleteBycode(Integer code)throws Exception;
   //通过Code更新
boolean updateStaff(Staff staff) throws Exception;
  //   boolean deleteBatch(Map<String,Object> map)throws Exception;
}
