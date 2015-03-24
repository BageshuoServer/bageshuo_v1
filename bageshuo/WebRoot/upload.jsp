<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
  
</head>  
  
<body>  
  
    <form action="user/SetUserInfo"
        method="post" enctype="multipart/form-data">  
        <table>  
             <input type="text" name="u_id" />
             <input type="text" name="device_id" />
             <input type="text" name="u_nick" />
             <input type="text" name="u_sex" />
             <input type="text" name="u_home" />
             <input type="text" name="u_sign" />
             <input type="file" name="u_head" />  
             <input type="submit" value="提交"/> 
        </table>  
    </form>  
</body>  
</html>  