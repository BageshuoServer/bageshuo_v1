package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.feytuo.bageshuo.domian.Comment;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 评论表的操作
 * @author Tms
 *
 */
public class CommentDao {
	
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
	 * 根据帖子的id获得评论列表
	 * @param inv_id 帖子的iD
	 * @return 对应的评论的列表
	 * @throws Exception
	 */
	public List<Comment> queryCommentListByInvId(int inv_id) throws Exception {
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select * from comment where inv_id=?";
		QueryRunner runner = new QueryRunner();
		try {
			List<Comment> query = (List<Comment>)runner.query(conn, sql, inv_id, new BeanListHandler(
					Comment.class));
			if (query.size()>0) {
				commentList =query;
			}
			return commentList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * 根据帖子的id获得评论数目
	 * @param inv_id 帖子的iD
	 * @return 对应的评论的数目
	 * @throws Exception
	 */
	public int queryCommentNumListByInvId(int inv_id) throws Exception {
		int topInvitationNum = 0;
		String sql = "select count(*) from comment where inv_id=?";
		QueryRunner runner = new QueryRunner();
		try {
			topInvitationNum = ((Long)runner.query(conn, sql, inv_id, new ScalarHandler(1))).intValue();
			return topInvitationNum;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
