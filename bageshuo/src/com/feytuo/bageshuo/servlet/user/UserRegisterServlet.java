package com.feytuo.bageshuo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.service.UserService;

public class UserRegisterServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2838920136507493399L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 用户注册方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String u_name = request.getParameter("u_name");
		String u_pwd = request.getParameter("u_pwd");
		String u_type = request.getParameter("u_type");
		String device_id = request.getParameter("device_id");
		String u_push_id = request.getParameter("u_push_id");

		System.out.println("u_push_id:" + u_push_id);
		System.out.println("u_type:" + u_type);
		System.out.println("device_id:" + device_id);

		String u_nick = "昵称";
		String u_bage = "123456";
		String u_sex = "女";
		String u_home = "长沙";

		User user = new User();
		user.setU_name(u_name);
		user.setU_pwd(u_pwd);
		user.setU_type(u_type);
		user.setDevice_id(device_id);
		user.setU_push_id(u_push_id);
		user.setU_nick(u_nick);
		user.setU_bage(u_bage);
		user.setU_sex(u_sex);
		user.setU_home(u_home);

		// 封装成json对象
		JSONObject obj = new JSONObject();

		UserService userService = new UserService();
		PrintWriter writer = response.getWriter();
		int u_id = 0;
		int code = 0;
		String msg;
		try {
			int isSuccess = userService.userRegister(user);
			if (isSuccess > 0) {
				code = 100;
				u_id = isSuccess;
				msg = "注册成功";
			} else {
				code = 101;
				msg = "用户已存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = 101;
			msg = "注册失败";
		}

		try {
			JSONObject objData = new JSONObject();
			objData.put("u_id", u_id);
			if (code == 100) {
				obj.put("data", objData);
			}
			obj.put("code", code);
			obj.put("msg", msg);
			writer.print(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
