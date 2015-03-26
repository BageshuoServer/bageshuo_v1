<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h1>八哥说url测试</h1>
    <hr>
    <h3>用户模块：</h3>
    <p>用户注册：http://localhost:8080/bageshuo/user/Register?u_name=13874787877&u_pwd=hand&u_type=normal&device_id=DFD8D7FUFD8DFID9&u_push_id=38D88F8D9CK</p>
  	<p>普通登录：http://localhost:8080/bageshuo/user/NormalLogin?u_name=13874787877&u_pwd=hand&u_type=normal&device_id=DFD8D7FUFD8DFID9&u_push_id=38D88F8D9CK</p>
  	<p>第三方登录：http://localhost:8080/bageshuo/user/ThreeLogin?u_name=EDFAGA4GRA435Y6GDG&u_pwd=hand&u_type=QQ&device_id=DFD8D7FUFD8DFID9&u_push_id=38D88F8D9CK</p>
  	<p>忘记密码：http://localhost:8080/bageshuo/user/UpdatePwd?u_name=13874787877&device_id=DFD8D7FUFD8DFID9&u_pwd=newhand</p>
  	<a href="upload.jsp">设置用户信息</a>
  
  </body>
</html>
