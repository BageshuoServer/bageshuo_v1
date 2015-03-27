package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
	 * 
	 * @param u_id
	 * @return
	 * @throws SQLException
	 */
	public List<CommunityUser> queryCoIdListByUid(int u_id) throws Exception {
		List<CommunityUser> communityList = new ArrayList<CommunityUser>();
		String sql = "select * from community_user where u_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			communityList = (List<CommunityUser>) runner.query(conn, sql, u_id,
					new BeanListHandler(CommunityUser.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return communityList;
	}

	/**
	 * 根据co_id得到关注此co_id对应的社区的关注数(统计有相同的co_id的u_id的数目)
	 * 
	 * @param co_id
	 * @return
	 * @throws Exception
	 */
	public List<CommunityUser> queryUidListByCoId(int co_id) throws Exception {
		List<CommunityUser> communityList = new ArrayList<CommunityUser>();
		String sql = "select * from community_user where co_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			communityList = (List<CommunityUser>) runner.query(conn, sql,
					co_id, new BeanListHandler(CommunityUser.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return communityList;
	}

	/**
	 * 用户关注社区，向中间表中添加一行数据
	 * 
	 * @param co_id
	 *            社区id
	 * @param u_id
	 *            用户id
	 * @return
	 * @throws Exception
	 */
	public boolean insertIntoComminityUser(int u_id, int co_id)
			throws Exception {
		boolean isInsert = false;
		String sql = "insert into community_user(u_id,co_id) values(?,?)";
		Object[] params = new Object[] { u_id, co_id };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				isInsert = true;
				JdbcUtil.commitTransaction(conn); // 提交
			} else {
				// 回滚
				JdbcUtil.rollbackTransaction(conn);
			}
		} catch (Exception e) {
			// 回滚
			JdbcUtil.rollbackTransaction(conn);
			return isInsert;
		}
		return isInsert;
	}

	/**
	 * 判断当前用户是否已经关注了当前的社区
	 * 
	 * @param u_id
	 *            用户ID
	 * @param co_id
	 *            社区ID
	 * @return 用户是否关注此社区
	 * @throws Exception
	 */
	public boolean queryUserIsInerest(int u_id, int co_id) throws Exception {
		boolean isInterest = false;
		String sql = "select * from community_user where u_id =? and co_id =?";
		Object[] params = new Object[] { u_id, co_id };
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql, params, new BeanHandler(
					CommunityUser.class));
			if (query != null) {
				isInterest = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return isInterest;
	}
}
