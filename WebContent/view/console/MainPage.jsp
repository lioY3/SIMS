<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.nothread{
 text-decoration:none;
}
.ward {
   color: #C5DFF8;
  text-shadow: 5px 5px 0 #fff, 7px 7px 0 #C5DFF8;
   width: 440px;
      padding: 30px;
      font: bold 55px/100% "微软雅黑", "Lucida Grande", "Lucida Sans", Helvetica, Arial, Sans;
       text-transform: uppercase;
      
}

</style>
<title>Insert title here</title>
</head>
<body>
<div style="position:absolute;left:20%; top:0%;bottom:0%; width:100%; height:100%; z-index:-1" >   
	<img src="${pageContext.request.contextPath }/static/image/background.png" height="100%" width="80%"/>   
</div>
<div style="position:absolute;left:15%; top:25%;bottom:0%;">
<p><font class="ward">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎使用</font></p><br>
<p><font class="ward">
学生信息管理系统!!
</font></p>
</div>
</body>
</html>