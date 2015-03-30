package com.feytuo.bageshuo.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.feytuo.bageshuo.service.UserService;

public class ThreeLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4539837249715269915L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String u_name = request.getParameter("u_name");
		String u_pwd = request.getParameter("u_pwd");
		String u_type = request.getParameter("u_type");
		String device_id = request.getParameter("device_id");
		String u_push_id = request.getParameter("u_push_id");
		
		System.out.println(u_name+"--"+u_pwd+"--"+u_type+"--"+device_id+"--"+u_push_id);
		int code = 0;
		String msg = "";
		UserService userService = new UserService();
		int isLoginSuccess = 0;
		try {
			isLoginSuccess = userService.threeLogin(u_name, u_pwd, u_type, device_id, u_push_id);
			if(isLoginSuccess > 0){//返回大于0（uid）
				if(userService.isThreeLoginExist()){
					code = 99;
					msg = "登录成功,老用户";
				}else{
					code = 100;
					msg = "登录成功,新用户";
				}
			}else if(isLoginSuccess == -2){
				code = 101;
				msg = "登录失败，八哥号创建失败";
			}else if(isLoginSuccess == -3){
				code = 101;
				msg = "登录失败，环信注册失败";
			}else{
				code = 101;
				msg = "登录失败";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = 101;
			msg = "登录失败，服务器问题";
			e.printStackTrace();
		}
		
		//数据封装成json
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("code", code);
			if(code == 100 || code == 99){
				JSONObject dataObject = new JSONObject();
				dataObject.put("u_id", isLoginSuccess);
				jObject.put("data",dataObject);
			}
			jObject.put("msg", msg);
			//发送返回数据
			response.getWriter().print(jObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
