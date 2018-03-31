package com.sxd.util.jdbc.impl;
/*
 * date:   2018年3月22日 下午8:47:41
 * author: Shixiaodong
 */
public class OracleConn extends AbstractDataBase {
	public OracleConn(String JdniName) {
		super(JdniName);
	}
	
	public OracleConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
