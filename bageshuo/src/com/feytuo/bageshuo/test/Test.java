package com.feytuo.bageshuo.test;

import java.sql.SQLException;

import javax.transaction.Transaction;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.util.JdbcUtil;

public class Test {
	public static void main(String[] args) throws SQLException {
		Test test = new Test();
		Object flag = test.insertUser("ttt", "aaa");
		System.out.println(flag);
	}

	public Object insertUser(String name, String password) throws SQLException {
		Object flag = null;
		String sql = "insert into testuser(name,password) values(?,?)";
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		//Transaction tx = new 
		try {
			int id = runner.update(sql, new Object[] { name, password });
			if (id > 0) {
		    Object queryid = runner.query("SELECT LAST_INSERT_ID()",new ScalarHandler(1));
		    flag = queryid;
			return flag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return flag;
	}
}
