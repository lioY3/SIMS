<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/view/personinfo/pass-revise.jsp"/>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.ico" />

<script src="${pageContext.request.contextPath}/static/jquery/jquery-3.4.1.min.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script>
        $(function () {
        $(".dropdown").mouseover(function () {
            $(this).addClass("open");
        });
 
        $(".dropdown").mouseleave(function () {
            $(this).removeClass("open");
        })
    })
    </script>
<title>管理员端|学生管理系统</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo"style="top:25%"><h3><a href="${pageContext.request.contextPath}/view/console/MainPage.jsp"style="text-decoration:none;text-align:center;">学生信息管理系统</a></h3></div>

			 <ul class="nav navbar-nav navbar-right"style="right:0%">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"
                               data-toggle="dropdown"
                               style="height: 60px">
                                <img alt="" class="img-circle" src="static/image/004.jpg" width="38px" height="38px" />
                                <span style="color: #FFFFFF;font-size: 15px">
                                <i>${user.username}</i>
                            </span>
                            </a>
                            <div class="dropdown-menu pull-right"
                                 style="background: #FFFFFF;width: 320px;overflow: hidden">
                                <div style="margin-top: 16px;border-bottom: 1px solid #eeeeee">
                                    <div style="text-align: center">
                                        <img class="img-circle" src="static/image/004.jpg"
                                             style="width: 38px;height: 38px;"/>
                                    </div>
                                    <div style="color: #323534;text-align: center;line-height: 36px;font-size: 15px">
                                        <span>${user.username}</span>
                                    </div>
                                </div>
 
                                <div class="row" style="margin-left: 15px;margin-right: 15px;margin-top: 10px">
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-user" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px" 
                                        onclick="javascript:window.location.href='${pageContext.request.contextPath}/view/personinfo/infochange.jsp'">
                                               	个人中心</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-gear" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px" >
                                            	账号管理</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-key" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px"data-toggle="modal" data-target="#wyp">
                                         	   密码修改</p>
                                    </div>
                                </div>
 
                                <div class="row" style="margin-left: 15px;margin-right: 15px;margin-top: 10px">
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-user-circle" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                           	 修改头像</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-comments" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                          	  消息</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-heart-o" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            	帮助中心</p>
                                    </div>
                                </div>
 
                                <div class="text-center"onclick="javascript:window.location.href='SystemServlet?method=LoginOut'"
                                		style="margin-top: 20px; padding: 15px;margin: 0px;background: #f6f5f5;color: #323534;">
                                		退出登陆
                                </div>
                            </div>
                        </li>
                    </ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域   -->
				
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
					<a class=""
						href="javascript:;"style="text-decoration:none;">学生管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="view/student/Stu-Infor.jsp"style="text-decoration:none;">学生信息查询</a>
							</dd>
							<dd>
								<a href="view/student/Stu-modify.jsp"style="text-decoration:none;">学生信息修改</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;" style="text-decoration:none;">教师管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="view/teacher/Tea-Infor.jsp"style="text-decoration:none;">教师信息查询</a>
							</dd>
							<dd>
								<a href="view/teacher/Tea-modify.jsp"style="text-decoration:none;">教师信息修改</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"style="text-decoration:none;">课程管理</a>
					<dl class="layui-nav-child">
							<dd>
								<a href="view/course/Cour-info.jsp"style="text-decoration:none;">课程信息查询</a>
							</dd>
							<dd>
								<a href="view/course/Cour-modify.jsp"style="text-decoration:none;">课程信息修改</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;"style="text-decoration:none;">成绩管理</a>
						<dl class="layui-nav-child">
						<dd>
								<a href="ScoreServlet?method=toScoreInfoView" style="text-decoration:none;">成绩查询</a>
							</dd>
							<dd>
								<a href="ScoreServlet?method=toScoreModifyView"style="text-decoration:none;">成绩录入修改</a>
							</dd>
						</dl>
					</li>
				<li class="layui-nav-item"><a href="javascript:;"style="text-decoration:none;">高级</a>
						<dl class="layui-nav-child">
						<dd>
								<a href="view/console/echarts.jsp"style="text-decoration:none;">成绩统计</a>
							</dd>
							<!-- <dd>
								<a href="javascript:;"style="text-decoration:none;">人数比</a>
							</dd> -->
						</dl>
					</li> 
				</ul>

			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			
			<iframe id="iframeMain" src="${pageContext.request.contextPath}/view/console/MainPage.jsp" frameborder="0" height="100%" width="100%" scrolling="no"></iframe>
		</div>

		<div class="layui-footer" align="center">
			<!-- 底部固定区域 -->
			© jmu.edu.cn - 集美大学计算机工程学院
		</div>
	</div>
	<script src="static/layui/layui.js"></script>
	<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});
</script>
<script src="static/jquery/jquery-3.4.1.min.js">
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