package com.feytuo.bageshuo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.feytuo.bageshuo.service.UserService;

public class NormalLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String u_name = request.getParameter("u_name");
		String u_pwd = request.getParameter("u_pwd");
		String u_type = request.getParameter("u_type");
		String device_id = request.getParameter("device_id");
		String u_push_id = request.getParameter("u_push_id");
		
		// 封装成json对象
	    JSONObject obj = new JSONObject();
		
		UserService service = new UserService();
		PrintWriter writer = response.getWriter();
		int u_id = 0;
		int code = 0;
		String msg;
		try {
			 u_id = service.normalLogin(u_name, u_pwd,u_type,device_id,u_push_id);
		     if(u_id > 0) {
		    	 code = 100;
				 msg = "登录成功";
			 }else{
				 code = 101;
		    	 msg = "用户名或密码错误";
			 }
		     
		     JSONObject objData = new JSONObject();
			 objData.put("u_id", u_id);
			 if(code==100) {
				 obj.put("data", objData);
			 }
			 obj.put("code", code);
			 obj.put("msg", msg);
			 writer.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
