<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../password/pass-revise.jsp"/>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script src="jquery/jquery-3.4.1.min.js"></script>
<link href="bootstrap-3.3.7-dist/css/bootstrap-theme.css" rel="stylesheet"/>
<link href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrapz.css">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>管理员端|学生管理系统</title>
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo"><h3><a href="console/MainPage.jsp">学生信息管理系统</a></h3></div>

			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="image/School-Logo.JPG" class="layui-nav-img"> ${user.username}
				</a>
					<dl class="layui-nav-child">
						<dd>
							  <a href="javascript:void(0);"data-toggle="modal" data-target="#wyp">修改密码</a>
						</dd>
						<dd>
							<div><a href="SystemServlet?method=LoginOut">退出登录</a></div>
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
			
			<iframe id="iframeMain" src="console/MainPage.jsp" frameborder="0" height="100%" width="100%" scrolling="no"></iframe>
		</div>

		<div class="layui-footer" align="center">
			<!-- 底部固定区域 -->
			© jmu.edu.cn - 集美大学计算机工程学院
		</div>
	</div>
	<script src="layui/layui.js"></script>
	<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});
</script>
<script src="jquery/jquery-3.4.1.min.js">
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