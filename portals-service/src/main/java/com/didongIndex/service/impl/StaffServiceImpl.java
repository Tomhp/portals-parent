package com.didongIndex.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.didong.manager.frame.api.exception.BusinessException;
import com.didong.manager.frame.servdao.dao.IBaseDao;
import com.didong.manager.frame.servdao.service.impl.BaseServiceImpl;
import com.didongIndex.dao.IStaffDao;
import com.didongIndex.po.Staff;
import com.didongIndex.service.IStaffService;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：StaffServiceImpl
 * 功能描述：员工管理实现
 * 模块作者：LIHEPING
 * 开发时间：2016年12月31日下午5:29:08
 * 模块路径:com.ordersystem.service.impl
 * 更新记录：
 */
@Repository("staffService")
public class StaffServiceImpl extends BaseServiceImpl<Staff>  implements IStaffService{
@Resource
private IStaffDao staffDao;
	@Override
	public boolean addStaffs(Staff recode) throws Exception {
		// TODO Auto-generated method stub
		if (staffDao.insertSelective(recode) > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public List<Staff> findStaff(Staff recod) throws Exception {
		return staffDao.getStaff(recod);
	}

	@Override
	public String getMax() throws Exception {
		return staffDao.getMaxCode();
	}

	@Override
	public Staff queryByCode(Integer code) throws Exception {
		return staffDao.queryStaffByCode(code);
	}

	@Override
	public boolean deleteBycode(Integer code) throws Exception {
		return staffDao.delByCode(code);
	}

	@Override
	public boolean updateStaff(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		return staffDao.updateByCode(staff);
	}


	@Override
	public IBaseDao<Staff> coreDaoImpl() throws BusinessException {
		// TODO Auto-generated method stub
		return staffDao;
	}
}

	