package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.feytuo.bageshuo.domian.CommunityUser;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 社区用户中间表操作
 * 
 * @author Tms
 * 
 */
public class CommunityUserDao {

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
	 * 根据U_ID得到用户所关注的社区的列表	
	 * @param u_id
	 * @return
	 * @throws SQLException 
	 */
	public List<CommunityUser> queryCoIdListByUid(int u_id) throws Exception {
		List<CommunityUser> communityList = new ArrayList<CommunityUser>();
		String sql = "select * from community_user where u_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			communityList  = (List<CommunityUser>) runner.query(conn, sql, u_id, new BeanListHandler(CommunityUser.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return communityList;
	}
	
	/**
	 * 根据co_id得到关注此co_id对应的社区的关注数(统计有相同的co_id的u_id的数目)
	 * @param co_id
	 * @return
	 * @throws Exception
	 */
	public List<CommunityUser> queryUidListByCoId(int co_id) throws Exception {
		List<CommunityUser> communityList = new ArrayList<CommunityUser>();
		String sql = "select * from community_user where co_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			communityList  = (List<CommunityUser>) runner.query(conn, sql, co_id, new BeanListHandler(CommunityUser.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return communityList;
	}
}
