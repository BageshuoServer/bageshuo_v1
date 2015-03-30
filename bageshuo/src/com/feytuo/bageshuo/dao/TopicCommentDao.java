package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 话题评论表操作
 * @author Tms
 *
 */
public class TopicCommentDao {
	
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
	 * 根据话题的id在评论表中插入数据（对应用户的评论）
	 * @param to_com_location
	 * @param to_com_time
	 * @param to_com_word
	 * @param to_com_voice
	 * @param u_id  评论用户的id
	 * @param to_id 被评论的帖子的ID
	 * @return 是否评论成功
	 * @throws Exception
	 */
	public boolean insertCommentTopicByToId(String to_com_location,
			java.util.Date to_com_time, String to_com_word, String to_com_voice,
			int u_id, int to_id) throws Exception {
		boolean isSuccessInsert = false;
		String sql = "insert into topic_comment(to_com_location,to_com_time,to_com_word,to_com_voice,u_id,to_id) values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner();
		Object[] params = new Object[] { to_com_location, to_com_time, to_com_word,
				to_com_voice, u_id, to_id };
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
