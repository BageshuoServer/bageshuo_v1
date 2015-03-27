package com.feytuo.bageshuo.servlet.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.feytuo.bageshuo.domian.Invitation;
import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.service.CommunityService;

public class GetTopInvServlet extends HttpServlet {

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
		System.out.println("u_id=="+u_id+"  device_id=="+device_id+" co_id=="+co_id);
		
		CommunityService communityService = new CommunityService();
		PrintWriter write = response.getWriter();

		int code = 101;
		String msg = "获取社区中置顶帖列表";
		
		JSONArray jsonArray = new JSONArray();
		JSONObject allobject = new JSONObject();
		
		List<HashMap<String, Object>> topInvationInfoList  = new ArrayList<HashMap<String,Object>>();
		try {
			topInvationInfoList = communityService.getTopInvationInfoList(u_id, device_id, co_id);
			if(!topInvationInfoList.isEmpty()) {
				code = 100;
				msg = "获取用户社区成功";
				for(int i=0;i<topInvationInfoList.size();i++){
					Invitation in = (Invitation) topInvationInfoList.get(i).get("invitation");
					int num = (Integer) topInvationInfoList.get(i).get("topInvitationNum");
					User user =  (User) topInvationInfoList.get(i).get("user");
					JSONObject object = new JSONObject();
					object.put("u_id",user.getU_id());
					object.put("u_head",user.getU_head());
					object.put("u_nick",user.getU_id());
					object.put("u_sex",user.getU_head());
					object.put("u_push_id",user.getU_push_id());
					object.put("inv_id",in.getInv_id());
					object.put("inv_location",in.getInv_location());
					object.put("inv_time",in.getInv_time());
					object.put("inv_word",in.getInv_word());
					object.put("inv_voice",in.getInv_voice());
					object.put("inv_share_num",in.getInv_share_num());
					object.put("inv_praise_num",in.getInv_praise_num());
					object.put("inv_comment_num",num);
					
					jsonArray.put(object);
				}
				allobject.put("code", code);
				allobject.put("msg", msg);
				allobject.put("data", jsonArray);
				write.print(allobject);
			}else {
				JSONObject object = new JSONObject();
				object.put("code", code);
				object.put("msg", msg);
				write.print(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
