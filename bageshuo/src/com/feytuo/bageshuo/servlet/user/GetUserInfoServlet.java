package com.feytuo.bageshuo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.service.UserService;

public class GetUserInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		String device_id = request.getParameter("device_id");
		int target_u_id = Integer.parseInt(request.getParameter("target_u_id"));
		System.out.println("uid=="+u_id);
		System.out.println("device_id=="+device_id);
		System.out.println("target_u_id=="+target_u_id);

		UserService userService = new UserService();
		PrintWriter writer = response.getWriter();
		JSONObject object = new JSONObject();
		User user = null;
		int code = 100;
		String msg = "获取用户信息成功";
		try {
			user = userService.getUserInfoByUid(target_u_id, u_id, device_id);
			if (user != null) {
				JSONObject data = new JSONObject();
				data.put("u_id", user.getU_id());
				data.put("u_head", user.getU_head());
				data.put("u_nick", user.getU_nick());
				data.put("u_bage", user.getU_bage());
				data.put("u_sex", user.getU_sex());
				data.put("u_home", user.getU_home());
				data.put("u_sign", user.getU_sign());
				object.put("data", data);
				object.put("code", code);
				object.put("msg", msg);
				writer.print(object);
			} else {
				object.put("code", 101);
				object.put("msg","获取用户信息失败");
				writer.print(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
