<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.ico"/>

<title>教师端|学生管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo"><h3><a href="${pageContext.request.contextPath}/view/console/MainPage.jsp">学生信息管理系统</a></h3></div>
			
			
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="${pageContext.request.contextPath}/static/image/School-Logo.JPG" class="layui-nav-img"> ${user.username}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">修改密码</a>
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
								<a href="StudentServlet?method=toStudentInfoView">学生信息查询</a>
							</dd>
							<dd>
								<a href="StudentServlet?method=toStudentModifyView">学生信息修改</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">教师管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="TeacherServlet?method=toTeacherInfoView">教师信息查询</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">课程管理</a>
					<dl class="layui-nav-child">
							<dd>
								<a href="CourseServlet?method=toCourseInfoView">课程信息查询</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;">成绩管理</a>
						<dl class="layui-nav-child">
						<dd>
								<a href="ScoreServlet?method=toScoreInfoView">成绩查询</a>
							</dd>
							<dd>
								<a href="ScoreServlet?method=toScoreModifyView">成绩录入修改</a>
							</dd>
						</dl>
					</li>
				</ul>

			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<iframe id="iframeMain" src="${pageContext.request.contextPath}/view/console/MainPage.jsp" frameborder="0" height="99.4%" width="100%" scrolling="no"></iframe>
		</div>

		<div class="layui-footer" align="center">
			<!-- 底部固定区域 -->
			© jmu.edu.cn - 集美大学计算机工程学院
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});
</script>
<script src="${pageContext.request.contextPath}/static/jquery/jquery-3.4.1.min.js">
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