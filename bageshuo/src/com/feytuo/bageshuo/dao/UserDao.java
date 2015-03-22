package com.feytuo.bageshuo.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 用户操作dao
 * 
 * @author Tms
 * 
 */
public class UserDao {

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
	 * 向数据库的用户表插入一条用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 用户ID
	 */
	public int saveUser(User user) throws Exception {
		int uId = 0;
		String sql = "insert into user(u_name,u_pwd,u_head,u_nick,u_bage,u_sex,u_home,u_sign,u_push_id,u_type,device_id) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { user.getU_name(), user.getU_pwd(),
				user.getU_head(), user.getU_nick(), user.getU_bage(),
				user.getU_sex(), user.getU_home(), user.getU_sign(),
				user.getU_push_id(), user.getU_type(), user.getDevice_id() };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				BigInteger queryId = (BigInteger) runner.query(conn,
						"SELECT LAST_INSERT_ID()", new ScalarHandler(1));
				uId = queryId.intValue();
				JdbcUtil.commitTransaction(conn); // 提交
				return uId;
			} else {
				// 回滚
				JdbcUtil.rollbackTransaction(conn);
			}
		} catch (Exception e) {
			// 回滚
			JdbcUtil.rollbackTransaction(conn);
			throw e;
		}
		return uId;
	}

	/**
	 * 根据用户名来查询用户是否在数据库中
	 * 
	 * @param u_name
	 *            用户名
	 * @return 用户是否存在
	 */
	public boolean queryUserByUName(User user) throws Exception {
		boolean isExist = false;
		String sql = "select * from user where u_name=?";
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql, user.getU_name(),
					new BeanHandler(User.class));
			if (query != null) {
				isExist = true;
			}
			return isExist;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 根据用户和密码检查是否有用户
	 * 
	 * @param u_name
	 *            用户名
	 * @param u_pwd
	 *            密码
	 * @return 用户ID
	 * @throws Exception
	 */
	public int queryUserByUNameAndUPwd(String u_name, String u_pwd)
			throws Exception {
		int uId = 0;
		String sql = "select * from user where u_name=? and u_pwd=?";
		Object[] params = new Object[] { u_name, u_pwd };
		QueryRunner runner = new QueryRunner();
		try {
			User sqlResult = (User) runner.query(conn, sql, params,
					new BeanHandler(User.class));
			if (sqlResult != null) {
				uId = sqlResult.getU_id();
				JdbcUtil.commitTransaction(conn); // 提交
				return uId;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return uId;
	}

	/**
	 * 跟新用户的type device_id push_id
	 * 
	 * @param u_type
	 *            用户登录方式
	 * @param device_id
	 *            设备ID
	 * @param u_push_id
	 *            推送方式
	 * @param u_id
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserTypeDeviceIdPushId(String u_type,
			String device_id, String u_push_id, int u_id) throws Exception {
		boolean isUpdate = false;
		String sql = "update user set u_type=?,device_id=?,u_push_id=? where u_id =? ";
		Object[] params = new Object[] { u_type, device_id, u_push_id,u_id };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				isUpdate = true;

				JdbcUtil.commitTransaction(conn); // 提交
				return isUpdate;
			} else {
				// 回滚
				JdbcUtil.rollbackTransaction(conn);
			}

		} catch (Exception e) {
			// 回滚
			JdbcUtil.rollbackTransaction(conn);
			throw e;
		}
		return isUpdate;
	}
	
	
	/**
	 * 根据用户名判断用户是否存在
	 * @return
	 * @throws Exception 
	 */
	public boolean queryUserIsExistByUname(String u_name) throws Exception{
		boolean isExist = false;
		String sql = "select * from user where u_name=?";
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql,u_name ,
					new BeanHandler(User.class));
			if (query != null) {
				isExist = true;
			}
			return isExist;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 修改密码的时候根据用户修改密码和更新相应的设备的ID
	 * @param u_pwd
	 * @param device_id
	 * @param u_name
	 * @return
	 * @throws Exception
	 */
	public boolean updatePwdAndDeviceID(String u_pwd, String device_id,String u_name) throws Exception {
		boolean isUpdate = false;
		String sql = "update user set u_pwd=?,device_id=? where u_name =? ";
		Object[] params = new Object[] { u_pwd, device_id, u_name };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				isUpdate = true;
				JdbcUtil.commitTransaction(conn); // 提交
				return isUpdate;
			} else {
				// 回滚
				JdbcUtil.rollbackTransaction(conn);
			}

		} catch (Exception e) {
			// 回滚
			JdbcUtil.rollbackTransaction(conn);
			throw e;
		}
		return isUpdate;
	}
}
