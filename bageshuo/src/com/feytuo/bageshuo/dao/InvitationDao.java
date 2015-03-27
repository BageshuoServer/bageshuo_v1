package com.feytuo.bageshuo.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.feytuo.bageshuo.domian.CommunityUser;
import com.feytuo.bageshuo.domian.Invitation;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 帖子表数据库操作
 * @author Tms
 *
 */
public class InvitationDao {

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
	 * 根据社区id(co_id)获得对应社区中的帖子
	 * @param co_id 社区的id co_id
	 * @return
	 * @throws Exception 
	 */
	public List<Invitation> queryInvatationListByCoId(int co_id) throws Exception {
		List<Invitation> invitationList = new ArrayList<Invitation>();
		String sql = "select * from invitation where co_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			invitationList  = (List<Invitation>) runner.query(conn, sql, co_id, new BeanListHandler(Invitation.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return invitationList;
	}
	
	/**
	 * 根据co_id获得对应社区的置顶帖，并且是通过时间进行排序的 
	 * @param co_id 社区ID
	 * @return 进过时间排序的置顶帖的列表
	 * @throws Exception
	 */
	public List<Invitation> queryTopInvationListByCoIdOrderByInvTime(int co_id) throws Exception{
		List<Invitation> topInvationList = new ArrayList<Invitation>();
		String sql = "select * from invitation where inv_top = 1 and co_id=? ORDER BY inv_time DESC";
		QueryRunner runner = new QueryRunner();
		try {
			topInvationList  = (List<Invitation>) runner.query(conn, sql, co_id, new BeanListHandler(Invitation.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return topInvationList;
	}
}
