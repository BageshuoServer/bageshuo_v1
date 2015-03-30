package com.feytuo.bageshuo.servlet.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.feytuo.bageshuo.aliyun.AliYunUpload;
import com.feytuo.bageshuo.dao.UserDao;
import com.feytuo.bageshuo.service.CommunityService;
import com.feytuo.bageshuo.service.UserService;

public class PublishInvServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		UserDao userDao = new UserDao();
		boolean isExist = false;
		if (ServletFileUpload.isMultipartContent(request)) {
			// 创建 DiskFileItemFactory工厂 对象
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			// 创建DiskFileItemFactory的解析器对象
			ServletFileUpload fileUpload = new ServletFileUpload(
					diskFileItemFactory);
			// 设置上传头的编码格式
			fileUpload.setHeaderEncoding("UTF-8");
			// 设置文件上传大小的限制 为5M
			fileUpload.setFileSizeMax(5 * 1024 * 1024);
			// 注册监听 已经上传的进度
			fileUpload.setProgressListener(new ProgressListener() {
				@Override
				public void update(long pBytesRead, long pContentLength,
						int pItems) {

					System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
							+ pContentLength);
				}
			});

			int u_id = 0;
			String divice_id = "";
			int co_id = 0;
			String inv_location = "";
			String inv_word = "";
			String inv_voice = "";

			int inv_share_num = 0;
			int inv_praise_num = 0;
			int inv_top = 0;
			int inv_outstanding = 0;
			Date inv_time = new Date();

			int code = 0;// 返回code
			String msg = "";// 返回msg
			try {
				// 解析request请求
				List<FileItem> fileItems = fileUpload.parseRequest(request);
				// fileItem 对应<input type="file" name="upload" id="upfile" />
				// fileItem <input type="text" name="user" />
				// 遍历操作
				for (FileItem fileItem : fileItems) {
					// 首先判断 是否是 普通的文本
					if (fileItem.isFormField()) {
						if ("u_id".equals(fileItem.getFieldName())) {
							u_id = Integer
									.parseInt(fileItem.getString().trim());
							System.out.println("value==" + u_id);
						}

						if ("device_id".equals(fileItem.getFieldName())) {
							divice_id = fileItem.getString().trim();
							System.out.println("divice_id==" + divice_id);
						}

						if ("co_id".equals(fileItem.getFieldName())) {
							co_id = Integer.parseInt(fileItem.getString()
									.trim());
							System.out.println("co_id==" + co_id);
						}
						if ("inv_location".equals(fileItem.getFieldName())) {
							inv_location = fileItem.getString().trim();
							inv_location = new String(inv_location.getBytes("ISO-8859-1"),"utf-8");
							System.out.println("inv_location==" + inv_location);
						}
						if ("inv_word".equals(fileItem.getFieldName())) {
							inv_word = fileItem.getString().trim();
							inv_word = new String(inv_word.getBytes("ISO-8859-1"),"utf-8");
							System.out.println("inv_word==" + inv_word);
						}

					} else {
						AliYunUpload upload = new AliYunUpload();
						upload.createBucket();
						inv_voice = upload.putObject(fileItem,
								"invitationVoice");
						fileItem.delete();
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				code = 102;
				msg = "评论失败，文件上传过大";
			}
			// 封装成json对象
			JSONObject obj = new JSONObject();
			PrintWriter writer = response.getWriter();
			try {
				if (code != 102) {
					// 将数据保存到数据库里面，包括了图片的url
					CommunityService communityuserService = new CommunityService();
					boolean isSuccess = communityuserService.publishInv(
							inv_location, inv_time, inv_word, inv_voice,
							inv_share_num, inv_praise_num, inv_top,
							inv_outstanding, u_id, co_id);
					if (isSuccess) {
						code = 100;
						msg = "评论成功";
					} else {
						code = 101;
						msg = "评论失败";
					}
				}

				JSONObject objData = new JSONObject();
				objData.put("inv_voice", inv_voice);
				if (code == 100) {
					obj.put("data", objData);
				}
				obj.put("code", code);
				obj.put("msg", msg);
				writer.print(obj);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
