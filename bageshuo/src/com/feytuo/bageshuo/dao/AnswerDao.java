package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 回答表操作
 * 
 * @author Tms
 * 
 */
public class AnswerDao {
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
	 * 向回答表中插入数据（根据对应的问题的id）
	 * 
	 * @param an_location
	 * @param an_time
	 * @param an_word
	 * @param an_voice
	 * @param an_praise_num
	 * @param u_id
	 * @param pr_id
	 * @return
	 * @throws Exception
	 */
	public boolean insertAnswer(String an_location, java.util.Date an_time,
			String an_word, String an_voice, int an_praise_num, int u_id,
			int pr_id) throws Exception {
		boolean isSuccessInsert = false;
		String sql = "insert into answer(an_location,an_time,an_word,an_voice,an_praise_num,u_id,pr_id) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner();
		Object[] params = new Object[] { an_location, an_time, an_word,
				an_voice, an_praise_num, u_id, pr_id };
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
