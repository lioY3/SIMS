<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>课程信息修改</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
</head>
<body>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath}/static/jquery/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/layui/tool.js"></script>


	<div
		style="position: absolute; top: 0; width: 100%; height: 100%; z-index: -1">
		<img src="${pageContext.request.contextPath}/static/image/bg2.JPG" height="100%" width="100%" />
	</div>

	<br>
	<!------------------------------------------------------------搜索栏--------------------------------------------------------->
	<div class="demoTable" style="position:relative;left:16%">
		姓名：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_name"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchname">
		<i class="layui-icon">&#xe615;</i>搜索</button>
		学号：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_no"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchno">
		<i class="layui-icon">&#xe615;</i>搜索</button>		
	</div>
	<!------------------------------------------------------------------搜索栏------------------------------------------------------------>
	
	
	<hr class="layui-bg-blue">
	
	<!---------------------------------------------------新增、刷新、编辑、删除按钮-------------------------------------------------------->
	<script type="text/html" id="Cour_headerBar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe608;</i> 新&emsp;增
        </button>
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="refresh"  id ="ref" style=" margin-left: 15px">
            <i class="layui-icon">&#xe669;</i> 刷&emsp;新
        </button>
    </div>
	</script>
	<script type="text/html" id="Cour_lineBar">
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="edit"><i class="layui-icon ">&#xe642;</i> </a>
    	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
        </button>
	</script>
	<!---------------------------------------------------新增、刷新、编辑、删除按钮-------------------------------------------------------->
	
	
	<!-------------------------------------------------------------数据表格--------------------------------------------------------------->
	<div style="text-align: center;">
		<div class="layui-inline">
			<table id="Cour-info" lay-filter="Cour"></table>
		</div>
	</div>
	<!-------------------------------------------------------------数据表格--------------------------------------------------------------->
	
	<!-------------------------------------------------------------新增、编辑表单--------------------------------------------------------->
	<div class="layui-row" id="addopen_div" style="display: none;">
		<form id="add_form" class="layui-form" action="" style="margin-top: 20px; align: center;">

			<div class="layui-form-item">
				<label class="layui-form-label">课程号:</label>
				<div class="layui-inline">
					<input type="text" name="cno" id="form_cno" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">课程名：</label>
				<div class="layui-inline">
					<input type="text" name="cname" id="form_cname" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">学分：</label>
					<div class="layui-input-inline">
				        <input type="text"  name="credit" id="form_credit" class="layui-input">
				    </div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学期:</label>
				<div class="layui-inline">
					<input type="text" name="term" id="term" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学时:</label>
				<div class="layui-inline">
					<input type="text" name="hours" id="form_hours" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">任课教师号:</label>
				<div class="layui-inline">
					<input type="text" name="tno" id="form_tno" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item" style="margin-left: 40%">
				<div class="layui-btn-group">
					<button class="layui-btn layui-btn-normal layui-btn-sm" lay-submit="" lay-filter="Cour_submit">提&ensp;交</button>
					<button type="reset" class="layui-btn layui-btn-primary layui-btn-sm">重&ensp;置</button>
				</div>
			</div>
		</form>
	</div>
	<!-------------------------------------------------------------新增、编辑表单--------------------------------------------------------->
	<script>
		layui.use(['table', 'layer', 'form', 'laypage'], function() {
			var table = layui.table;
			var form = layui.form;
			
		/*		-- 表格实例	 --		*/		
			table.render({
				elem : '#Cour-info',
				id : 'tableOne',
				height : 450,
				width : 1200,
				url : "test.json" //数据接口
				,
				page : true //开启分页
				,
				method: 'post'//提交方式
				,
				limit : 8//默认展示的每页记录数
				,
				limits : [ 5, 8, 10 ]//可选展示的每页记录数
				,
				size : 'sm'//小尺寸表格
				,
				toolbar: '#Cour_headerBar'
				,
				title : '学生信息表'
				,
				cols : [ [ //表头
				{type: 'checkbox', fixed: 'left'}
			     ,{field: 'cno', title: '学号', sort: true, fixed: 'left',align:'center'}
				 ,{field: 'cname', title: '姓名',align:'center'}
				 ,{field: 'Ssex', title: '性别',align:'center'}
				 ,{field: 'Snation', title: '民族',align:'center'}
				 ,{field: 'credit', title: '出生日期', sort: true,align:'center'} 
				 ,{field: 'Sid', title: '身份证',align:'center'}
				 ,{field: 'Dname', title: '学院',align:'center'}
				 ,{field: 'Clname', title: '班级', sort: true,align:'center'} 
				 ,{
		               fixed: 'right',
		               title: '操作',
		               toolbar: '#Cour_lineBar',
		               width: 160,
		               align: 'center'
		             }
				] ]
			});
		
			/*	--	按课程名重载	--	*/
			var $ = layui.$, active = {
				reload : function() {
					var send_name = $('#send_name');

					//执行重载
					table.reload('tableOne', {
						page : {
							curr : 1//重新从第 1 页开始
						},
						where : {
							key : {
								cname : send_name.val()
							}
						}
					}, 'data');
				}
			};

			$('#do_searchname').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			
			/*	--	按课程号重载	--	*/
			var $ = layui.$, active = {
					reload : function() {
						var send_no = $('#send_no');

						//执行重载
						table.reload('tableOne', {
							page : {
								curr : 1//重新从第 1 页开始
							},
							where : {
								key : {
									cno : send_no.val()
								}
							}
						}, 'data');
					}
				};

				$('#do_searchno').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
				
					
 			/*	--	监听头部工具栏	--	*/
					 table.on('toolbar(Cour)', function (obj) {
					    switch (obj.event) {
					        case 'refresh':
					        // 执行一个表格重载即实现刷新功能
					             table.reload('tableOne', {
					                 where: '',
					                 contentType: 'application/x-www-form-urlencoded',
					                 page: {
					                     curr: 1 //重新从第 1 页开始
					                 },
					                 url: "test.json",
					                 method: 'get'
					             });
					             break;
					             // 根据增加行为给form隐藏项赋值
					        case 'add':
					            var data = {};
					            data.action = 'addCour';
					            data.request_type = 'post';
					            data.cno=null;
					            data.cname=null;
					            data.Ssex=null;
					            data.credit=null;
					            data.Sid=null;
					            data.Dname=null;
					            data.Clname=null;
					            // 调用打开弹层的工具方法
					            open_form("#addopen_div", data, '增加学生', '380px', '550px');
					            break;
					    }
					});
 			/* -- 监听操作栏  --*/
 						table.on('tool(Cour)', function (obj) {
						    var data = obj.data;
						    var layEvent = obj.event;
						    var id = data.cno;
						    switch (layEvent) {
						        case 'edit':
						            // 根据编辑行为为form隐藏项赋值
						            data.action = 'updateCour';
						            data.request_type = 'post';
						            data.request_cno = id;
						            open_form("#editopen_div", data, '编辑学生', '380px', '550px');
						            break; 
						    }
						});
 					
			/*	-- 监听表单提交-- */
						form.on('submit(Cour_submit)', function (data) {
						    var uri = data.field.action;
						    var type = data.field.request_type;
						    $.ajax({
						         type: type,
						         url: '/goods/' + uri,
						         contentType: "application/json; charset=utf-8",
						         data: JSON.stringify(data.field),
						         dataType: "json",
						         success: function (result) {
						            if (result.code == "0") {
						                table.reload('tableOne', {
						                    contentType: 'application/x-www-form-urlencoded',
						                    page: {
						                        curr: 1 //重新从第 1 页开始
						                    },
						                    url: '/goods/goodsList',
						                    method: 'get'
						                });
						                layer.msg('修改成功', {icon: 1, time: 1000});
						            } else {  //失败
						                layer.alert(result.msg, {icon: 2}, function () {
						                    layer.close(index);
						                });
						            }
						        }
						    });
						    layer.close(index);//关闭弹出层
						    return false;
						});
		});
	</script>
</body>
</html>