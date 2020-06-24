<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="shortcut icon" href="image/favicon.ico"/>

<link href="h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">

<script type="text/javascript" src="easyui/jquery.min.js"></script> 
<script type="text/javascript" src="h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="h-ui/lib/icheck/jquery.icheck.min.js"></script> 

<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<style type="text/css">
body, html {
  margin: 0;
  width: 100%;
  height: 100%;
}
.container {
  position: relative;
  overflow: hidden;
}
img {
  transition: .1s ease-in-out;
}
.container div {
  overflow: hidden;
  float: left;
}
.blur img {
  filter: blur(15px);
  -webkit-filter: blur(15px);
}
.blur img:hover {
  filter: blur(0);
  -webkit-filter: blur(0);
}
</style>

<script type="text/javascript">
	$(function(){
		//点击图片切换验证码
		$("#vcodeImg").click(function(){
			this.src="SystemServlet?method=GetVCode&time"+new Date().getTime();
		});
		
		//登录
		$("#submitBtn").click(function(){		
			var data = $("#form").serialize();//序列化表单值 使用ajax()提交表单
			$.ajax({
				type: "post",
				url: "SystemServlet?method=Login",//登录获取数据后端
				data: data, 
				dataType: "text", //返回数据类型
				success: function(msg){
					if("vcodeError" == msg){
						$.messager.alert("消息提醒", "验证码错误!", "warning");
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					} else if("loginError" == msg){
						$.messager.alert("消息提醒", "用户名或密码错误!", "warning");
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					} else if("admin" == msg){
						window.location.href = "SystemServlet?method=toAdminView";//管理员
					} else if("teacher" == msg){
						window.location.href = "SystemServlet?method=toTeacherView";//教师
					} else if("student" == msg){
						window.location.href = "SystemServlet?method=toStudentView";//学生
					}
				}
				
			});
		});
		
		//设置复选框
		$(".skin-minimal input").iCheck({
			radioClass: 'iradio-blue',
			increaseArea: '25%'
		});
	})
</script> 
<title>登录|学生信息管理系统</title>
<meta name="keywords" content="学生成绩管理系统">
</head>
<body>
ini_set('display_errors','off');
<div class="header">SIMS</div>
<div class="header2">学生成绩管理系统</div>
<div class="loginWraper">
	<div  class="blur container" >   
	<img src="${pageContext.request.contextPath }/image/bg1.JPG" height="100%" width="100%"/>   
</div>
  <div id="loginform" class="loginBox">
    <form id="form" class="form form-horizontal" method="post">
    
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="account" name="account" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码" style="width:200px;">
          <img title="点击图片切换验证码" id="vcodeImg" style="height:40px;width:115px" src="SystemServlet?method=GetVCode"></div>
      </div>	
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="submitBtn" type="button" class="btn btn-success radius size-L " style="width:320px;background:#49bcb0" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        </div>
      </div>

    </form>
  </div>
</div>
<div class="footer">Copyright &nbsp; @wYp </div>

</body>
</html>