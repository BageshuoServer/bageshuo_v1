package com.feytuo.bageshuo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.aliyun.oss.OSSClient;
import com.feytuo.bageshuo.aliyun.AliYunUpload;
import com.feytuo.bageshuo.service.UserService;

public class SetUserInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 判断是否是上传表单是否为multipart/form-data类型
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		 String accessKeyId;	accessKeyId = "UctImVM65WFfyWhU";
		 String accessKeySecret;	accessKeySecret = "1i4ky4qL3FtoVH8qaka22B1roGxz5T";
		// 以杭州为例
		 String endpoint;	endpoint = "http://oss-cn-qingdao.aliyuncs.com";
		 String bucket;	bucket = "bageshuo";
		 OSSClient client;	client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		
		if (ServletFileUpload.isMultipartContent(request)) {
			// 创建 DiskFileItemFactory工厂 对象
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			// 创建DiskFileItemFactory的解析器对象
			ServletFileUpload fileUpload = new ServletFileUpload(
					diskFileItemFactory);
			// 设置上传头的编码格式
			fileUpload.setHeaderEncoding("UTF-8");
			// 设置文件上传大小的限制 为1M
			fileUpload.setFileSizeMax(1024 * 1024);
			// 注册监听 已经上传的进度
			fileUpload.setProgressListener(new ProgressListener() {
				@Override
				public void update(long pBytesRead, long pContentLength,
						int pItems) {

					System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
							+ pContentLength);

				}
			});

			try {
				// 解析request请求
				List<FileItem> fileItems = fileUpload.parseRequest(request);
				// fileItem 对应<input type="file" name="upload" id="upfile" />
				// fileItem <input type="text" name="user" />
				// 遍历操作
				int u_id = 0;
				String divice_id = "";
				String u_nick = "";
				String u_sex = "";
				String u_home = "";
				String u_sign = "";
				String u_head = "";
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

						if ("u_nick".equals(fileItem.getFieldName())) {
							u_nick = fileItem.getString().trim();
							System.out.println("u_nick==" + u_nick);
						}
						if ("u_sex".equals(fileItem.getFieldName())) {
							u_sex = fileItem.getString().trim();
							System.out.println("u_sex==" + u_sex);
						}
						if ("u_home".equals(fileItem.getFieldName())) {
							u_home = fileItem.getString().trim();
							System.out.println("u_home==" + u_home);
						}

						if ("u_sign".equals(fileItem.getFieldName())) {
							u_sign = fileItem.getString().trim();
							System.out.println("u_sign==" + u_sign);
						}
					} else {
						AliYunUpload upload = new AliYunUpload();
				        upload.createBucket(client,bucket);
				        u_head = upload.putObject(client, bucket,fileItem);
						fileItem.delete();

					}
				}
				// 封装成json对象
			    JSONObject obj = new JSONObject();
				UserService service = new UserService();
				PrintWriter writer = response.getWriter();
				int code = 0;
				String msg;
		        try {
		        	//将数据保存到数据库里面，包括了图片的url
		        	UserService userService = new UserService();
					boolean isSuccess = userService.setUserInfo(u_head, divice_id, u_nick, u_sex, u_home, u_sign, u_id);
					if(isSuccess) {
						code = 100;
						msg = "设置成功";
					}else {
						code = 101;
						msg = "设置成功";
					}
					
					JSONObject objData = new JSONObject();
					 objData.put("u_head", u_head);
					 if(code==100) {
						 obj.put("data", objData);
					 }
					 obj.put("code", code);
					 obj.put("msg", msg);
					 writer.print(obj);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (FileUploadException e) {
				// 获取错误的异常信息
				String message = e.getMessage();
				// The field upload exceeds its maximum permitted size of
				// 1048576 bytes.
				// 如果存在 限制文件大小的字符 就提示 文件上传过大
				if (message.contains("permitted size of")) {
					request.setAttribute("msg", "文件上传过大");
				}
				// 转发到 文件上传界面
				request.getRequestDispatcher("./upload.jsp").forward(request,
						response);
			}

		}
	}
}
