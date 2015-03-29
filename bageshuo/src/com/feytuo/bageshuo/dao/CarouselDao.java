package com.feytuo.bageshuo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.feytuo.bageshuo.domian.Carousel;
import com.feytuo.bageshuo.util.JdbcUtil;

/**
 * 轮播表操作
 * @author Tms
 *
 */
public class CarouselDao {

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
	 * 获得轮播列表 （按时间排序）
	 * @return 轮播列表
	 * @throws Exception
	 */
	public List<Carousel> queryCarouseListOrderByCaTime() throws Exception {
		List<Carousel> carouselList = new ArrayList<Carousel>();
		String sql = "select * from carousel order by ca_time desc";
		QueryRunner runner = new QueryRunner();
		try {
			carouselList = (List<Carousel>)runner.query(conn, sql, new BeanListHandler(Carousel.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return carouselList;
	}
}
