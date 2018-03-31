package com.sxd.util.jdbc.impl;
/*
 * date:   2018年3月22日 下午8:46:52
 * author: Shixiaodong
 */

public class MySqlConn extends AbstractDataBase {

	/**
	 * 提供无参构造函数，通过JNDI获取数据源对象，使用JDBC连接池
	 */
	public MySqlConn(String JdniName) {
		super(JdniName);
	}
 	

	public MySqlConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
