package com.didongIndex.po;

public class Table {
	private Integer tableCode;
	private Integer tableSize;
	private String tableStatus;
	public Integer getTableCode() {
		return tableCode;
	}
	public void setTableCode(Integer tableCode) {
		this.tableCode = tableCode;
	}
	public Integer getTableSize() {
		return tableSize;
	}
	public void setTableSize(Integer tableSize) {
		this.tableSize = tableSize;
	}
	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus==null?null:tableStatus;
	}
	

}
