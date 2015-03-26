package com.feytuo.bageshuo.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import com.feytuo.bageshuo.domian.Community;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 社区操作Dao
 * @author Tms
 *
 */
public class CommunityDao {
    
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
	 * 根据社区的co_id得到相应社区信息
	 * @param co_id 社区的co_Id
	 * @return
	 * @throws Exception 
	 */
	public Community queryCommunityByCoId(int co_id) throws Exception{
		Community community = new Community();
		String sql = "select * from community where co_id =?";
		QueryRunner runner = new QueryRunner();
		try {
			community  = (Community) runner.query(conn, sql, co_id, new BeanHandler(Community.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return community;
	}
}
