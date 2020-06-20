<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>学生管理系统主界面</title>
<link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo"><h3><a href="MainPage.jsp">学生信息管理系统</a></h3></div>
			
			<!-- 界面上方左侧选择框 
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="https://blog.csdn.net/weixin_42855542/article/details/83476597">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			-->
			
			
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="../image/School-Logo.JPG" class="layui-nav-img"> 管理员
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">修改密码</a>
						</dd>
						<dd>
							<a href="">退出登录</a>
						</dd>
					</dl></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域   -->
				
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
					<a class=""
						href="javascript:;">学生管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="https://blog.csdn.net/weixin_42855542/article/details/83476597">学生信息查询</a>
							</dd>
							<dd>
								<a href="https://www.baidu.com">学生信息修改</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">教师管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">教师信息查询</a>
							</dd>
							<dd>
								<a href="javascript:;">教师信息修改</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">课程管理</a>
					<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">课程信息查询</a>
							</dd>
							<dd>
								<a href="javascript:;">课程信息修改</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;">成绩管理</a>
						<dl class="layui-nav-child">
						<dd>
								<a href="javascript:;">成绩查询</a>
							</dd>
							<dd>
								<a href="javascript:;">成绩录入修改</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;">高级</a>
						<dl class="layui-nav-child">
						<dd>
								<a href="javascript:;">男女比</a>
							</dd>
							<dd>
								<a href="javascript:;">人数比</a>
							</dd>
						</dl>
					</li>
				</ul>

			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<!--  div style="padding: 15px;">
			</div>-->
			<iframe id="iframeMain" src="MainPage.jsp" frameborder="0" height="99.4%" width="100%" scrolling="no"></iframe>
		</div>

		<div class="layui-footer" align="center">
			<!-- 底部固定区域 -->
			© jmu.edu.cn - 集美大学计算机工程学院
		</div>
	</div>
	<script src="../layui/layui.js"></script>
	<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});
</script>
<script src="../jquery/jquery-3.4.1.min.js">
</script>
<script>
$(document).ready(function(){
    $("dd>a").click(function (e) {
         e.preventDefault();	//禁止页面的跳转新页面动作
         $("#iframeMain").attr("src",$(this).attr("href"));
     });
    $("h3>a").click(function (e) {
        e.preventDefault();	//禁止页面的跳转新页面动作
        $("#iframeMain").attr("src",$(this).attr("href"));
    });
 });
</script>
</body>
</html>