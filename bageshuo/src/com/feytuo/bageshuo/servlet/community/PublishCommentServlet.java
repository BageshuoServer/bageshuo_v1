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
import com.feytuo.bageshuo.service.CommunityService;

public class PublishCommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
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
			int inv_id = 0;
			String com_location = "";
			String com_word = "";
			String com_voice = "";

			Date com_time = new Date();

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

						if ("inv_id".equals(fileItem.getFieldName())) {
							inv_id = Integer.parseInt(fileItem.getString()
									.trim());
							System.out.println("inv_id==" + inv_id);
						}
						if ("com_location".equals(fileItem.getFieldName())) {
							com_location = fileItem.getString().trim();
							com_location = new String(com_location.getBytes("ISO-8859-1"),"utf-8");
							System.out.println("inv_location==" + com_location);
						}
						if ("com_word".equals(fileItem.getFieldName())) {
							com_word = fileItem.getString().trim();
							com_word = new String(com_word.getBytes("ISO-8859-1"),"utf-8");
							System.out.println("inv_word==" + com_word);
						}

					} else {
						AliYunUpload upload = new AliYunUpload();
						upload.createBucket();
						com_voice = upload.putObject(fileItem,
								"commentVoice");
						fileItem.delete();
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				code = 102;
				msg = "发表失败，文件上传过大";
			}
			// 封装成json对象
			JSONObject obj = new JSONObject();
			PrintWriter writer = response.getWriter();
			try {
				if (code != 102) {
					// 将数据保存到数据库里面，包括了图片的url
					CommunityService communityuserService = new CommunityService();
					boolean isSuccess = false;
				    isSuccess = communityuserService.publishComment(com_location, com_time, com_word, com_voice, u_id, inv_id);
					if (isSuccess) {
						code = 100;
						msg = "发表成功";
					} else {
						code = 101;
						msg = "发表失败";
					}
				}

				JSONObject objData = new JSONObject();
				objData.put("com_voice", com_voice);
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
