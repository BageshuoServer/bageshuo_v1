package com.feytuo.bageshuo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//JDBC�����ࣺ�ر�����ȡ������
public final class JdbcUtil {
	private static ComboPooledDataSource dataSource;
	static{
		dataSource = new ComboPooledDataSource();
	}
	//取得连接池数据源
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
	
	//事务开始
	public static void beginTransaction(Connection conn){
		if(conn!=null) {
			try {
				if(conn.getAutoCommit()){
					conn.setAutoCommit(false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//事务开始
	public static void commitTransaction(Connection conn){
		if(conn!=null) {
			try {
				if(!conn.getAutoCommit()){
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//事务回滚
	public static void rollbackTransaction(Connection conn){
		if(conn!=null) {
			try {
				if(!conn.getAutoCommit()){
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



