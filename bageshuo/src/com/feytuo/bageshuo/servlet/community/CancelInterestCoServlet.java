package com.feytuo.bageshuo.servlet.community;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.feytuo.bageshuo.service.CommunityService;

public class CancelInterestCoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		int co_id = Integer.parseInt(request.getParameter("co_id"));
		String device_id = request.getParameter("device_id");
		
		System.out.println("device_id=== "+device_id);
		
		CommunityService communityService = new CommunityService();
		// 封装成json对象
		JSONObject obj = new JSONObject();
		PrintWriter writer = response.getWriter();
		int code = 100;
		String msg = "取消关注成功";
		
		try {
			boolean cancelInterestSuccess = communityService.interestCo(u_id, co_id, device_id);
			if(!cancelInterestSuccess){
				code = 101;
				msg = "取消关注失败";
			}
			obj.put("code", code);
			obj.put("msg", msg);
			writer.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
