<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Information</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
</head>
<body>
<div style="position:absolute; top:0; width:100%; height:100%; z-index:-1">   
	<img src="${pageContext.request.contextPath}/static/image/bg2.JPG" height="100%" width="100%"/>   
</div>
	
	<br>
	<br>
	<br>
	<div class="demoTable" style="position:relative;left:16%">
		姓名：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_name"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchname">
		<i class="layui-icon">&#xe615;</i>搜索</button>
		教师号：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_no"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchno">
		<i class="layui-icon">&#xe615;</i>搜索</button>
		课程：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_cname"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchcname">
		<i class="layui-icon">&#xe615;</i>搜索</button>
	</div>
	

	<div style="text-align: center;">
        <div class="layui-inline">
			<table id="tea-info" lay-filter="test"></table>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	
	<script>
		layui.use('table', function() {
			var table = layui.table;
			
		/*		-- 表格实例	 --		*/		
			table.render({
				elem : '#tea-info',
				id : 'tableOne',
				height : 450,
				width : 900,
				url : "${pageContext.request.contextPath}/TeacherServlet?method=TeacherList", //数据接口
				method :'post'//提交方式
				,
				page : true //开启分页
				,
				limit : 8//默认展示的每页记录数
				,
				limits : [ 5, 8, 10 ]//可选展示的每页记录数
				,
				size : 'sm'//小尺寸表格
				,
				cols : [ [ //表头
			     {field: 'tno', title: '教师号', sort: true, fixed: 'left',align:'center'}
				 ,{field: 'tname', title: '姓名',align:'center'}
				 ,{field: 'tsex', title: '性别',align:'center'}
				 ,{field: 'cname', title: '授课课程',align:'center'} 
				] ]
			});
		
			/*	--	搜索重载	--	*/
			var $ = layui.$, active = {
				reload : function() {
					var send_no = $('#send_no');
					var send_cname = $('#send_cname');
					var send_name = $('#send_name');
					//执行重载
					table.reload('tableOne', {
						page : {
							curr : 1//重新从第 1 页开始
						},
						where : {
							key : {
								tname : send_name.val(),
								tno : send_no.val(),
								cname : send_cname.val()
							}
						}
					}, 'data');
				}
			};

			$('#do_searchname').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			$('#do_searchclass').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			$('#do_searchno').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});

		});
	</script>
</body>
</html>