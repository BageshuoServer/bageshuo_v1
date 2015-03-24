package com.feytuo.bageshuo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.feytuo.bageshuo.service.UserService;

public class UpdatePwdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String u_name = request.getParameter("u_name");
		String u_pwd = request.getParameter("u_pwd");
		String device_id = request.getParameter("device_id");

		// 封装成json对象
		JSONObject obj = new JSONObject();

		UserService service = new UserService();
		PrintWriter writer = response.getWriter();
		int code = 0;
		String msg;

		try {
			boolean isUpdatePwdSuccessservice = service.updatePwd(u_name,
					u_pwd, device_id);
			if (isUpdatePwdSuccessservice) {
				code = 100;
				msg = "修改成功";
			} else {
				code = 101;
				msg = "修改失败";
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
