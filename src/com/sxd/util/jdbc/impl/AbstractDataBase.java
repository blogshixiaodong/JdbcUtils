package com.sxd.util.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sxd.util.jdbc.IDataBase;

/*
 * date:   2018年3月22日 下午8:27:51
 * author: Shixiaodong
 */
enum LOAD_WAY {
	BY_DRIVER,
	BY_JDNI
}


public abstract class AbstractDataBase implements IDataBase {
	protected static String driverName;
	protected static String url;
	protected static String userName;
	protected static String password;
	protected static Connection conn;
	
	private LOAD_WAY way;
	protected static DataSource ds;
	
		
	//Class加载注册驱动
	public AbstractDataBase(String driverName, String url, String userName, String password) {
		setLoadWay(LOAD_WAY.BY_DRIVER);
		AbstractDataBase.driverName = driverName; 
		AbstractDataBase.url = url;
		AbstractDataBase.userName = userName;
		AbstractDataBase.password = password;
		loadDriver();
		conn = getConnection();
		
	}
	
	//从数据源（容器）中获取数据库连接
	public AbstractDataBase(String JdniName) {
		setLoadWay(LOAD_WAY.BY_JDNI);
		ds = getDataSource(JdniName);
	}
	
	private void setLoadWay(LOAD_WAY way) {
		this.way = way;
	}
	
	private void loadDriver()  {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			//cann't load driver
			e.printStackTrace();
		}
	}
	
	private synchronized DataSource getDataSource(String JdniName) {
		InitialContext init = null;
		try {
			init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/" + JdniName);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return ds;
	}
	
	public DataSource getDataSource() {
		return ds;
	}

	@Override
	public Connection getConnection() {
		try {
			if(way.equals(LOAD_WAY.BY_DRIVER)) {
				if(conn == null || conn.isClosed()) {
					conn = DriverManager.getConnection(url, userName, password);
				}
			} else if(way.equals(LOAD_WAY.BY_JDNI)) {
				if(conn == null || conn.isClosed()) {
					conn = ds.getConnection();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void closeQuickly() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				//nothing
			}
		}	
	}
}
