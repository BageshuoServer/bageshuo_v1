package com.feytuo.bageshuo.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * �û������
 * @author Tms
 *
 */
public class UserDao {

	public boolean insertUser(User user) throws SQLException {
		boolean flag = false;
		String sql = "insert into user() values()";
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		try {
			runner.update(sql,new Object[]{user.getU_name()});
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

}
