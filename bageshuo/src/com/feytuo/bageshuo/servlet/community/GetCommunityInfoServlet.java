package com.feytuo.bageshuo.servlet.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.feytuo.bageshuo.domian.Community;
import com.feytuo.bageshuo.service.CommunityService;

/**
 * 获取用户社区列表servlet
 * @author Tms
 *
 */
public class GetCommunityInfoServlet extends HttpServlet {

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
		int co_id = Integer.parseInt(request.getParameter("co_id"));
		System.out.println("u_id=="+u_id+"  device_id=="+device_id+"  co_id=="+co_id);
		
		CommunityService communityService = new CommunityService();
		PrintWriter write = response.getWriter();
		
		int code = 101;
		String msg = "获取社区详情失败";
		
		JSONObject object = new JSONObject();

		Map<String,Object> perCommunityInfoMap = new HashMap<String,Object>();
		try {
			perCommunityInfoMap = communityService.perCommunityInfo(u_id, co_id, device_id);
			if(perCommunityInfoMap.isEmpty()) {
				object.put("code", code);
				object.put("msg", msg);
				write.print(object);
			}else {
				code  = 100;
				msg = "获取社区详情成功";
				
		    	int co_interest_num = (Integer) perCommunityInfoMap.get("co_interest_num");
		    	int co_inv_num = (Integer) perCommunityInfoMap.get("co_inv_num");
		    	Community community = (Community) perCommunityInfoMap.get("community");
		    	
				JSONObject object2 = new JSONObject();
				object2.put("co_id",co_id);
				object2.put("co_name",community.getCo_name());
				object2.put("co_title",community.getCo_title());
				object2.put("co_head",community.getCo_head());
				object2.put("co_interest_num",co_interest_num);
				object2.put("co_inv_num",co_inv_num);
				
				object.put("code", code);
				object.put("data", object2);
				object.put("msg", msg);
				write.print(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
