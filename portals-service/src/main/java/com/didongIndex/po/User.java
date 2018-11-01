package com.didongIndex.po;

public class User {
	private Integer id;
	private String usernum;//用户登陆账号
	private String userpwd;//用户登陆密码
	private String usertype;//用户类型
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum == null?null:usernum.trim();
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd== null ?null :userpwd.trim();
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype==null?null:usertype.trim();
	}
	
	

}
