package com.sxd.util.jdbc.impl;
/*
 * date:   2018年3月22日 下午10:29:53
 * author: Shixiaodong
 */
public class SqlServerConn extends AbstractDataBase {
	public SqlServerConn(String JdniName) {
		super(JdniName);
	}
	
	public SqlServerConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
