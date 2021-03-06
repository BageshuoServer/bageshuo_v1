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
			e.printStackTrace();
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
	public boolean queryUserByUName(String userName) throws Exception {
		boolean isExist = false;
		String sql = "select * from user where u_name=?";
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql, userName, new BeanHandler(
					User.class));
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
		String sql = "select u_id from user where u_name=? and u_pwd=?";
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
		Object[] params = new Object[] { u_type, device_id, u_push_id, u_id };
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
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean queryUserIsExistByUname(String u_name) throws Exception {
		boolean isExist = false;
		String sql = "select * from user where u_name=?";
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql, u_name, new BeanHandler(
					User.class));
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
	 * 
	 * @param u_pwd
	 * @param device_id
	 * @param u_name
	 * @return
	 * @throws Exception
	 */
	public boolean updatePwdAndDeviceID(String u_pwd, String device_id,
			String u_name) throws Exception {
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

	/**
	 * 设置用户的基本信息
	 * 
	 * @param device_id
	 * @param u_head
	 * @param u_nick
	 * @param u_sex
	 * @param u_home
	 * @param u_sign
	 * @param u_id
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(String device_id, String u_head,
			String u_nick, String u_sex, String u_home, String u_sign, int u_id)
			throws Exception {
		boolean isUpdate = false;
		String sql = "update user set device_id=?,u_head=?,u_nick=?,u_sex=?,u_home=?,u_sign=? where u_id =? ";
		Object[] params = new Object[] { device_id, u_head, u_nick, u_sex,
				u_home, u_sign, u_id };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				isUpdate = true;
				JdbcUtil.commitTransaction(conn); // 提交
			} else {
				// 回滚
				JdbcUtil.rollbackTransaction(conn);
			}

		} catch (Exception e) {
			// 回滚
			JdbcUtil.rollbackTransaction(conn);
			e.printStackTrace();
			throw e;
		}
		return isUpdate;
	}

	/**
	 * <<<<<<< HEAD 根据用户名更新用户信息
	 * 
	 * @param u_type
	 *            登录方式
	 * @param device_id
	 *            设备id
	 * @param u_push_id
	 *            推送id
	 * @param u_name
	 *            用户名
	 * @return 是否更新成功
	 * @throws Exception
	 */
	public boolean updateTypeDeviceIDPushID(String u_type, String device_id,
			String u_push_id, String u_name) throws Exception {
		boolean isUpdate = false;
		String sql = "update user set u_type=?,device_id=?,u_push_id=? where u_name=? ";
		Object[] params = new Object[] { u_type, device_id, u_push_id, u_name };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn); // 开启事务
			System.out.println(sql + "--" + params);
			int sqlResult = runner.update(conn, sql, params);
			if (sqlResult > 0) {
				isUpdate = true;
				JdbcUtil.commitTransaction(conn); // 提交
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
	 * 根据用户名查找用户id
	 * 
	 * @param u_name
	 *            用户名
	 * @return 用户id
	 * @throws SQLException
	 */
	public int queryUidByUname(String u_name) throws SQLException {
		int uId = 0;
		String sql = "select u_id from user where u_name=? ";
		Object[] params = new Object[] { u_name };
		QueryRunner runner = new QueryRunner();
		try {
			User resultSet = (User) runner.query(conn, sql, params,
					new BeanHandler(User.class));
			if (resultSet != null) {
				uId = resultSet.getU_id();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return uId;
	}

	/**
	 * 根据uid更新八哥号
	 * 
	 * @param u_id
	 *            用户id
	 * @return 是否更新成功
	 * @throws SQLException
	 */
	public boolean updateBageByUid(String bage, int u_id) throws SQLException {
		boolean isUpdate = false;
		String sql = "update user set u_bage=? where u_id=?";
		Object[] params = new Object[] { bage, u_id };
		QueryRunner runner = new QueryRunner();
		try {
			JdbcUtil.beginTransaction(conn);
			int isSuccess = runner.update(conn, sql, params);
			if (isSuccess > 0) {
				isUpdate = true;
				JdbcUtil.commitTransaction(conn);// 提交事务
			} else {
				JdbcUtil.rollbackTransaction(conn);// 更新失败回滚
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JdbcUtil.rollbackTransaction(conn);// 更新失败回滚
			e.printStackTrace();
			throw e;
		}
		return isUpdate;
	}

	/**
	 * 根据uid删除用户
	 * 
	 * @param u_id
	 *            用户id
	 * @throws SQLException
	 */
	public void deleteUserByUid(int u_id) throws SQLException {
		String sql = "delete from user where u_id=?";
		QueryRunner runner = new QueryRunner();
		Object[] params = new Object[] { u_id };
		try {
			JdbcUtil.beginTransaction(conn);
			int isSuccess = runner.update(conn, sql, params);
			if (isSuccess > 0) {
				JdbcUtil.commitTransaction(conn);// 提交事务
			} else {
				JdbcUtil.rollbackTransaction(conn);// 更新失败回滚
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JdbcUtil.rollbackTransaction(conn);// 更新失败回滚
		}
	}

	/*
	 * 根据用户的id和device_id判断用户是否在数据库里面
	 * 
	 * @param u_id
	 * 
	 * @param device_id
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public boolean queryUserByUidAndDeviceId(int u_id, String device_id)
			throws Exception {
		boolean isUserExist = false;
		String sql = "select * from user where u_id=? and device_id =? ";
		Object[] params = new Object[] { u_id, device_id };
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql, params, new BeanHandler(
					User.class));
			if (query != null) {
				isUserExist = true;
			}
			return isUserExist;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 根据用户的id查找对应的用户对象
	 * @param u_id 用户id
	 * @return 用户对象
	 * @throws Exception
	 */
	public User queryUserByUid(int u_id) throws Exception{
		User user = null;
		String sql = "select * from user where u_id=?";
		QueryRunner runner = new QueryRunner();
		try {
			Object query = runner.query(conn, sql, u_id, new BeanHandler(
					User.class));
			if (query != null) {
				user = (User)query;
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
