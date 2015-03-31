package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 问题表操作
 * @author Tms
 *
 */
public class ProblemDao {
	private static Connection conn;

	/**
	 * 取得数据库连接
	 */
	static {
		try {
			conn = JdbcUtil.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向问题表中插入数据
	 * @param pr_location
	 * @param pr_time
	 * @param pr_word
	 * @param pr_voice
	 * @param pr_native
	 * @param pr_share_num
	 * @param pr_praise_num
	 * @param u_id  提问的人
	 * @return
	 * @throws Exception
	 */
	public boolean insertProblem(String pr_location,
			java.util.Date pr_time, String pr_word, String pr_voice,
			String pr_native,int pr_share_num,int pr_praise_num,int u_id) throws Exception {
		boolean isSuccessInsert = false;
		String sql = "insert into problem(pr_location,pr_time,pr_word,pr_voice,pr_native,pr_share_num,pr_praise_num,u_id) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner();
		Object[] params = new Object[] { pr_location, pr_time, pr_word,
				pr_voice,pr_native,pr_share_num,pr_praise_num,u_id };
		try {
			JdbcUtil.beginTransaction(conn);
			int isSuccess = runner.update(conn, sql, params);
			if (isSuccess > 0) {
				JdbcUtil.commitTransaction(conn);// 提交事务
				isSuccessInsert = true;
			} else {
				JdbcUtil.rollbackTransaction(conn);// 更新失败回滚
			}
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollbackTransaction(conn);// 更新失败回滚
			throw e;
		}
		return isSuccessInsert;
	}
}
