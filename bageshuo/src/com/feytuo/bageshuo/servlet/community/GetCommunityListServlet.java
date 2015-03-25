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

import com.feytuo.bageshuo.domian.CommunityUser;
import com.feytuo.bageshuo.otherentry.UserCommunityInfo;
import com.feytuo.bageshuo.service.CommunityService;
import com.google.gson.JsonObject;

public class GetCommunityListServlet extends HttpServlet {

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
		
		CommunityService communityService = new CommunityService();
		PrintWriter write = response.getWriter();
		
		int code = 101;
		String msg = "获取用户社区失败";
		
		JSONArray jsonArray = new JSONArray();
		JSONObject allobject = new JSONObject();
		
		List<UserCommunityInfo> userCommunityInfoList = new ArrayList<UserCommunityInfo>();
		try {
			userCommunityInfoList = communityService.getUserCommunityInfo(u_id, device_id);
			if(userCommunityInfoList.size()==0) {
				JSONObject object = new JSONObject();
				object.put("code", code);
				object.put("msg", msg);
				write.print(object);
			}else {
				code = 100;
				msg = "获取用户社区成功";
				for(UserCommunityInfo communityInfo:userCommunityInfoList) {
					JSONObject object = new JSONObject();
					object.put("co_name",communityInfo.getCo_name());
					object.put("co_inv_num",communityInfo.getCo_inv_num());
					jsonArray.put(object);
				}
				allobject.put("code", code);
				allobject.put("msg", msg);
				allobject.put("data", jsonArray);
				write.print(allobject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
