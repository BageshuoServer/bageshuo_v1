<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
  
</head>  
  
<body>  
  
    <form action="user/SetUserInfo"
        method="post" enctype="multipart/form-data">  
             id：<input type="text" name="u_id" value="7" /><p>
             decice_id<input type="text" name="device_id" value="DFD8D7FUFD8DFID9"/><p>
             u_nick:<input type="text" name="u_nick" value="丁神带你飞"/><p>
             u_sex:<input type="text" name="u_sex" value="爷们"/><p>
             u_home:<input type="text" name="u_home" value="湘潭"/><p>
             u_sign:<input type="text" name="u_sign" value="带你装X，带你飞"/><p>
             u_head:<input type="file" name="u_head"/><p> 
             <input type="submit" value="提交"/> 
    </form>  
</body>  
</html>  