package com.feytuo.bageshuo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.service.UserService;

public class UserRegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 用户注册方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String u_name = request.getParameter("u_name");
		String u_pwd = request.getParameter("u_pwd");
		String u_type = request.getParameter("u_type");
		String device_id = request.getParameter("device_id");
		String u_push_id = request.getParameter("u_push_id");
		
		String u_nick = "昵称";
		String u_bage = "123456";
		String u_sex = "女";
		String u_home = "长沙";
		
		User user = new User();
		user.setU_name(u_name);
		user.setU_name(u_pwd);
		user.setU_name(u_type);
		user.setU_name(device_id);
		user.setU_name(u_push_id);
		user.setU_name(u_nick);
		user.setU_name(u_bage);
		user.setU_name(u_sex);
		user.setU_name(u_home);
		
		UserService userService = new UserService();
		boolean flag = userService.userRegister(user);

	}
}
