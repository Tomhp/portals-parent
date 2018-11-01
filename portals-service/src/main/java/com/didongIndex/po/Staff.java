package com.didongIndex.po;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：Stuff
 * 功能描述：员工实体类
 * 模块作者：LIHEPING
 * 开发时间：2016年12月31日下午4:00:03
 * 模块路径:com.ordersystem.po
 * 更新记录：
 */
public class Staff {
	private Integer id;//主键id
	private Integer staffCode;// 工号
	private String staffName;// 姓名
	private String sex;// 性别
	private String position;// 职务
	private String contactWay;// 联系方式
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStaffCode() {
		return staffCode;
	}

	public void setStaffCode( Integer staffCode) {
		this.staffCode =staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName == null ? null : staffName.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay == null ? null : contactWay.trim();
	}

	
	}
	

