<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/pass-revise.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.ico" />
<title>管理员端|学生管理系统</title>
<script type="text/javascript">  
	
	
	$(function(){
		//点击图片切换验证码
		$("#vcodeImg").click(function(){
			this.src="SystemServlet?method=GetVCode&time"+new Date().getTime();
		});
		
		form.on('submit("requeding")', function (data) {
			$.ajax({
				type: "post",
				url: "SystemServlet?method=EditPassword&user=${user.account}",
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
					} 
				}
				
			});
		});
		
		
		
		
		
	});

  
  





</script>
</head>
<body>
 <div class="modal changepassesModal" id="wyp">
	<div class="modal-dialog">
		  <div class="modal-content">     
			<!-- BEGIN FORM 修改密码的第一要素：输入新密码-->
			
			<form class="form-horizontal" action="SystemServlet?method=EditPassword&user=${user.account}" method="post" id=passform" name="passform" novalidate>
				<div class="modal-body">
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-3">用户名</label>
							<div class="input-icon col-md-7">
								 <input
									class="form-control placeholder-no-fix" type="password"
									placeholder="用户名" name="StorePassword" id="newAcount" required /><span
									class="help-block" hidden   minlength="6" maxlength="6"
							></span>
							</div>
						 </div>
						 <div class="form-group">
							<label class="control-label col-md-3">新密码</label>
							<div class="controls">
								<div class="input-icon col-md-7">
									<input class="form-control placeholder-no-fix" type="password"
										   placeholder="确认密码" name="newStorePassword" id="newPassword" required/><span
										class="help-block" hidden  minlength="6" maxlength="6"
								></span>
								</div>
							</div>
						 </div>
						 <div class="form-group">
							     <label class="control-label col-md-3">验证码</label>
							     <div class="controls">
								<div class="input-icon col-md-7">
									<input class="form-control placeholder-no-fix" type="password"
										   placeholder="验证码" name="proof" id="newStorePassword" required/><span
										class="help-block" hidden  minlength="6" maxlength="6"
								></span>        
								<img title="点击图片切换验证码" id="vcodeImg" style="height:40px;width:115px" src="SystemServlet?method=GetVCode"></div>
								</div>
						</div>
						</div>
					</div>
				<!-- 关闭和确定修改-->
							<div class="modal-footer">
								<button type="reset" id="close" class="btn btn-default" >重置</button>
								<button type="submit" id="requeding" class="btn btn-default" lay-filter="requeding">提交</button>
							</div>
			</form>

          
			<!-- END FORM-->
			</div>
		</div>
	</div>
</body>

</html>