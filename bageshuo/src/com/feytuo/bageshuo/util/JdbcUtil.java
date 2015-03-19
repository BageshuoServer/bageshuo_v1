package com.feytuo.bageshuo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//JDBC工具类：关闭流和取得连接
public final class JdbcUtil {
	private static ComboPooledDataSource dataSource;
	static{
		dataSource = new ComboPooledDataSource();
	}
	//取得数据源
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
}



