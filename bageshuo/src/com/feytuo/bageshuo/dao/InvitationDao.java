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
 * ���ӱ����
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
}
