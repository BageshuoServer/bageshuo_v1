package com.feytuo.bageshuo.servlet.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.feytuo.bageshuo.dao.UserDao;
import com.feytuo.bageshuo.domian.Carousel;
import com.feytuo.bageshuo.service.CarouselService;

/**
 * 得到轮播列表
 * @author tms
 *
 */
public class GetCarouselListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		String device_id = request.getParameter("device_id");
		System.out.println("device_id"+device_id);
		
		PrintWriter writer = response.getWriter();
		UserDao userDao = new UserDao();
		int code = 101;
		String msg = "获取轮播列表失败";
		JSONObject object = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			boolean isExist = false;
			//先判断用户是否存在
			isExist = userDao.queryUserByUidAndDeviceId(u_id, device_id);
			if(isExist) {
				code = 100;
				msg = "获取轮播列表成功";
				CarouselService carouselService = new CarouselService();
				List<Carousel> carouselList = new ArrayList<Carousel>();
				carouselList = carouselService.getCarouselList();
				for(Carousel carousel:carouselList) {
					JSONObject carouselobject = new JSONObject();
					carouselobject.put("ca_id", carousel.getCa_id());
					carouselobject.put("ca_theme", carousel.getCa_theme());
					carouselobject.put("ca_pic", carousel.getCa_pic());
					carouselobject.put("ca_time", carousel.getCa_time());
					carouselobject.put("ca_content", carousel.getCa_content());
					jsonArray.put(carouselobject);
				}
				object.put("code", code);
				object.put("data", jsonArray);
				object.put("msg",msg);
				writer.print(object);
			}else {
				object.put("code", code);
				object.put("msg", msg);
				writer.print(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
