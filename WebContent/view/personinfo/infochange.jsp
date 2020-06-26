<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${pageContext.request.contextPath}/static/jquery/jquery-3.4.1.min.js"></script>
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</head>

<body>
<div class="page-header">
  <h3>个人信息 </h3>
</div>
<div class="container">
    <form action="" class="form-horizontal"style="position: absolute;width: 350px;height: 400px;background: transparent;left: 40%;top: 30%;">
            <div class="form-group">
                    <label class="control-label col-md-3">昵称</label>
                    <input type="text" id="name" readonly="readonly" style="height:30px;"value="煦妹">
            </div>
            <div class="form-group">
                    <label class="control-label col-md-3">手机</label>
                    <input type="text" id="fhone"  readonly="readonly"style="height:30px;"value="26060606">
            </div>

            <div class="form-group">
                    <label class="control-label col-md-3">性别</label> &nbsp;  
                    <input type="radio" id="#manager_name" name="radio1" readonly="readonly">保密&nbsp;&nbsp;                 
                    <input type="radio" id="#manager_name" name="radio1" readonly="readonly">男&nbsp;&nbsp;
                    <input type="radio" id="#manager_name" name="radio1" readonly="readonly">女
            </div>
            <div class="form-group">
                    <label class="control-label col-md-3">邮箱</label>
                    <input type="text" id="#manager_name"  readonly="readonly"style="height:30px;"value="1156609270@ss.com">
            </div>
            <div class="form-group">
                    <label class="control-label col-md-3">生日</label>
                    <input type="text" id="#manager_name"  readonly="readonly"style="height:30px;"value="2000-4-21">
            </div>
           
            <div class="form-group">
                    <label class="control-label col-md-3">介绍</label>
                    <textarea class="form-control" rows="5" >I'm a bay guy</textarea>
            </div>

            
			<div class="btn-group" style="left:28%;">
				 <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">修改</button>
				  <button type="button" class="btn btn-default">提交</button>
			</div>	
 
    </form>
</div>
<div class="modal changepassesModal" id="myModal">>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">编辑个人信息</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">昵称</span>
							  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
					
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">手机</span>
							  <input type="text" class="form-control" placeholder="iphone" aria-describedby="basic-addon1">
					</div>
							
					<div class="input-group">
							  <input type="text" class="form-control" placeholder=E-mail aria-describedby="basic-addon2">
							  <span class="input-group-addon" id="basic-addon2">@example.com</span>
							
							
					</div>
							
                     <div class="form-group"style="left:0%;">
				                <label for="dtp_input2" class="col-md-2 control-label">Date Picking</label>
				                <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="dd MM yyyy">
				                    <input class="form-control" style="width:100%" type="text" value="" readonly="">
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<input type="hidden" id="dtp_input2" value=""><br>
				            </div>
                       <div class="form-group" >
                        <label for="" class="col-sm-2 control-label">介绍：</label>
                        <div class="col-sm-9" >
                              <textarea class="form-control" onblur="Isintroduc(this.value)" rows="5"id="modal_introduc" placeholder="请输入新介绍"></textarea></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"  data-dismiss="modal" id="sava-edit-btn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<!--中文引用-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language : 'zh-CN',
        //format : 'yyyy-mm-dd hh:ii:ss',//日期格式
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

</script>

</body>
</html>