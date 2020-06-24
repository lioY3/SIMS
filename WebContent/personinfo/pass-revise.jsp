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

<title>Insert title here</title>
<script type="text/javascript">  
 function xiugai(){
	 $("wyp").show();
 }
 



 $(function(){
	$("#StorePassword").on('input',function(){
		if(this.value.length==6)
		 {
			$(".queding").prop('disabled',false);
		 }else{
			 $(".queding").prop('disabled',true);
		 }
		$("newStorePassword").on('input',function(){
			if(this.value.length==6)
			 {
				$(".queding").prop('disabled',false);
			 }else{
				 $(".queding").prop('disabled',true);
			 }
			
		});
		
    })
	
 });
 
 

  
  





</script>
</head>
<body>
        <div class="modal changepassesModal" id="wyp">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- 创建隐藏域，得到uid用户的id(js中无法使用el)，因为修改当前用户的密码，首先要知道获取当前用户所在数据表中的id-->
				<input name="uiid" type="hidden"/>
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" id="gb">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<i class="icon-info"></i><span style="margin-left: .5em;">修改密码</span>
				</h4>
			</div>

			<!-- BEGIN FORM 修改密码的第一要素：输入新密码-->
			<form class="form-horizontal" method="post" name="passForm"
				  novalidate>
				<div class="modal-body">
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-3">新密码</label>
							<div class="input-icon col-md-7">
								 <input
									class="form-control placeholder-no-fix" type="password"
									placeholder="新密码" name="StorePassword" id="StorePassword" required /><span
									class="help-block" hidden   minlength="6" maxlength="6"
							></span>
							</div>
						</div>
						<!-- 修改密码的第二要素：确定新密码-->
						<div class="form-group">
							<label class="control-label col-md-3">确认密码</label>
							<div class="controls">
								<div class="input-icon col-md-7">
									<input class="form-control placeholder-no-fix" type="password"
										   placeholder="确认密码" name="newStorePassword" id="newStorePassword" required/><span
										class="help-block" hidden  minlength="6" maxlength="6"
								></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 关闭和确定修改-->
				<div class="modal-footer">
					<button type="reset" id="close" class="btn btn-default" >重置</button>
					<button type="button" class="btn btn-default">提交</button>
				</div>
			</form>


			<!-- END FORM-->
		</div>
	</div>
	</div>
</body>

</html>