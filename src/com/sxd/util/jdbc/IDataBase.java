package com.sxd.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * date:   2018年3月22日 下午8:23:11
 * author: Shixiaodong
 */
public interface IDataBase {	
	public Connection getConnection();
	public void close() throws SQLException;
	public void closeQuickly();
}
