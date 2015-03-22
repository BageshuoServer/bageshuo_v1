package com.feytuo.bageshuo.test;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.feytuo.bageshuo.util.JdbcUtil;

public class Test {
	public static void main(String[] args) throws SQLException {
		Test test = new Test();
		Object flag = test.insertUser("ttt", "aaa");
		//System.out.println(flag);
	}

	public Object insertUser(String name, String password) throws SQLException {
		Object flag = null;
		String sql = "insert into testuser(name,password) values(?,?)";
		QueryRunner runner = new QueryRunner();
		java.sql.Connection conn = JdbcUtil.getDataSource().getConnection();
		
		try {
			JdbcUtil.beginTransaction(conn);
			int id = runner.update(conn,sql, new Object[] { name, password });
			if (id > 0) {
		    Object queryid = runner.query(conn,"SELECT LAST_INSERT_ID()",new ScalarHandler(1));
		    flag = queryid;
		    System.out.println(flag);
		    throw new Exception();
			}else{
				JdbcUtil.rollbackTransaction(conn);
			}
		} catch (Exception e) {
			JdbcUtil.rollbackTransaction(conn);
		}

		return flag;
	}
}
