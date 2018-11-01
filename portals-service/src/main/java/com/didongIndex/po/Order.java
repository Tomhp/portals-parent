package com.didongIndex.po;

/**
 * 系统名称：餐厅点餐系统
 * 模块名称：Order
 * 功能描述：订单实体类
 * 模块作者：LIHEPING
 * 开发时间：2017年2月25日下午7:03:41
 * 模块路径:com.didongIndex.po
 * 更新记录：
 */
public class Order {

	private Integer tableCode;// 餐桌编号

	private String orderNum;// 订单号

	private String tableStatus;// 餐桌状态 0代表使用中对应顾客此时正在就餐，未结账；1代表餐桌空闲可以使用

	private String sumGoodsNum;// 顾客点餐的商品总数

	private String sumPrice;// 消费金额

	public Integer getTableCode() {
		return tableCode;
	}

	public void setTableCode(Integer tableCode) {
		this.tableCode = tableCode;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}

	public String getSumGoodsNum() {
		return sumGoodsNum;
	}

	public void setSumGoodsNum(String sumGoodsNum) {
		this.sumGoodsNum = sumGoodsNum;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

}
