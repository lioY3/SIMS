<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="bookmark" href="favicon.ico"/>

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

<script type="text/javascript">
	$(function(){
		//点击图片切换验证码
		$("#vcodeImg").click(function(){
			this.src="111.jsp"+"?"+Math.random();
		});
		
		//登录
		$("#submitBtn").click(function(){
			if($("#radio-2").attr("checked") && "${systemInfo.forbidStudent}" == 1){
				$.messager.alert("消息提醒", "学生暂不能登录系统！", "warning");
				return;
			}
			if($("#radio-3").attr("checked") && "${systemInfo.forbidTeacher}" == 1){
				$.messager.alert("消息提醒", "教师暂不能登录系统！", "warning");
				return;
			}
			
			var data = $("#form").serialize();      //序列化表单值 使用ajax()提交表单
			$.ajax({
				type: "post",
				url: "",//登录获取数据后端
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
						window.location.href = "";//管理员
					} else if("student" == msg){
						window.location.href = "";//学生
					} else if("teacher" == msg){
						window.location.href = "";//教师
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
  <div id="loginform" class="loginBox">
    <form id="form" class="form form-horizontal" method="post">
    
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="" name="account" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码" style="width:200px;">
          <img title="点击图片切换验证码" id="vcodeImg" src="111.jsp" style="height:40px;width:115px;"></div>
      </div>
      <div class="mt-20 skin-minimal" style="text-align: center;">
		<div class="radio-box">
			<input type="radio" id="radio-2" name="type" checked value="2" />
			<label for="radio-1">学生</label>
		</div>
		
		<div class="radio-box">
			<input type="radio" id="radio-3" name="type" value="3" />
			<label for="radio-2">老师</label>
		</div>
		<div class="radio-box">
			<input type="radio" id="radio-1" name="type" value="1" />
			<label for="radio-3">管理员</label>
		</div>
	  </div>
	
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="submitBtn" type="button" class="btn btn-success radius size-L " style="width:320px;background:#49bcb0"; value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        </div>
      </div>
      
      
      <div class="mt-20 skin-minimal">
      <div class="radio-box" style="left:25%;">
			<input type="radio" id="radio-4" name="type" value="1" />
			<label for="radio-3">记住密码</label>
	  </div>
      </div>
      
 

    </form>
  </div>
</div>
<div class="footer">Copyright &nbsp; @wYp </div>


</body>
</html>