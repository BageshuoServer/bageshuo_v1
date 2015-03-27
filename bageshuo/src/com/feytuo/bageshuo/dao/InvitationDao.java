package com.feytuo.bageshuo.dao;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.feytuo.bageshuo.domian.Invitation;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 帖子表数据库操作
 * 
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
	 * 
	 * @param co_id
	 *            社区的id co_id
	 * @return
	 * @throws Exception
	 */
	public List<Invitation> queryInvatationListByCoId(int co_id)
			throws Exception {
		List<Invitation> invitationList = new ArrayList<Invitation>();
		String sql = "select * from invitation where co_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			invitationList = (List<Invitation>) runner.query(conn, sql, co_id,
					new BeanListHandler(Invitation.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return invitationList;
	}

	/**
	 * 根据co_id获得对应社区的置顶帖，并且是通过时间进行排序的
	 * 
	 * @param co_id
	 *            社区ID
	 * @return 进过时间排序的置顶帖的列表
	 * @throws Exception
	 */
	public List<Invitation> queryTopInvationListByCoIdOrderByInvTime(int co_id)
			throws Exception {
		List<Invitation> topInvationList = new ArrayList<Invitation>();
		String sql = "select * from invitation where inv_top = 1 and co_id=? ORDER BY inv_time DESC";
		QueryRunner runner = new QueryRunner();
		try {
			topInvationList = (List<Invitation>) runner.query(conn, sql, co_id,
					new BeanListHandler(Invitation.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return topInvationList;
	}

	/**
	 * 发表帖子，向帖子表中添加信息
	 * @param inv_location
	 * @param inv_time 服务器的系统时间
	 * @param inv_word 帖子内容文字
	 * @param inv_voice 帖子语音
	 * @param inv_share_num 分享数
	 * @param inv_praise_num 点赞数
	 * @param inv_top 置顶数
	 * @param inv_outstanding 是否是发现帖
 	 * @param u_id 用户iD
	 * @param co_id 社区ID
	 * @return
	 * @throws Exception
	 */
	public boolean insertInvitation(String inv_location,
			java.util.Date inv_time, String inv_word, String inv_voice,
			int inv_share_num, int inv_praise_num, int inv_top,
			int inv_outstanding, int u_id, int co_id) throws Exception {
		boolean isInsertSuccess = false;
		String sql = "insert into" +
				" invitation(inv_location,inv_time,inv_word,inv_voice,inv_share_num,inv_praise_num,inv_top,inv_outstanding,u_id,co_id)" +
				" values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { inv_location, inv_time, inv_word,
				inv_voice, inv_share_num, inv_praise_num, inv_top,
				inv_outstanding, u_id, co_id };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				isInsertSuccess = true;
				JdbcUtil.commitTransaction(conn); // 提交
				return isInsertSuccess;
			} else {
				// 回滚
				JdbcUtil.rollbackTransaction(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚
			JdbcUtil.rollbackTransaction(conn);
			throw e;
		}
		return isInsertSuccess;
	}
	
	public static void main(String[] args) throws Exception {
		InvitationDao dao = new InvitationDao();
		boolean falg = dao.insertInvitation("inv_location",new Date(), "inv_word", "inv_voice", 1, 1, 0, 1, 23, 5);
	}
}
