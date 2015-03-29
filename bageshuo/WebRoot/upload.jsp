<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
  
</head>  
  
<body>  
  
    <form action="community/PublishComment"
        method="post" enctype="multipart/form-data">  
             u_id：<input type="text" name="u_id" /><p>
             decice_id<input type="text" name="device_id"/><p>
             inv_id:<input type="text" name="inv_id" /><p>
             com_location:<input type="text" name="com_location" /><p>
             com_word:<input type="text" name="com_word" /><p>
             com_voice:<input type="file" name="com_voice"/><p> 
             <input type="submit" value="提交"/> 
    </form>  
</body>  
</html>  